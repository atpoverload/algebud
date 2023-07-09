package algebud.service;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A client that computes the roots of a quadratic equation given integer coefficients.
 *
 * <p>java -jar bazel-bin/service/java/algebud/service/root_finder_deploy.jar 1 2 1
 *
 * <p>the roots for x^2 + 2*x + 1 are -1.00 and -1.00
 */
final class RootFinder {
  private static final String QUADRATIC_POS = "(-B + (B^2 - 4*A*C)^0.5) / (2*C)";
  private static final String QUADRATIC_NEG = "(-B - (B^2 - 4*A*C)^0.5) / (2*C)";

  // TODO(timur): i rushed and did this manually so the output looks hand-written but this is a disaster to read
  private static void printRoots(int[] coeffs, double[] roots) {
    String a = "";
    if (coeffs[0] == 1) {
      a = "x^2";
    } else if (coeffs[0] != 0) {
      a = String.format("%s*x^2", coeffs[0]);
    }

    String b = "";
    if (coeffs[1] == 1) {
      if (a.isBlank()) {
        b = "x";
      } else {
        b = " + x";
      }
    } else if (coeffs[1] == -1) {
      if (a.isBlank()) {
        b = "-x";
      } else {
        b = " - x";
      }
    } else if (coeffs[1] < 0) {
      if (a.isBlank()) {
        b = String.format(" -%d*x", -coeffs[1]);
      } else {
        b = String.format(" - %d*x", -coeffs[1]);
      }
    } else if (coeffs[1] > 0) {
      if (a.isBlank()) {
        b = String.format("%d*x", coeffs[1]);
      } else {
        b = String.format(" + %d*x", coeffs[1]);
      }
    }

    String c = "";
    if (coeffs[2] < 0) {
      if (a.isBlank() && b.isBlank()) {
        c = String.format("-%d", -coeffs[2]);
      } else {
        c = String.format(" - %d", -coeffs[2]);
      }
    } else if (coeffs[2] > 0) {
      if (a.isBlank() && b.isBlank()) {
        c = String.format("%d", coeffs[2]);
      } else {
        c = String.format(" + %d", coeffs[2]);
      }
    }
    String expression = String.format("%s%s%s", a, b, c);

    if (Double.isNaN(roots[0]) || Double.isNaN(roots[1])) {
      System.out.println(
          String.format("the roots for %s are complex and cannot be found", expression));
    } else {
      System.out.println(
          String.format("the roots for %s are %.2f and %.2f", expression, roots[0], roots[1]));
    }
  }

  private final AlgebudServiceGrpc.AlgebudServiceBlockingStub stub;

  private String positiveId = "";
  private String negativeId = "";

  private RootFinder(ManagedChannel channel) {
    this.stub = AlgebudServiceGrpc.newBlockingStub(channel);
  }

  private void compile() {
    if (positiveId.isBlank()) {
      CompileRequest compileRequest =
          CompileRequest.newBuilder()
              .setExpression(Expression.newBuilder().addToken(QUADRATIC_POS))
              .build();
      this.positiveId = this.stub.compile(compileRequest).getExpressionId();
    }
    if (negativeId.isBlank()) {
      CompileRequest compileRequest =
          CompileRequest.newBuilder()
              .setExpression(Expression.newBuilder().addToken(QUADRATIC_NEG))
              .build();
      this.negativeId = this.stub.compile(compileRequest).getExpressionId();
    }
  }

  private double[] findRoots(double a, double b, double c) {
    List<Variable> variables =
        List.of(
            Variable.newBuilder().setName("A").setValue(a).build(),
            Variable.newBuilder().setName("B").setValue(b).build(),
            Variable.newBuilder().setName("C").setValue(c).build());
    double posRoot =
        stub.compute(
                ComputeRequest.newBuilder()
                    .setExpressionId(positiveId)
                    .addAllVariable(variables)
                    .build())
            .getResult();
    double negRoot =
        stub.compute(
                ComputeRequest.newBuilder()
                    .setExpressionId(negativeId)
                    .addAllVariable(variables)
                    .build())
            .getResult();
    return new double[] {posRoot, negRoot};
  }

  public static void main(String[] args) throws InterruptedException {
    // TODO(timur): we should make this an arg/option
    String target = "localhost:8980";

    int[] coeffs =
        new int[] {Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])};

    ManagedChannel channel =
        Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
    try {
      RootFinder rootFinder = new RootFinder(channel);
      rootFinder.compile();
      double[] roots = rootFinder.findRoots(coeffs[0], coeffs[1], coeffs[2]);
      printRoots(coeffs, roots);
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}

package algebud.service;

import static java.util.stream.Collectors.toMap;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A client that mimics the behavior of {@link AlgebudCLI}. Adapated from
 * https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldClient.java
 */
final class AlgebudClient {
  // TODO(timur): this isn't validated on the client-side, so error details gets swallowed by grpc
  private static Expression buildExpression(String[] expression) {
    Expression.Builder builder = Expression.newBuilder();
    for (String term : expression) {
      builder.addTerm(term);
    }
    return builder.build();
  }

  // TODO(timur): had to copy this from the cli
  private static List<Variable> getVariables() {
    Map<String, Double> singleVariables =
        System.getProperties().entrySet().stream()
            .filter(variable -> ((String) variable.getKey()).contains("algebud.variable."))
            .collect(
                toMap(
                    variable -> ((String) variable.getKey()).split("\\.")[2],
                    variable -> Double.parseDouble(((String) variable.getValue()))));
    Map<String, Double> multiVariables = new HashMap<>();
    if (!System.getProperty("algebud.variables", "").isBlank()) {
      multiVariables =
          Arrays.stream(System.getProperty("algebud.variables", "").split(","))
              .collect(
                  toMap(
                      variable -> variable.split(":")[0],
                      variable -> Double.parseDouble(variable.split(":")[1])));
    }
    List<Variable> variables = new ArrayList<>();
    for (String variable : multiVariables.keySet()) {
      if (singleVariables.keySet().contains(variable)) {
        throw new IllegalArgumentException(
            String.format(
                "multiple values for variable '%s' found (%s, %s)",
                variable, singleVariables.get(variable), multiVariables.get(variable)));
      } else {
        variables.add(
            Variable.newBuilder().setName(variable).setValue(multiVariables.get(variable)).build());
      }
    }
    for (String variable : singleVariables.keySet()) {
      variables.add(
          Variable.newBuilder().setName(variable).setValue(singleVariables.get(variable)).build());
    }
    return variables;
  }

  private static class AlgebudClientWrapper {
    private final AlgebudServiceGrpc.AlgebudServiceBlockingStub stub;

    private AlgebudClientWrapper(ManagedChannel channel) {
      this.stub = AlgebudServiceGrpc.newBlockingStub(channel);
    }

    private String compile(Expression expression) {
      CompileRequest compileRequest = CompileRequest.newBuilder().setExpression(expression).build();
      return stub.compile(compileRequest).getExpressionId();
    }

    private double compute(String id, List<Variable> variables) {
      ComputeRequest computeRequest =
          ComputeRequest.newBuilder().setExpressionId(id).addAllVariable(variables).build();
      return stub.compute(computeRequest).getResult();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    // TODO(timur): we should make this an arg/option
    String target = "localhost:8980";

    Expression expression = buildExpression(args);
    List<Variable> variables = getVariables();

    ManagedChannel channel =
        Grpc.newChannelBuilder(target, InsecureChannelCredentials.create()).build();
    try {
      AlgebudClientWrapper client = new AlgebudClientWrapper(channel);

      String id = client.compile(expression);

      double result = client.compute(id, variables);

      System.out.println(
          String.format(
              "%s where %s = %f",
              String.join(" ", expression.getTermList()),
              variables.stream().collect(toMap(v -> v.getName(), v -> v.getValue())),
              result));
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}

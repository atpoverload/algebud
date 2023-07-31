package algebud.service;

import static algebud.Evaluator.evaluate;
import static algebud.token.Tokenizer.toPostfix;
import static algebud.token.Tokenizer.tokenize;
import static java.util.stream.Collectors.toMap;

import algebud.token.Token;
import algebud.token.Variable;
import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A server that uses the {@link Tokenizer} to cache postfix expressions, and only evaluate
 * expressions from the cached ids. Adapted from
 * https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/helloworld/HelloWorldServer.java
 */
final class AlgebudServer {
  // TODO(timur): definitely need a logger in here
  private static class AlgebudService extends AlgebudServiceGrpc.AlgebudServiceImplBase {
    // TODO(timur): very crude caching system
    private final HashMap<String, List<Token>> expressions = new HashMap<>();

    private AlgebudService() {}

    @Override
    public void compile(CompileRequest request, StreamObserver<CompileResult> resultObserver) {
      String expressionId = compileExpression(request.getExpression());
      resultObserver.onNext(CompileResult.newBuilder().setExpressionId(expressionId).build());
      resultObserver.onCompleted();
    }

    @Override
    public void compute(ComputeRequest request, StreamObserver<ComputeResult> resultObserver) {
      List<Token> tokens = fetchExpressionById(request.getExpressionId());
      Map<Variable, Double> variables =
          request.getVariableList().stream()
              .collect(toMap(v -> Variable.of(v.getName()), v -> v.getValue()));

      System.out.println(String.format("evaluating %s where %s", tokens, variables));
      double result = evaluate(tokens, variables);
      resultObserver.onNext(ComputeResult.newBuilder().setResult(result).build());
      resultObserver.onCompleted();
    }

    private String compileExpression(Expression expression) {
      // TODO(timur): this is probably insecure
      String expressionString = String.join("", expression.getTermList()).replaceAll("\\s+", "");
      synchronized (expressions) {
        if (expressions.containsKey(expressionString)) {
          System.out.println(String.format("already compiled %s", expressionString));
        } else {
          System.out.println(String.format("compiling %s", expressionString));
          List<Token> tokens = toPostfix(tokenize(expressionString));
          expressions.put(expressionString, tokens);
        }
        return expressionString;
      }
    }

    private List<Token> fetchExpressionById(String expressionId) {
      synchronized (expressions) {
        if (expressions.containsKey(expressionId)) {
          System.out.println(String.format("expression for id %s found", expressionId));
          return expressions.get(expressionId);
        } else {
          throw new IllegalArgumentException(
              String.format("expression for id %s was not found", expressionId));
        }
      }
    }
  }

  private final int port;
  private final Server server;

  private AlgebudServer(int port) throws IOException {
    this.port = port;
    this.server =
        Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
            .addService(new AlgebudService())
            .build();
  }

  /** Start serving requests. */
  public void start() throws IOException {
    server.start();
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread() {
              @Override
              public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                  AlgebudServer.this.stop();
                } catch (InterruptedException e) {
                  e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
              }
            });
  }

  /** Stop serving requests and shutdown resources. */
  public void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /** Await termination on the main thread since the grpc library uses daemon threads. */
  public void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  /** Spins up the server. */
  public static void main(String[] args) throws Exception {
    AlgebudServer server = new AlgebudServer(8980);
    server.start();
    server.blockUntilShutdown();
  }
}

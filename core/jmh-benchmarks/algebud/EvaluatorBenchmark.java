package algebud;

import static algebud.token.Tokenizer.toPostfix;
import static algebud.token.Tokenizer.tokenize;

import algebud.token.Token;
import algebud.token.Variable;
import java.util.List;
import java.util.Map;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Based on running this benchmark, the performance is very poor, around a factor of 40x worse.
 * 
 * Benchmark                                             Mode  Cnt          Score         Error  Units
 * EvaluatorBenchmark.euclideanDistance                 thrpt   25    5222894.372 ±  187899.856  ops/s
 * EvaluatorBenchmark.euclideanDistanceNative           thrpt   25  216500878.845 ±  830771.037  ops/s
 * EvaluatorBenchmark.euclideanDistanceNativeOptimized  thrpt   25  213836385.313 ± 2147726.721  ops/s
 * EvaluatorBenchmark.euclideanDistanceOptimized        thrpt   25    3172408.584 ±   87259.703  ops/s
 * EvaluatorBenchmark.quadraticFormula                  thrpt   25    5203744.156 ±  230679.066  ops/s
 * EvaluatorBenchmark.quadraticFormulaNative            thrpt   25  213843681.711 ± 1144579.348  ops/s
 * EvaluatorBenchmark.quadraticFormulaNativeOptimized   thrpt   25  215386036.392 ±  699907.468  ops/s
 * EvaluatorBenchmark.quadraticFormulaOptimized         thrpt   25    5083875.371 ±  121673.598  ops/s
 */
public class EvaluatorBenchmark {
  private static final double X_0 = 0;
  private static final double X_1 = 1;
  private static final double Y_0 = 0;
  private static final double Y_1 = 1;
  private static final Map<Variable, Double> POINTS =
      Map.of(
          Variable.of("X_0"),
          X_0,
          Variable.of("Y_0"),
          Y_0,
          Variable.of("X_1"),
          X_1,
          Variable.of("Y_1"),
          Y_1);
  public static final List<Token> EUCLIDEAN_DISTANCE =
      toPostfix(tokenize("((X_1 - X_0)^2 + (Y_1 - Y_0)^2)^0.5"));
  public static final List<Token> EUCLIDEAN_DISTANCE_OPTIMIZED =
      toPostfix(tokenize("((X_1 - X_0)*(X_1 - X_0) + (Y_1 - Y_0)*(Y_1 - Y_0))^0.5"));

  private static final double A = 1;
  private static final double B = 2;
  private static final double C = 1;
  private static final Map<Variable, Double> COEFFICIENTS =
      Map.of(Variable.of("A"), A, Variable.of("B"), B, Variable.of("C"), C);
  public static final List<Token> QUADRATIC_FORMULA =
      toPostfix(tokenize("(-B + (B^2 - 4*A*C)^0.5) / (2*C)"));
  public static final List<Token> QUADRATIC_FORMULA_OPTIMIZED =
      toPostfix(tokenize("(-B + (B*B - 4*A*C)^0.5) / (2*C)"));

  @Benchmark
  public void euclideanDistance(Blackhole blackhole) {
    blackhole.consume(Evaluator.evaluate(EUCLIDEAN_DISTANCE, POINTS));
  }

  @Benchmark
  public void quadraticFormula(Blackhole blackhole) {
    blackhole.consume(Evaluator.evaluate(QUADRATIC_FORMULA, COEFFICIENTS));
  }

  @Benchmark
  public void euclideanDistanceOptimized(Blackhole blackhole) {
    blackhole.consume(Evaluator.evaluate(EUCLIDEAN_DISTANCE_OPTIMIZED, POINTS));
  }

  @Benchmark
  public void quadraticFormulaOptimized(Blackhole blackhole) {
    blackhole.consume(Evaluator.evaluate(QUADRATIC_FORMULA_OPTIMIZED, COEFFICIENTS));
  }

  @Benchmark
  public void euclideanDistanceNative(Blackhole blackhole) {
    blackhole.consume(Math.sqrt(Math.pow(X_1 - X_0, 2) + Math.pow(Y_1 - Y_0, 2)));
  }

  @Benchmark
  public void quadraticFormulaNative(Blackhole blackhole) {
    blackhole.consume((-B + Math.sqrt(Math.pow(B, 2) - 4 * A * C)) / (2 * C));
  }

  @Benchmark
  public void euclideanDistanceNativeOptimized(Blackhole blackhole) {
    blackhole.consume(Math.sqrt((X_1 - X_0) * (X_1 - X_0) + (Y_1 - Y_0) * (Y_1 - Y_0)));
  }

  @Benchmark
  public void quadraticFormulaNativeOptimized(Blackhole blackhole) {
    blackhole.consume((-B + Math.sqrt(B * B - 4 * A * C)) / (2 * C));
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder().include(EvaluatorBenchmark.class.getSimpleName()).build();
    new Runner(opt).run();
  }
}

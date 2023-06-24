package algebud;

import static algebud.Evaluator.evaluate;
import static algebud.token.Tokenizer.toPostfix;
import static algebud.token.Tokenizer.tokenize;
import static org.junit.Assert.assertEquals;

import algebud.token.Variable;
import java.util.Map;
import org.junit.Test;

// TODO(timur): this test is redundant, we can write a template for the cases, maybe with a golden?
public class EvaluatorTest {
  private static final double A = 1;
  private static final double B = 2;
  private static final double C = 1;
  private static final Map<Variable, Double> COEFFICIENTS =
      Map.of(Variable.of("A"), A, Variable.of("B"), B, Variable.of("C"), C);

  // TODO(timur): we should be able to fuzz these tests
  @Test
  public void variables_success() {
    assertEquals(A, evaluate(toPostfix(tokenize("A")), COEFFICIENTS), 0);
  }

  @Test
  public void basicOps_success() {
    assertEquals(A + B, evaluate(toPostfix(tokenize("A + B")), COEFFICIENTS), 0);
    assertEquals(A - B, evaluate(toPostfix(tokenize("A - B")), COEFFICIENTS), 0);
    assertEquals(A * B, evaluate(toPostfix(tokenize("A * B")), COEFFICIENTS), 0);
    assertEquals(A / B, evaluate(toPostfix(tokenize("A / B")), COEFFICIENTS), 0);
    assertEquals(Math.pow(A, B), evaluate(toPostfix(tokenize("A ^ B")), COEFFICIENTS), 0);
  }

  @Test
  public void addition2_success() {
    assertEquals(A + 1, evaluate(toPostfix(tokenize("A + 1")), COEFFICIENTS), 0);
  }

  @Test
  public void addition3_success() {
    assertEquals(A + 0, evaluate(toPostfix(tokenize("A + 0.0")), COEFFICIENTS), 0);
  }

  @Test
  public void addition4_success() {
    assertEquals(A + Math.E, evaluate(toPostfix(tokenize("A + @e")), COEFFICIENTS), 0);
  }

  @Test
  public void parenthesis1_success() {
    assertEquals(A, evaluate(toPostfix(tokenize("(A)")), COEFFICIENTS), 0);
  }

  @Test
  public void parenthesis2_success() {
    assertEquals(A * (B + C), evaluate(toPostfix(tokenize("A * (B + C)")), COEFFICIENTS), 0);
  }

  @Test
  public void parenthesis3_success() {
    assertEquals(A - (B + C), evaluate(toPostfix(tokenize("A - (B + C)")), COEFFICIENTS), 0);
  }

  @Test
  public void negation1_success() {
    assertEquals(-A, evaluate(toPostfix(tokenize("-A")), COEFFICIENTS), 0);
  }

  @Test
  public void negation2_success() {
    assertEquals(A + -B, evaluate(toPostfix(tokenize("A + -B")), COEFFICIENTS), 0);
  }

  @Test
  public void negation3_success() {
    assertEquals(A * -(B + C), evaluate(toPostfix(tokenize("A * -(B + C)")), COEFFICIENTS), 0);
  }

  @Test
  public void quadraticFormula_success() {
    assertEquals(
        (-B + Math.pow(Math.pow(B, 2) - 4 * A * C, 0.5)) / (2 * C),
        evaluate(toPostfix(tokenize("(-B + (B^2 - 4*A*C)^0.5) / (2*C)")), COEFFICIENTS),
        0);
  }
}

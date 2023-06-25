package algebud.token;

import static algebud.token.Tokenizer.toPostfix;
import static algebud.token.Tokenizer.tokenize;
import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

// TODO(timur): this test is huge. can we write a template for the cases, maybe with a golden?
public class TokenizerTest {
  private static final Token A = Variable.of("A");
  private static final Token B = Variable.of("B");
  private static final Token C = Variable.of("C");
  private static final Token ZERO = new ConstantValue(0);
  private static final Token ONE = new ConstantValue(1);
  private static final Token EULERS_NUMBER = NamedConstant.of("@e");

  // TODO(timur): we should be able to fuzz these tests
  @Test
  public void variables_success() {
    assertEqualContents(List.of(A), tokenize("A"));
  }

  @Test
  public void basicOps_success() {
    assertEqualContents(List.of(A, Operation.ADD, B), tokenize("A + B"));
    assertEqualContents(List.of(A, Operation.SUBTRACT, B), tokenize("A - B"));
    assertEqualContents(List.of(A, Operation.MULTIPLY, B), tokenize("A * B"));
    assertEqualContents(List.of(A, Operation.DIVIDE, B), tokenize("A / B"));
    assertEqualContents(List.of(A, Operation.EXPONENTIATE, B), tokenize("A ^ B"));
  }

  @Test
  public void addition2_success() {
    assertEqualContents(List.of(Variable.of("A"), Operation.ADD, ONE), tokenize("A + 1"));
  }

  @Test
  public void addition3_success() {
    assertEqualContents(List.of(A, Operation.ADD, ZERO), tokenize("A + 0.0"));
  }

  @Test
  public void addition4_success() {
    assertEqualContents(List.of(A, Operation.ADD, EULERS_NUMBER), tokenize("A + @e"));
  }

  @Test
  public void parenthesis1_success() {
    assertEqualContents(
        List.of(Operation.LEFT_PARENTHESIS, A, Operation.RIGHT_PARENTHESIS), tokenize("(A)"));
  }

  @Test
  public void parenthesis2_success() {
    assertEqualContents(
        List.of(
            A,
            Operation.MULTIPLY,
            Operation.LEFT_PARENTHESIS,
            B,
            Operation.ADD,
            C,
            Operation.RIGHT_PARENTHESIS),
        tokenize("A * (B + C)"));
  }

  @Test
  public void parenthesis3_success() {
    assertEqualContents(
        List.of(
            A,
            Operation.SUBTRACT,
            Operation.LEFT_PARENTHESIS,
            B,
            Operation.ADD,
            C,
            Operation.RIGHT_PARENTHESIS),
        tokenize("A - (B + C)"));
  }

  @Test
  public void negation1_success() {
    assertEqualContents(List.of(Variable.of("-A")), tokenize("-A"));
  }

  @Test
  public void negation2_success() {
    assertEqualContents(List.of(A, Operation.ADD, Variable.of("-B")), tokenize("A + -B"));
  }

  @Test
  public void negation3_success() {
    assertEqualContents(
        List.of(
            A,
            Operation.MULTIPLY,
            new ConstantValue(-1),
            Operation.MULTIPLY,
            Operation.LEFT_PARENTHESIS,
            B,
            Operation.ADD,
            C,
            Operation.RIGHT_PARENTHESIS),
        tokenize("A * -(B + C)"));
  }

  @Test
  public void negation4_failure() {
    String expression = "A / -(B + C)";
    try {
      tokenize(expression);
      throw new RuntimeException(String.format("expected '%s' to fail tokenization", expression));
    } catch (IllegalArgumentException iae) {
      assertEquals(
          iae.getMessage(),
          "unable to parse expressions of the form '... X / -(...'. consider moving the"
              + " negative sign above the divider");
    } catch (Exception e) {
      throw new RuntimeException(
          String.format(
              "expected '%s' to fail with an IllegalArgumentException but got %s",
              expression, e.getClass().getSimpleName()));
    }
  }

  @Test
  public void negation5_failure() {
    String expression = "A ^ -(B + C)";
    try {
      tokenize(expression);
      throw new RuntimeException(String.format("expected '%s' to fail tokenization", expression));
    } catch (IllegalArgumentException iae) {
      assertEquals(
          iae.getMessage(),
          "unable to parse expressions of the form '... X ^ -(...'. consider enclosing"
              + " the expression in parentheses");
    } catch (Exception e) {
      throw new RuntimeException(
          String.format(
              "expected '%s' to fail with an IllegalArgumentException but got %s",
              expression, e.getClass().getSimpleName()));
    }
  }

  @Test
  public void quadraticFormula_success() {
    assertEqualContents(
        List.of(
            Operation.LEFT_PARENTHESIS,
            Variable.of("-B"),
            Operation.ADD,
            Operation.LEFT_PARENTHESIS,
            B,
            Operation.EXPONENTIATE,
            new ConstantValue(2),
            Operation.SUBTRACT,
            new ConstantValue(4),
            Operation.MULTIPLY,
            A,
            Operation.MULTIPLY,
            C,
            Operation.RIGHT_PARENTHESIS,
            Operation.EXPONENTIATE,
            new ConstantValue(0.5),
            Operation.RIGHT_PARENTHESIS,
            Operation.DIVIDE,
            Operation.LEFT_PARENTHESIS,
            new ConstantValue(2),
            Operation.MULTIPLY,
            C,
            Operation.RIGHT_PARENTHESIS),
        tokenize("(-B + (B^2 - 4*A*C)^0.5) / (2*C)"));
  }

  @Test
  public void variables_postFix_success() {
    assertEqualContents(List.of(A), toPostfix(tokenize("A")));
  }

  @Test
  public void basicOps_postFix_success() {
    assertEqualContents(List.of(A, B, Operation.ADD), toPostfix(tokenize("A + B")));
    assertEqualContents(List.of(A, B, Operation.SUBTRACT), toPostfix(tokenize("A - B")));
    assertEqualContents(List.of(A, B, Operation.MULTIPLY), toPostfix(tokenize("A * B")));
    assertEqualContents(List.of(A, B, Operation.DIVIDE), toPostfix(tokenize("A / B")));
    assertEqualContents(List.of(A, B, Operation.EXPONENTIATE), toPostfix(tokenize("A ^ B")));
  }

  @Test
  public void addition2_postFix_success() {
    assertEqualContents(
        List.of(Variable.of("A"), ONE, Operation.ADD), toPostfix(tokenize("A + 1")));
  }

  @Test
  public void addition3_postFix_success() {
    assertEqualContents(List.of(A, ZERO, Operation.ADD), toPostfix(tokenize("A + 0.0")));
  }

  @Test
  public void addition4_postFix_success() {
    assertEqualContents(List.of(A, EULERS_NUMBER, Operation.ADD), toPostfix(tokenize("A + @e")));
  }

  @Test
  public void parenthesis1_postFix_success() {
    assertEqualContents(List.of(A), toPostfix(tokenize("(A)")));
  }

  @Test
  public void parenthesis2_postFix_success() {
    assertEqualContents(
        List.of(A, B, C, Operation.ADD, Operation.MULTIPLY), toPostfix(tokenize("A * (B + C)")));
  }

  @Test
  public void parenthesis3_postFix_success() {
    assertEqualContents(
        List.of(A, B, C, Operation.ADD, Operation.SUBTRACT), toPostfix(tokenize("A - (B + C)")));
  }

  @Test
  public void negation1_postFix_success() {
    assertEqualContents(List.of(Variable.of("-A")), toPostfix(tokenize("-A")));
  }

  @Test
  public void negation2_postFix_success() {
    assertEqualContents(
        List.of(A, Variable.of("-B"), Operation.ADD), toPostfix(tokenize("A + -B")));
  }

  @Test
  public void negation3_postFix_success() {
    assertEqualContents(
        List.of(
            A, new ConstantValue(-1), B, C, Operation.ADD, Operation.MULTIPLY, Operation.MULTIPLY),
        toPostfix(tokenize("A * -(B + C)")));
  }

  @Test
  public void quadraticFormula_postFix_success() {
    assertEqualContents(
        List.of(
            Variable.of("-B"),
            B,
            new ConstantValue(2),
            Operation.EXPONENTIATE,
            new ConstantValue(4),
            A,
            C,
            Operation.MULTIPLY,
            Operation.MULTIPLY,
            Operation.SUBTRACT,
            new ConstantValue(0.5),
            Operation.EXPONENTIATE,
            Operation.ADD,
            new ConstantValue(2),
            C,
            Operation.MULTIPLY,
            Operation.DIVIDE),
        toPostfix(tokenize("(-B + (B^2 - 4*A*C)^0.5) / (2*C)")));
  }

  private static void assertEqualContents(List<Token> expected, List<Token> actual) {
    int expectedCount = Math.max(expected.size(), actual.size());
    for (int i = 0; i < expectedCount; i++) {
      assertEquals(
          String.format(
              "expected:%s but was:%s for token %d in (%s, %s)",
              expected.get(i), actual.get(i), i, expected, actual),
          expected.get(i),
          actual.get(i));
      if (expected.get(i) instanceof Variable) {
        Variable expectedVariable = (Variable) expected.get(i);
        Variable actualVariable = (Variable) actual.get(i);
        assertEquals(expectedVariable.positive, actualVariable.positive);
      } else if (expected.get(i) instanceof NamedConstant) {
        NamedConstant expectedConstant = (NamedConstant) expected.get(i);
        NamedConstant actualConstant = (NamedConstant) actual.get(i);
        assertEquals(expectedConstant.positive, actualConstant.positive);
      }
    }
  }
}

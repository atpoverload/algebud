package algebud.token;

/** A {@link Token} enum for arithmetic operations. */
// TODO(timur): this only supports binary operations
public enum Operation implements Token {
  LEFT_PARENTHESIS("("),
  RIGHT_PARENTHESIS(")"),
  EXPONENTIATE("^"),
  DIVIDE("/"),
  MULTIPLY("*"),
  SUBTRACT("-"),
  ADD("+");

  private final String token;

  private Operation(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return token;
  }

  public static boolean isOperation(char token) {
    switch (token) {
      case '(':
      case ')':
      case '^':
      case '/':
      case '*':
      case '-':
      case '+':
        return true;
      default:
        return false;
    }
  }

  public static boolean isOperation(String token) {
    switch (token) {
      case "(":
      case ")":
      case "^":
      case "/":
      case "*":
      case "-":
      case "+":
        return true;
      default:
        return false;
    }
  }

  public static Token parseOperation(char token) {
    switch (token) {
      case '(':
        return Operation.LEFT_PARENTHESIS;
      case ')':
        return Operation.RIGHT_PARENTHESIS;
      case '^':
        return Operation.EXPONENTIATE;
      case '*':
        return Operation.MULTIPLY;
      case '/':
        return Operation.DIVIDE;
      case '+':
        return Operation.ADD;
      case '-':
        return Operation.SUBTRACT;
      default:
        throw new IllegalArgumentException(
            String.format("expected one of ()^*/+- but got '%s'", token));
    }
  }

  public static Token parseOperation(String token) {
    switch (token) {
      case "(":
        return Operation.LEFT_PARENTHESIS;
      case ")":
        return Operation.RIGHT_PARENTHESIS;
      case "^":
        return Operation.EXPONENTIATE;
      case "*":
        return Operation.MULTIPLY;
      case "/":
        return Operation.DIVIDE;
      case "+":
        return Operation.ADD;
      case "-":
        return Operation.SUBTRACT;
      default:
        throw new IllegalArgumentException(
            String.format("expected one of ()^*/+- but got '%s'", token));
    }
  }
}

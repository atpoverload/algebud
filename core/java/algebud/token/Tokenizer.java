package algebud.token;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/** A tokenizer that formats a string into infix tokens. */
public final class Tokenizer {
  private static final Token NEGATIVE_ONE = new ConstantValue(-1);
  private static final Pattern DECIMAL_PATTERN = Pattern.compile("-?[0-9]+(.[0-9]*)?");
  private static final Pattern VARIABLE_PATTERN = Pattern.compile("-?@?[A-Za-z]+(_[A-Za-z0-9]+)*");

  private static boolean isVariableCharacter(char token) {
    boolean isUpper = 'A' <= token && token <= 'Z';
    boolean isLower = 'a' <= token && token <= 'z';
    boolean isDigit = '0' <= token && token <= '9';
    return isUpper || isLower || isDigit || token == '_' || token == '.' || token == '@';
  }

  private static Token parseToken(String token) {
    if (Operation.isOperation(token)) {
      return Operation.parseOperation(token);
    } else if (DECIMAL_PATTERN.matcher(token).matches()) {
      return new ConstantValue(Double.parseDouble(token));
    } else if (VARIABLE_PATTERN.matcher(token).matches()) {
      if (token.contains("@")) {
        return NamedConstant.of(token);
      } else {
        return Variable.of(token);
      }
    } else {
      throw new IllegalArgumentException(String.format("unable to find a token for %s", token));
    }
  }

  /** Parses a string into infix tokens. The following rules are also enforced:
   * 
   *  - spaces are not tokenized. for example, "A B" will be parsed as the token Variable.of("AB")
   *  - if a negative sign follows an operator, it is assumed the next tokens are either a name or a right parens.
   *   -- if it's a name, the negative sign is added to it and will be part of the variable
   *   -- if it's a parens, the parens body is multiplied by -1. this will throw if the preceeding operation is division.
   *  - a '@' is considered the beginning of a {@link NamedConstant}. if a non-variable follows, this will throw.
   */
  // TODO(timur): this should perform validation so that bad tokens are reported
  // TODO(timur): it would be nice to include a snippet of where tokenization failed
  // TODO(timur): maybe we should just make it postfix here instead of doing two passes
  public static ArrayList<Token> tokenize(String expression) {
    ArrayList<Token> tokens = new ArrayList<>();
    Optional<String> variable = Optional.empty();
    for (char token : expression.toCharArray()) {
      // TODO(timur): there's a better way to organize this block
      switch (token) {
        case '-':
          // check if this is potentially the start of a new variable
          if (variable.isEmpty()) {
            variable = Optional.of("-");
            break;
          } else if (variable.orElse("").equals("-")) {
            break;
          } else if (variable.isPresent()
              || tokens.get(tokens.size() - 1).equals(Operation.RIGHT_PARENTHESIS)) {
            variable.ifPresent(t -> tokens.add(parseToken(t)));
            variable = Optional.empty();
            tokens.add(Operation.parseOperation(token));
            break;
          } else {
            throw new IllegalArgumentException(
                String.format(
                    "bad sequence for '%s' and '%s'", tokens.get(tokens.size() - 1), token));
          }
        case '(':
          // TODO(timur): this will fail for the sequence '...X / -(...' and . we could move the
          // negative
          // above the divider automatically, but we may have to check preceeding tokens
          // TODO(timur): this will fail for the sequence '...X ^ -(...' and . we could wrap the
          // body in parens, but we have to catch the right paren
          if (variable.orElse("").equals("-")) {
            if (tokens.get(tokens.size() - 1).equals(Operation.DIVIDE)) {
              throw new IllegalArgumentException(
                  "unable to parse expressions of the form '... X / -(...'. consider moving the"
                      + " negative sign above the divider");
            } else if (tokens.get(tokens.size() - 1).equals(Operation.EXPONENTIATE)) {
              throw new IllegalArgumentException(
                  "unable to parse expressions of the form '... X ^ -(...'. consider enclosing"
                      + " the expression in parentheses");
            }
            // push -1 * infront of the parens
            // TODO(timur): does this add unnecessary work?
            tokens.add(NEGATIVE_ONE);
            tokens.add(Operation.MULTIPLY);
            tokens.add(Operation.LEFT_PARENTHESIS);
            variable = Optional.empty();
            break;
          } else if (tokens.isEmpty()
              || tokens.get(tokens.size() - 1).getClass().equals(Operation.class)) {
            variable.ifPresent(t -> tokens.add(parseToken(t)));
            variable = Optional.empty();
            tokens.add(Operation.LEFT_PARENTHESIS);
            break;
          }
        case '+':
        case ')':
        case '*':
        case '/':
        case '^':
          // we've hit a terminal, so we need to push whatever we were holding
          if (!variable.orElse("-").equals("-")
              || tokens.get(tokens.size() - 1).equals(Operation.RIGHT_PARENTHESIS)) {
            variable.ifPresent(t -> tokens.add(parseToken(t)));
            variable = Optional.empty();
            tokens.add(Operation.parseOperation(token));
          } else {
            if (variable.isPresent()) {
              throw new IllegalArgumentException(
                  String.format(
                      "bad sequence for '%s' '%s' '%s'",
                      tokens.get(tokens.size() - 1), variable.get(), token));
            } else {
              throw new IllegalArgumentException(
                  String.format(
                      "bad sequence for '%s' '%s'", tokens.get(tokens.size() - 1), token));
            }
          }
        case ' ':
          break;
        default:
          if (isVariableCharacter(token)) {
            variable = Optional.of(variable.orElse("") + Character.toString(token));
          } else {
            throw new IllegalArgumentException(
                String.format("unable to find a token for %s", token));
          }
      }
    }
    variable.ifPresent(t -> tokens.add(parseToken(t)));
    return tokens;
  }

  /**
   * Parses a list of tokens into postfix notation using the shunting-yard algorithm
   * (https://en.wikipedia.org/wiki/Shunting_yard_algorithm).
   */
  public static ArrayList<Token> toPostfix(List<Token> infixTokens) {
    ArrayList<Token> postfixTokens = new ArrayList<>();
    ArrayList<Operation> operators = new ArrayList<>();
    while (!infixTokens.isEmpty()) {
      Token token = infixTokens.remove(0);
      if (token instanceof Operation) {
        Operation operation = (Operation) token;
        switch (operation) {
          case RIGHT_PARENTHESIS:
            while (!operators.isEmpty()
                && operators.get(operators.size() - 1) != Operation.LEFT_PARENTHESIS) {
              postfixTokens.add(operators.remove(operators.size() - 1));
            }
            if (operators.isEmpty()) {
              throw new IllegalArgumentException("detected a ')' with no matching '('");
            }
            operators.remove(operators.size() - 1);
            break;
          default:
            while (!operators.isEmpty()
                && operators.get(operators.size() - 1) != Operation.LEFT_PARENTHESIS
                && operators.get(operators.size() - 1).compareTo(operation) < 0) {
              postfixTokens.add(operators.remove(operators.size() - 1));
            }
          case LEFT_PARENTHESIS:
            operators.add(operation);
        }
      } else {
        postfixTokens.add(token);
      }
    }
    while (!operators.isEmpty()) {
      postfixTokens.add(operators.remove(operators.size() - 1));
    }

    return postfixTokens;
  }
}

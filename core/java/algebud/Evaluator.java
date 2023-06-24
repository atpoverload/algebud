package algebud;

import algebud.token.ConstantValue;
import algebud.token.NamedConstant;
import algebud.token.Operation;
import algebud.token.Token;
import algebud.token.Variable;
import java.util.List;
import java.util.Map;

/**
 * An evaluator to compute the value of a list of postfix tokens given user-provided variable
 * values.
 */
public final class Evaluator {
  // TODO(timur): should we have synonyms?
  private static final Map<NamedConstant, Double> CONSTANTS =
      Map.of(NamedConstant.of("@e"), Math.E, NamedConstant.of("@pi"), Math.PI);

  private static double retrieveValue(Token token, Map<Variable, Double> variables) {
    if (token instanceof Variable) {
      Variable variable = (Variable) token;
      if (variables.containsKey(variable)) {
        return (variable.positive ? 1 : -1) * variables.get(variable);
      } else {
        throw new IllegalArgumentException(String.format("variable '%s' not found", variable));
      }
    } else if (token instanceof NamedConstant) {
      NamedConstant constant = (NamedConstant) token;
      if (CONSTANTS.containsKey(constant)) {
        return (constant.positive ? 1 : -1) * CONSTANTS.get(constant);
      } else {
        throw new IllegalArgumentException(String.format("constant '%s' not found", constant));
      }
    } else if (token instanceof ConstantValue) {
      return ((ConstantValue) token).value;
    } else {
      throw new IllegalArgumentException(
          String.format("could not retrieve value for token '%s'", token));
    }
  }

  private static double execute(Operation operation, double first, double second) {
    switch (operation) {
      case EXPONENTIATE:
        return Math.pow(first, second);
      case DIVIDE:
        return first / second;
      case MULTIPLY:
        return first * second;
      case SUBTRACT:
        return first - second;
      case ADD:
        return first + second;
      default:
        throw new IllegalArgumentException(
            String.format("cannot execute operation '%s'", operation));
    }
  }

  /**
   * Evaluates a list of {@link Tokens} that are assumed to be in postfix notation. Value for
   * variables are expected to be provided by the user.
   */
  public static double evaluate(List<Token> postfixTokens, Map<Variable, Double> variables) {
    double[] buffer = new double[postfixTokens.size()];
    int cursor = 0;
    for (Token token : postfixTokens) {
      if (token instanceof Operation) {
        if (cursor < 2) {
          throw new IllegalArgumentException(
              String.format(
                  "'%s' was found but %d token%s available",
                  token, cursor, cursor == 0 ? "s" : ""));
        }
        // we have an operator, so we need to consume the two values it is being applied to
        double second = buffer[--cursor];
        double first = buffer[--cursor];
        buffer[cursor++] = execute((Operation) token, first, second);
      } else {
        buffer[cursor++] = retrieveValue(token, variables);
      }
    }
    return buffer[0];
  }
}

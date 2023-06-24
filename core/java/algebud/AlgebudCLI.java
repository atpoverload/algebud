package algebud;

import static java.util.stream.Collectors.toMap;

import algebud.token.Token;
import algebud.token.Tokenizer;
import algebud.token.Variable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Command-line tool to run the {@link Tokenizer} and {@Evaluator} on a user-provided sequence. */
// TODO(timur): we should probably use a logger
public class AlgebudCLI {
  /** Parses two kinds of variables from the system properties:
   *  -> -Dalgebud.variable.A=1 -Dalgebud.variable.B=1
   *  -> -Dalgebud.variables=A:1,B:1,...
   * If duplicates are found, this will throw.
  */
  // TODO(timur): what a mess! i'm not really sure how to improve this, i'd like --option flags
  private static Map<Variable, Double> getVariables() {
    Map<Variable, Double> singleVariables =
        System.getProperties().entrySet().stream()
            .filter(variable -> ((String) variable.getKey()).contains("algebud.variable."))
            .collect(
                toMap(
                    variable -> Variable.of(((String) variable.getKey()).split("\\.")[2]),
                    variable -> Double.parseDouble(((String) variable.getValue()))));
    Map<Variable, Double> multiVariables = new HashMap<>();
    if (!System.getProperty("algebud.variables", "").isBlank()) {
      multiVariables =
          Arrays.stream(System.getProperty("algebud.variables", "").split(","))
              .collect(
                  toMap(
                      variable -> Variable.of(variable.split(":")[0]),
                      variable -> Double.parseDouble(variable.split(":")[1])));
    }
    HashMap<Variable, Double> variables = new HashMap<>();
    for (Variable variable : multiVariables.keySet()) {
      if (singleVariables.keySet().contains(variable)) {
        throw new IllegalArgumentException(
            String.format(
                "multiple values for variable '%s' found (%s, %s)",
                variable, singleVariables.get(variable), multiVariables.get(variable)));
      } else {
        variables.put(variable, multiVariables.get(variable));
      }
    }
    for (Variable variable : singleVariables.keySet()) {
      variables.put(variable, singleVariables.get(variable));
    }
    return variables;
  }

  public static void main(String[] args) {
    String expression = String.join(" ", args);
    boolean verbose = Boolean.parseBoolean(System.getProperty("algebud.verbose", "false"));
    if (verbose) {
      System.out.println(expression);
    }

    List<Token> tokens = Tokenizer.tokenize(expression);
    if (verbose) {
      System.out.println("infix tokenized: " + tokens);
    }

    tokens = Tokenizer.toPostfix(tokens);
    if (verbose) {
      System.out.println("postfix tokenized: " + tokens);
    }

    // TODO(timur): how could we support ranges for variables?
    Map<Variable, Double> variables = getVariables();
    double result = Evaluator.evaluate(tokens, variables);
    System.out.println(String.format("%s where %s = %f", expression, variables, result));
  }
}

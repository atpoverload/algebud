package algebud.token;

/** A {@link Token} whose value is provided by the user. */
public final class Variable implements Token {
  public static final Variable of(String token) {
    if (token.charAt(0) != '-') {
      return new Variable(token, true, token);
    } else {
      return new Variable(token.substring(1), false, token);
    }
  }

  public final String name;
  public final boolean positive;
  private final String rawToken;

  private Variable(String name, boolean positive, String rawToken) {
    this.name = name;
    this.positive = positive;
    this.rawToken = rawToken;
  }

  @Override
  public String toString() {
    return rawToken;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Variable) {
      Variable other = (Variable) o;
      return this.name.equals(other.name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}

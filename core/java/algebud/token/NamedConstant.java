package algebud.token;

/** A {@link Token} whose value is provided by the system. */
public final class NamedConstant implements Constant {
  public static final NamedConstant of(String token) {
    if (token.charAt(0) != '-') {
      return new NamedConstant(token.substring(1), true, token);
    } else {
      return new NamedConstant(token.substring(2), false, token);
    }
  }

  public final String name;
  public final boolean positive;
  private final String rawToken;

  private NamedConstant(String name, boolean positive, String rawToken) {
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
    if (o instanceof NamedConstant) {
      NamedConstant other = (NamedConstant) o;
      return this.name.equals(other.name);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}

package algebud.token;

/** A {@link Token} which has a user-defined value before evaluation. */
// TODO(timur): it would be nice to handle other types
public final class ConstantValue implements Constant {
  public final double value;

  public ConstantValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return Double.toString(value);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ConstantValue) {
      return this.value == ((ConstantValue) o).value;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Double.hashCode(this.value);
  }
}

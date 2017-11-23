package cz.cvut.fjfi.ksi.dsj.trialbot.model;

public class Position {
  private final int x;
  private final int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Position translate(Direction dir) {
    return new Position(x + dir.getDx(), y + dir.getDy());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Position)) return false;

    Position position = (Position) o;

    if (x != position.x) return false;
    return y == position.y;

  }

  @Override
  public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }
}

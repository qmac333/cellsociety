package cellsociety.logic.grid;

/**
 * A data type to keep track of the coordinates of any given cell.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public record Coordinate(int r, int c) {

  /**
   * @see Object#equals(Object)
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate oCoordinate = (Coordinate) o;
    return this == o || (this.r == oCoordinate.r && this.c == oCoordinate.c);
  }

  /**
   * @see Object#hashCode()
   */
  @Override
  public int hashCode() {
    return (r * 31) + c;
  }
}

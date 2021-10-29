package cellsociety.logic.grid;

public record Coordinate(int r, int c) {

  //Overrides the equals method to state that 2 coordinates are equal if they share the same values.
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate oCoordinate = (Coordinate) o;
    return this == o || (this.r == oCoordinate.r && this.c == oCoordinate.c);
  }

  //Overrides the hashCode method to create a new hashcode based on the coordinates.
  //This will help distribute our coordinates in the map.
  @Override
  public int hashCode() {
    return (r * 31) + c;
  }
}

package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public abstract class ShapeManager {

  public List<Coordinate> getAllNeighbors (Coordinate cellCoordinates) {
    HashSet<Coordinate> myNeighbors = new HashSet<>();
    myNeighbors.addAll(getAllNeighborsUp(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsDown(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsRight(cellCoordinates));
    return (new LinkedList<>(myNeighbors));
  }

  public List<Coordinate> getNeighborsUL(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsUp(cellCoordinates);
    HashSet<Coordinate> myNeighborsLeft = new HashSet<>(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsLeft.contains(e));
    return (myNeighbors);
  }

  public List<Coordinate> getNeighborsDL(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsDown(cellCoordinates);
    HashSet<Coordinate> myNeighborsLeft = new HashSet<>(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsLeft.contains(e));
    return (myNeighbors);
  }

  public List<Coordinate> getNeighborsUR(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsUp(cellCoordinates);
    HashSet<Coordinate> myNeighborsRight = new HashSet<>(getAllNeighborsRight(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsRight.contains(e));
    return (myNeighbors);
  }

  public List<Coordinate> getNeighborsDR(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsDown(cellCoordinates);
    HashSet<Coordinate> myNeighborsRight = new HashSet<>(getAllNeighborsRight(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsRight.contains(e));
    return (myNeighbors);
  }

  public abstract List<Coordinate> getNeighborsLeft(Coordinate cellCoordinates);

  public abstract List<Coordinate> getNeighborsRight(Coordinate cellCoordinates);

  public abstract List<Coordinate> getNeighborsUp(Coordinate cellCoordinates);

  public abstract List<Coordinate> getNeighborsDown(Coordinate cellCoordinates);

  public abstract List<Coordinate> getAllNeighborsLeft(Coordinate cellCoordinates);

  public abstract List<Coordinate> getAllNeighborsRight(Coordinate cellCoordinates);

  public abstract List<Coordinate> getAllNeighborsUp(Coordinate cellCoordinates);

  public abstract List<Coordinate> getAllNeighborsDown(Coordinate cellCoordinates);

}

package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * An abstract parent class of the various ShapeManager classes. Has the ability
 * to find specific types of neighbors for each shape.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public abstract class ShapeManager {

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors in every direction.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of all neighboring cells.
   */
  public List<Coordinate> getAllNeighbors (Coordinate cellCoordinates) {
    HashSet<Coordinate> myNeighbors = new HashSet<>();
    myNeighbors.addAll(getAllNeighborsUp(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsDown(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.addAll(getAllNeighborsRight(cellCoordinates));
    return (new LinkedList<>(myNeighbors));
  }

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors up and to the left.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public List<Coordinate> getNeighborsUL(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsUp(cellCoordinates);
    HashSet<Coordinate> myNeighborsLeft = new HashSet<>(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsLeft.contains(e));
    return (myNeighbors);
  }

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors down and to the left.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public List<Coordinate> getNeighborsDL(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsDown(cellCoordinates);
    HashSet<Coordinate> myNeighborsLeft = new HashSet<>(getAllNeighborsLeft(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsLeft.contains(e));
    return (myNeighbors);
  }

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors up and to the right.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public List<Coordinate> getNeighborsUR(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsUp(cellCoordinates);
    HashSet<Coordinate> myNeighborsRight = new HashSet<>(getAllNeighborsRight(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsRight.contains(e));
    return (myNeighbors);
  }

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors down and to the right.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public List<Coordinate> getNeighborsDR(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = getAllNeighborsDown(cellCoordinates);
    HashSet<Coordinate> myNeighborsRight = new HashSet<>(getAllNeighborsRight(cellCoordinates));
    myNeighbors.removeIf(e->!myNeighborsRight.contains(e));
    return (myNeighbors);
  }

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors to the left.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getNeighborsLeft(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors to the right.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getNeighborsRight(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors above it.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getNeighborsUp(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of its
   * neighbors below it.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getNeighborsDown(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of all
   * the neighbors to its left.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getAllNeighborsLeft(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of all
   * the neighbors to its right.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getAllNeighborsRight(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of all
   * the neighbors above it.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getAllNeighborsUp(Coordinate cellCoordinates);

  /**
   * Given the coordinates of a cell, returns a list of the coordinates of all
   * the neighbors below it.
   *
   * @param cellCoordinates the coordinates of the cell whose neighbors you want to return.
   * @return a list of the coordinates of the neighboring cells.
   */
  public abstract List<Coordinate> getAllNeighborsDown(Coordinate cellCoordinates);

}

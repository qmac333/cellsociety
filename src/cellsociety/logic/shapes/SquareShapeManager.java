package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;
import java.util.LinkedList;
import java.util.List;

/**
 * A shape manager class to find the neighboring coordinates of a square-based grid.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public class SquareShapeManager extends ShapeManager {


  public SquareShapeManager(){

  }
  /**
   * @see ShapeManager#getNeighborsLeft(Coordinate)
   */
  @Override
  public List<Coordinate> getNeighborsLeft(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()-1));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getNeighborsRight(Coordinate)
   */
  @Override
  public List<Coordinate> getNeighborsRight(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()+1));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getNeighborsUp(Coordinate)
   */
  @Override
  public List<Coordinate> getNeighborsUp(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getNeighborsDown(Coordinate)
   */
  @Override
  public List<Coordinate> getNeighborsDown(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getAllNeighborsLeft(Coordinate)
   */
  @Override
  public List<Coordinate> getAllNeighborsLeft(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()-1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()-1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()-1));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getAllNeighborsRight(Coordinate)
   */
  @Override
  public List<Coordinate> getAllNeighborsRight(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r(),cellCoordinates.c()+1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()+1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()+1));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getAllNeighborsUp(Coordinate)
   */
  @Override
  public List<Coordinate> getAllNeighborsUp(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()-1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()));
    myNeighbors.add(new Coordinate(cellCoordinates.r()-1,cellCoordinates.c()+1));
    return myNeighbors;
  }

  /**
   * @see ShapeManager#getAllNeighborsDown(Coordinate)
   */
  @Override
  public List<Coordinate> getAllNeighborsDown(Coordinate cellCoordinates) {
    List<Coordinate> myNeighbors = new LinkedList<>();
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()-1));
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()));
    myNeighbors.add(new Coordinate(cellCoordinates.r()+1,cellCoordinates.c()+1));
    return myNeighbors;
  }
}

package cellsociety.logic.grid;

import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import java.util.LinkedList;
import java.util.List;

/**
 * An implementation of the Grid class in which cells are bounded by the width and height of the grid.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public class FiniteGrid extends Grid {

  /**
   * Constructs a new grid with a specified array of states
   *
   * @param states
   * @param shapeManager
   */
  public FiniteGrid(int[][] states, ShapeManager shapeManager) {
    super(states, shapeManager);
  }

  /**
   * @see Grid#getNeighbors(Cell, NeighborhoodPattern)
   */
  @Override
  public List<Cell> getNeighbors(Cell cell, NeighborhoodPattern myPattern) {
    List<Coordinate> potentialNeighbors = generateNeighborCoordinates(cell, myPattern);
    potentialNeighbors.removeIf(coordinate -> coordinate.r() < 0 || coordinate.r() > getHeight()
        || coordinate.c() < 0 || coordinate.c() > getWidth());
    List<Cell> myNeighbors = new LinkedList<>();
    for (Coordinate coord: potentialNeighbors) {
      myNeighbors.add(getCell(coord.r(), coord.c()));
    }
    return myNeighbors;
  }
}

package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;
import java.util.List;

/**
 * An abstract parent class for the NeighborhoodPatterns.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public abstract class NeighborhoodPattern {

  /**
   * Returns a list of coordinates of the neighboring cells.
   *
   * @param myCenter the cell whose neighbors
   * @param myShape
   * @return
   */
  public abstract List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape);

}

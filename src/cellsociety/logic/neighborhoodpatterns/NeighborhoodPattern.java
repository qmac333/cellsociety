package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;
import java.util.List;

public abstract class NeighborhoodPattern {

  public NeighborhoodPattern(){

  }

  public abstract List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape);

}

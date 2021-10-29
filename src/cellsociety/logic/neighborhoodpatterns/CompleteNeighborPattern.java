package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;

import java.util.ArrayList;
import java.util.List;

public class CompleteNeighborPattern extends NeighborhoodPattern{
    @Override
    public List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape) {
        return myShape.getAllNeighbors(myCenter);
    }
}

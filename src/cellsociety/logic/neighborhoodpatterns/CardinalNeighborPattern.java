package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;

import java.util.ArrayList;
import java.util.List;

public class CardinalNeighborPattern extends NeighborhoodPattern{
    @Override
    public List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape) {
        ArrayList<Coordinate> result = new ArrayList<>();
        result.addAll(myShape.getAllNeighborsUp(myCenter));
        result.addAll(myShape.getAllNeighborsLeft(myCenter));
        result.addAll(myShape.getAllNeighborsDown(myCenter));
        result.addAll(myShape.getAllNeighborsRight(myCenter));
        return result;
    }
}

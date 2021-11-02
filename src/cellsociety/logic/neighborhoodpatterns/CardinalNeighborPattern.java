package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;

import java.util.ArrayList;
import java.util.List;

public class CardinalNeighborPattern extends NeighborhoodPattern{
    public CardinalNeighborPattern() {
    }

    @Override
    public List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape) {
        ArrayList<Coordinate> result = new ArrayList<>();
        result.addAll(myShape.getNeighborsUp(myCenter));
        result.addAll(myShape.getNeighborsLeft(myCenter));
        result.addAll(myShape.getNeighborsDown(myCenter));
        result.addAll(myShape.getNeighborsRight(myCenter));
        return result;
    }
}

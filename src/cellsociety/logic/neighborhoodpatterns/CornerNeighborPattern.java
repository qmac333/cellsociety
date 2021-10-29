package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.shapes.ShapeManager;

import java.util.ArrayList;
import java.util.List;

public class CornerNeighborPattern extends NeighborhoodPattern{

    @Override
    public List<Coordinate> getNeighborhoodGroup(Coordinate myCenter, ShapeManager myShape) {
        ArrayList<Coordinate> result = new ArrayList<>();
        result.addAll(myShape.getNeighborsUL(myCenter));
        result.addAll(myShape.getNeighborsUR(myCenter));
        result.addAll(myShape.getNeighborsDL(myCenter));
        result.addAll(myShape.getNeighborsDR(myCenter));
        return result;
    }
}

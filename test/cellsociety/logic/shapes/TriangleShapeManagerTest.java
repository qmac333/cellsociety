package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TriangleShapeManagerTest  extends ShapeManagerTest{

    @BeforeEach
    public void initialize() {
        myShapeManager = new TriangleShapeManager();
    }

    Coordinate cl = new Coordinate(0,-1);
    Coordinate cr = new Coordinate(0,1);
    Coordinate cu = new Coordinate(-1,0);
    Coordinate cd = new Coordinate(1,0);


    @Test
    void testCardinalDirections() {
        assertUpNeighbors(cu);
        assertDownNeighbors(cd);
        assertLeftNeighbors(cl);
        assertRightNeighbors(cr);
    }



}
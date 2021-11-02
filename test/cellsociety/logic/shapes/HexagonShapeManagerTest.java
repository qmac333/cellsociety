package cellsociety.logic.shapes;

import cellsociety.logic.grid.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexagonShapeManagerTest extends ShapeManagerTest {

    Coordinate cl = new Coordinate(0,-1);
    Coordinate cr = new Coordinate(0,1);
    Coordinate cu1 = new Coordinate(-1,0);
    Coordinate cu2 = new Coordinate(-1,1);
    Coordinate cd1 = new Coordinate(1,0);
    Coordinate cd2 = new Coordinate(1,1);

    @Test
    void testCardinalDirections() {
        assertUpNeighbors(cu1, cu2);
        assertDownNeighbors(cd1, cd2);
        assertLeftNeighbors(cl);
        assertRightNeighbors(cr);
    }
    


    @Test
    void testAllDirections() {
        assertUpNeighbors(cu1, cu2);
        assertDownNeighbors(cd1, cd2);
        assertLeftNeighbors(cl);
        assertRightNeighbors(cr);
    }

    @BeforeEach
    public void initialize() {
        myShapeManager = new HexagonShapeManager();
    }
}
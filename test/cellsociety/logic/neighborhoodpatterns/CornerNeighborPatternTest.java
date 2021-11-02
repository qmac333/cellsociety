package cellsociety.logic.neighborhoodpatterns;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.FiniteGrid;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.grid.InfiniteGrid;
import cellsociety.logic.shapes.HexagonShapeManager;
import cellsociety.logic.shapes.SquareShapeManager;
import cellsociety.logic.shapes.TriangleShapeManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CornerNeighborPatternTest {

    Grid squareGrid;
    Grid triangleGrid;
    Grid hexagonGrid;
    CornerNeighborPattern cnp = new CornerNeighborPattern();

    @BeforeEach
    void initialize() {
        int[][] myArray =
                {   {0,0,0},
                        {0,1,0},
                        {0,0,0}
                };

        this.squareGrid = new InfiniteGrid(myArray, new SquareShapeManager());
        triangleGrid = new InfiniteGrid(myArray, new TriangleShapeManager());
        hexagonGrid = new InfiniteGrid(myArray, new HexagonShapeManager());
    }

    @Test
    void getNeighborhoodGroupSquareTest() {
        List<Cell> neighbors = squareGrid.getNeighbors(squareGrid.getCell(1, 1), cnp);

        assertEquals(squareGrid.getCell(0,0).getCoordinates(), neighbors.get(0).getCoordinates());
        assertEquals(squareGrid.getCell(0,2).getCoordinates(), neighbors.get(1).getCoordinates());
        assertEquals(squareGrid.getCell(2,0).getCoordinates(), neighbors.get(2).getCoordinates());
        assertEquals(squareGrid.getCell(2,2).getCoordinates(), neighbors.get(3).getCoordinates());
    }

    @Test
    void getNeighborhoodGroupTriangleTest() {
        List<Cell> neighbors = triangleGrid.getNeighbors(triangleGrid.getCell(1, 1), cnp);
        neighbors.removeIf(e -> e == null);
        assertEquals(triangleGrid.getCell(0,3).getCoordinates(), neighbors.get(0).getCoordinates());
        assertEquals(triangleGrid.getCell(2,0).getCoordinates(), neighbors.get(1).getCoordinates());
        assertEquals(triangleGrid.getCell(2,1).getCoordinates(), neighbors.get(2).getCoordinates());
        assertEquals(triangleGrid.getCell(2,1).getCoordinates(), neighbors.get(3).getCoordinates());
        assertEquals(triangleGrid.getCell(2,2).getCoordinates(), neighbors.get(4).getCoordinates());
    }

    @Test
    void getNeighborhoodGroupHexagonTest() {
        List<Cell> neighbors = hexagonGrid.getNeighbors(hexagonGrid.getCell(1, 1), cnp);

        assertEquals(hexagonGrid.getCell(0,1).getCoordinates(), neighbors.get(0).getCoordinates());
        assertEquals(hexagonGrid.getCell(0,2).getCoordinates(), neighbors.get(1).getCoordinates());
        assertEquals(hexagonGrid.getCell(2,1).getCoordinates(), neighbors.get(2).getCoordinates());
        assertEquals(hexagonGrid.getCell(2,2).getCoordinates(), neighbors.get(3).getCoordinates());
    }
}
package cellsociety.logic.grid;

import cellsociety.logic.neighborhoodpatterns.CardinalNeighborPattern;
import cellsociety.logic.shapes.SquareShapeManager;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiniteGridTest extends GridTest {

  @BeforeEach
  void initialize() {
    int[][] myArray =
        {   {0,0,0},
            {0,1,0},
            {0,0,0}
        };

    this.myGrid = new FiniteGrid(myArray, new SquareShapeManager());
  }

  @Test
  void testGetCell() {
    assertEquals(myGrid.getCell(0,0), new Cell(0,0,0));
    assertNotEquals(myGrid.getCell(0,0), new Cell(0,0,1));
    assertEquals(myGrid.getCell(1,1), new Cell(1,1,1));
    assertNotEquals(myGrid.getCell(1,1), new Cell(0,0,1));
  }

  @Test
  void testGetNeighbors() {
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(0).getCoordinates(), new Cell(0,1,0).getCoordinates());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(1).getCoordinates(), new Cell(1,0,0).getCoordinates());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(2).getCoordinates(), new Cell(2,1,0).getCoordinates());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(3).getCoordinates(), new Cell(1,2,0).getCoordinates());

    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(0).getCurrentState(), new Cell(0,1,0).getCurrentState());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(1).getCurrentState(), new Cell(1,0,0).getCurrentState());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(2).getCurrentState(), new Cell(2,1,0).getCurrentState());
    assertEquals(myGrid.getNeighbors(myGrid.getCell(1,1), new CardinalNeighborPattern()).get(3).getCurrentState(), new Cell(1,2,0).getCurrentState());
  }
}

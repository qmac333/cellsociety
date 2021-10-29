package cellsociety.logic.grid;

import cellsociety.logic.shapes.SquareShapeManager;

import static org.junit.jupiter.api.Assertions.*;
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


}

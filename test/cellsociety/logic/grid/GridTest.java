package cellsociety.logic.grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class GridTest {

  Grid myGrid;

  abstract void initialize();

  abstract void testGetCell();

}

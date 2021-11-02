package cellsociety.logic.shapes;


import cellsociety.logic.grid.Coordinate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SquareShapeManagerTest extends ShapeManagerTest {

  @BeforeEach
  public void initialize() {
    myShapeManager = new SquareShapeManager();
  }

  Coordinate cl = new Coordinate(0,-1);
  Coordinate cr = new Coordinate(0,1);
  Coordinate cu = new Coordinate(-1,0);
  Coordinate cd = new Coordinate(1,0);

  Coordinate cul = new Coordinate(-1,-1);
  Coordinate cur = new Coordinate(-1,1);
  Coordinate cdl = new Coordinate(1,-1);
  Coordinate cdr = new Coordinate(1,1);

  @Test
  void testCardinalDirections() {
    assertUpNeighbors(cu);
    assertDownNeighbors(cd);
    assertLeftNeighbors(cl);
    assertRightNeighbors(cr);
  }

  @Test
  void testCorners() {
    assertUpRightNeighbors(cur);
    assertDownRightNeighbors(cdr);
    assertUpLeftNeighbors(cul);
    assertDownLeftNeighbors(cdl);
  }

  @Test
  void testAllDirections() {
    assertAllLeftNeighbors(cl,cul,cdl);
    assertAllRightNeighbors(cr,cur,cdr);
    assertAllUpNeighbors(cul,cu,cur);
    assertAllDownNeighbors(cd,cdr,cdl);
  }

}

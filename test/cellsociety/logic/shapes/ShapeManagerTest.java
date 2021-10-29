package cellsociety.logic.shapes;

import static org.junit.jupiter.api.Assertions.*;

import cellsociety.logic.grid.Coordinate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class ShapeManagerTest {

  ShapeManager myShapeManager;
  Coordinate c0 = new Coordinate(0,0);

  public abstract void initialize();

  void assertLeftNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsLeft(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertRightNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsRight(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertUpNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsUp(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertDownNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsDown(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertDownLeftNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsDL(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertUpLeftNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsUL(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertDownRightNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsDR(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertUpRightNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getNeighborsUR(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertAllLeftNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getAllNeighborsLeft(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertAllRightNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getAllNeighborsRight(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertAllUpNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getAllNeighborsUp(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertAllDownNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getAllNeighborsDown(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  void assertAllNeighbors(Coordinate... expectedCoordinates) {
    List myNeighbors = myShapeManager.getAllNeighbors(c0);
    assertListContainsOnly(myNeighbors, expectedCoordinates);
  }

  public static boolean assertListContainsOnly(List myList, Object[] objects) {
    for (Object o: objects) {
      if (!myList.contains(o)) {
        assertTrue(false);
        return false;
      }
      myList.remove(o);
    }
    assertTrue(myList.size()==0);
    return (myList.size() == 0);
  }
}

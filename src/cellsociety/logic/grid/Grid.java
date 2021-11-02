package cellsociety.logic.grid;

import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * An abstract implementation of a grid. It contains a map of cells and
 * the ability to find neighboring cells. Also has methods to get, move, or
 * set cells.
 *
 * @author William Convertino
 *
 * @since 0.0.3
 */
public abstract class Grid {

  //A map of the cells in the grid.
  private Map<Coordinate, Cell> myCells;

  //The shape manager of the grid - determines how the cell's interact.
  private ShapeManager myShapeManager;


  //The size of the grid.
  private int height, width;

  /**
   * Constructs a new grid with a specified array of states
   *
   * @param states
   * @param shapeManager
   */
  public Grid (int[][] states, ShapeManager shapeManager) {
    this.myShapeManager = shapeManager;
    initializeGrid(states);
  }

  //Initializes the grid using a 2D array of states.
  private void initializeGrid(int[][] states) {
    this.myCells = new HashMap<>();
    this.height = states.length;
    this.width = states[0].length;
    for (int r = 0; r < states.length; r++) {
      for (int c = 0; c < states[0].length; c++) {
        addCellIfAbsent(r,c,states[r][c]);
      }
    }
  }

  /**
   * Adds a cell to the grid at the specified location with the specified
   * state, if it does not already exist.
   *
   * @param r the row coordinate of the new cell.
   * @param c the column coordinate of the new cell.
   * @param state the state of the new cell.
   */
  public void addCellIfAbsent(int r, int c, int state) {
    Coordinate newCell = new Coordinate(r,c);
    for(Coordinate cell : myCells.keySet()){
      if(cell == newCell){
        return;
      }
    }
    myCells.putIfAbsent(new Coordinate(r,c), new Cell(r,c,state));
  }

  /**
   * Returns the value at the specified coordinates, or null if
   * there is no cell at the coordinates.
   *
   * @param r the row coordinate of the cell to get.
   * @param c the column coordinate of the cell to get.
   * @return the cell at coordinates (r,c), or null if it does not exist.
   */
  public Cell getCell(int r, int c) {
    Coordinate cellCoords = new Coordinate(r,c);
    return myCells.getOrDefault(cellCoords, null);
  }

  /**
   * Given a cell and a neighborhood pattern, returns a list of the neighboring cells.
   *
   * @param cell the cell whose neighbors we find.
   * @param myPattern the neighborhood pattern that dictates what is considered a neighbord.
   * @return a list of the neighboring cells.
   */
  public abstract List<Cell> getNeighbors(Cell cell, NeighborhoodPattern myPattern);

  /**
   * Generates a list of coordinates for the potentially neighboring cells.
   *
   * @param c the cell whose neighbors' coordinates are generated.
   * @param myPattern the neighborhood pattern to use in order to find the neighbors.
   * @return a list of coordinates for the potentially neighboring cells.
   */
  protected List<Coordinate> generateNeighborCoordinates(Cell c, NeighborhoodPattern myPattern) {
    return myPattern.getNeighborhoodGroup(c.getCoordinates(),myShapeManager);
  }

  /**
   *  Moves a cell to the specified location.
   *
   * @param sourceCell the cell to move.
   * @param r the row to move the cell.
   * @param c the column to move the cell.
   */
  public void moveCellTo(Cell sourceCell, int r, int c){
    moveCellTo(sourceCell, getCell(r,c));
  }

  /**
   *  Moves a cell's states to another cell.
   *
   * @param sourceCell the cell to move.
   * @param targetCell the destination where the cell should move.
   */
  public void moveCellTo(Cell sourceCell, Cell targetCell){
    targetCell.setNextState(sourceCell.getCurrentState());
    targetCell.setNextAltStates(sourceCell.getAltStates());
    sourceCell.setNextState(0);
    sourceCell.setNextAltStates(new HashMap<>());
  }

  public void changeCell(Cell c, int state, Map<String, Integer> currentAltStates){
    myCells.get(c.getCoordinates()).setNextState(state);
    myCells.get(c.getCoordinates()).setNextAltStates(currentAltStates);
  }

  public void changeCell(Coordinate c, int state){
    myCells.get(c).setNextState(state);
  }

  /**
   * Updates each cell in the grid. This sets their current
   * states equal to their next states, and sets their
   * next states equal to 0.
   */
  public void updateCells() {
    for (Coordinate coord: myCells.keySet()) {
      Cell c = myCells.get(coord);
      c.setCurrentState(c.getNextState());
      c.setAltStates(c.getNextAltStates());
      c.setNextState(0);
      c.setNextAltStates(new HashMap<>());
    }
  }

  public void updateCell(Coordinate c) {
      Cell cell = myCells.get(c);
      cell.setCurrentState(cell.getNextState());
      cell.setAltStates(cell.getNextAltStates());
      cell.setNextState(0);
      cell.setNextAltStates(new HashMap<>());
  }

  public List<Cell> getCellsToUpdate() {

    return new LinkedList<>(myCells.values());

  }

  public void setMyCells(Map<Coordinate, Cell> myCells) {
    this.myCells = myCells;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

}
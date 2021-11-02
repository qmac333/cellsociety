package cellsociety.logic.grid;

import cellsociety.logic.grid.Coordinate;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * A datatype to represent a cell in a grid. This class keeps track of a
 * cell's row, column, current and next state, and current and next alternate state.
 *
 * @author William Convertino
 * @author Quentin MacFarlane
 *
 * @since 0.0.3
 */
public class Cell {

  //The row and column coordinate of the current cell.
  private Coordinate myCoordinates;

  //The current state of the cell.
  private int currentState;

  //The next state of the cell.
  private int nextState;

  //Map of alternate states of a cell, useful for a variety of abstract implementations.
  private Map<String, Integer> altStates;

  //The next alternate states of a cell
  private Map<String, Integer> nextAltStates;

  /**
   * Constructs a new Cell at the specified coordinates with the given
   * state and alternate state.
   *
   * @param r the row of the cell.
   * @param c the column of the cell.
   * @param state the current state of the cell.
   */
  public Cell(int r, int c, int state) {
    this.myCoordinates = new Coordinate(r,c);
    this.currentState = state;
    this.nextState = 0;
  }

  public Coordinate getCoordinates() {
    return myCoordinates;
  }

  /**
   * Returns the current state of the cell.
   *
   * @return the current state of the cell.
   */
  public int getCurrentState() {
    return this.currentState;
  }

  /**
   * Sets the value of this cell's state to the specified state.
   *
   * @param state the new state of this cell.
   */
  public void setCurrentState(int state) {
    this.currentState = state;
  }

  /**
   * Returns the next state of the cell.
   *
   * @return the next state of the cell.
   */
  public int getNextState() {
    return this.nextState;
  }

  /**
   * Sets the value of this cell's next state to the specified state.
   *
   * @param state the next state of this cell.
   */
  public void setNextState(int state) {
    this.nextState = state;
  }

  public Map<String, Integer> getAltStates() {
    return altStates;
  }

  public void setAltStates(Map<String, Integer> newStates) {
    altStates = newStates;
  }

  public void addState(String state, int value) {
    if (this.altStates == null) {
      altStates = new HashMap<>();
    }
    altStates.put(state, value);
  }

  public Map<String, Integer> getNextAltStates() {
    return nextAltStates;
  }

  public void setNextAltStates(Map<String, Integer> updatedStates) {
    nextAltStates = updatedStates;
  }

  public void addToNextState(String state, int value) {
    if (this.nextAltStates == null) {
      nextAltStates = new HashMap<>();
    }
    nextAltStates.put(state, value);
  }


  /**
   * @see Object#equals(Object)  
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Cell)) {
      return false;
    }
    Cell c = (Cell) o;
    boolean altStatesMatch = checkAltStatesEqual(this.getAltStates(), c.getAltStates());
    return this == o || (this.currentState == c.currentState && altStatesMatch
        && this.getCoordinates().equals(c.getCoordinates()));
  }

  private boolean checkAltStatesEqual(Map<String, Integer> altStatesOne, Map<String, Integer> altStatesTwo) {
    if (altStatesOne == null && altStatesTwo == null) {
      return true;
    } else if (altStatesOne == null || altStatesTwo == null) {
      return false;
    } else if (altStatesOne.keySet().equals(altStatesTwo.keySet()) &&
            new ArrayList<>(altStatesOne.values()).equals(new ArrayList<>(altStatesTwo.values()))) {
      return true;
    }
    return false;
  }

}

package cellsociety.controller;

import cellsociety.logic.Grid;
import cellsociety.logic.Logic;
import org.apache.commons.logging.Log;

/**
 * Controls the Logic portion of Cell Society, allowing the different
 * algorithms to be loaded, unloaded, and updated. Also communicates
 * the current grid state of the loaded algorithm.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class LogicController {


  //The current algorithm with which the grid should be updated.
  private Logic currentAlgorithm;

  //The current program grid to display to the user.
  private Grid gridToDisplay;

  /**
   * Constructs a new LogicController.
   *
   */
  public LogicController () {

  }

  /**
   * Returns the current grid state of the currently loaded
   * algorithm.
   *
   * @return the grid state of  the currently loaded algorithm.
   */
  public Grid getActiveGrid() {
    return gridToDisplay;
  }


}

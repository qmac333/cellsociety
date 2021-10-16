package cellsociety.controller;

import cellsociety.display.Display;
import cellsociety.io.IOHandler;
import cellsociety.logic.Logic;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * Organizes and runs the different parts of the Cell Society
 * program, allowing the Display, Logic, IO handling, and Error
 * handling to communicate with each other.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class Controller {

  //The current algorithm with which the grid should be updated.
  private Logic myAlgorithm;

  //The current display class of our program.
  private Display myDisplay;

  //The current IOHandler of our program.
  private IOHandler myIOHandler;

  /**
   *
   */
  public Controller(Scene myScene) {
    myDisplay = new Display(Color.color(100, 0, 100), 10);
  }


  /**
   *
   */
  public void update() {

  }

}

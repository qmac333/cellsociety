package cellsociety.controller;

import cellsociety.display.Display;
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

  //The current display class of our program.
  private Display myDisplay;

  /**
   * Creates a Controller to run a new instance of Cell Society,
   * using the passed scene to initialize its display.
   *
   * @param myScene the scene to which the display elements should be added.
   */
  public Controller(Scene myScene) {
    myDisplay = new Display(Color.color(100, 0, 100), 10);
  }


  /**
   *  Executes every program tick to allow the Logic and Display to update.
   */
  public void update() {

    if ((myGrid = myLogicController.getActiveGrid()) == null) {
      //myDisplay.updateScene(myGrid);
    }

  }

}

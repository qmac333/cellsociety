package cellsociety.controller;

import cellsociety.display.Display;
import cellsociety.logic.Grid;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Organizes and runs the different parts of the Cell Society
 * program, allowing the Display, Simulation, IO handling, and Error
 * handling to communicate with each other.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class Controller {

  //The current display class of our program.
  private Display myDisplay;

  //The current algorithm with which the grid should be updated.
  private LogicController myLogicController;

  //The current grid that should be shown by the Display.
  private Grid myGrid;

  /**
   * Creates a Controller to run a new instance of Cell Society,
   * using the passed scene to initialize its display.
   *
   * @param myStage the stage on which the display elements should be added.
   */
  public Controller(Stage myStage) {
    this.myLogicController = new LogicController();
    initializeDisplay(myStage);
  }

  //Initializes the display components.
  private void initializeDisplay (Stage myStage) {
    myDisplay = new Display(myStage, Color.color(.50,.50,.80));
    myDisplay.addFileChoser(new EventHandler() {
      @Override
      public void handle(Event event) {

      }
    });
    //myDisplay.addPlayButton();
    //myDisplay.addPauseButton();

  }

  /**
   *  Executes every program tick to allow the Simulation and Display to update.
   */
  public void update() {

    myLogicController.update();
    if ((myGrid = myLogicController.getActiveGrid()) != null) {
      myDisplay.updateScene(myGrid.getCurrentGrid());
    }

  }

}

package cellsociety.controller;

import cellsociety.display.ButtonManager;
import cellsociety.display.Display;
import cellsociety.display.RectangleDisplay;
import cellsociety.io.FileHandler;
import java.io.File;
import java.nio.file.Paths;

import cellsociety.logic.grid.Coordinate;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
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

  //The stage of the JFX application.
  private Stage myStage;

  //The current display class of our program.
  private Display myDisplay;

  //The current algorithm with which the grid_LEGACY should be updated.
  private LogicController myLogicController;

  /**
   * Creates a Controller to run a new instance of Cell Society,
   * using the passed scene to initialize its display.
   *
   * @param myStage the stage on which the display elements should be added.
   */
  public Controller(Stage myStage) {
    this.myLogicController = new LogicController();
    this.myStage = myStage;
    initializeDisplay(myStage);
  }

  //Initializes the display components.
  private void initializeDisplay (Stage myStage) {
    myDisplay = new RectangleDisplay(myStage, Color.color(.50,.50,.80));
    initializeButtons(myDisplay);
  }

  //Initializes all the buttons in the display.
  private void initializeButtons(Display myDisplay) {
    ButtonManager.initializeButtons(myDisplay, myLogicController, this, myStage);
  }

  /**
   * Saves the display's grid_LEGACY to a CVS file.
   */
  public void saveCurrentGrid() {
    FileHandler.saveFile(myLogicController.getActiveGrid(), "data/game_of_life/user_file.csv");
  }

  /**
   * Loads a new simulation using the specified file.
   *
   * @param file the SIM file with the simulation's information.
   */
  public void loadFile(File file) {
    try {
      myLogicController.initializeFromFile(file);
    } catch (Exception e) {
      e.printStackTrace();
      myDisplay.showError(e);
    }
  }

  /**
   *  Executes every program tick to allow the Simulation and Display to update.
   */
  public void update() {

    myLogicController.update();

    if (myLogicController.getActiveGrid() != null){
      myDisplay.updateScene(myLogicController.getActiveGrid().getCellsToUpdate());
    }

  }

}

package cellsociety.controller;

import cellsociety.Main;
import cellsociety.display.*;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.io.FileHandler;
import java.io.File;

import java.util.ArrayList;
import java.util.List;

import cellsociety.logic.grid.Coordinate;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Organizes and runs the different parts of the Cell Society
 * program, allowing the Display, Simulation, IO handling, and Error
 * handling to communicate with each other.
 *
 * @author William Convertino
 * @author Tim Jang
 * @since 0.0.1
 */
public class Controller {

  //The stage of the JFX application.
  private Stage myStage;

  //The current display class of our program.
  private Display myDisplay;

  //The main logic controller.
  private LogicController myLogicController;

  //The current algorithm with which the grid_LEGACY should be updated.
  private List<LogicController> myLogicControllers;


  /**
   * Creates a Controller to run a new instance of Cell Society,
   * using the passed scene to initialize its display.
   *
   * @param myStage the stage on which the display elements should be added.
   */
  public Controller(Stage myStage) {
    this.myStage = myStage;
    this.myLogicControllers = new ArrayList<>();
    this.myLogicController = new LogicController();
    loadNewDisplay(new File("data/game_of_life/default.sim"));
  }

  //Initializes the display components.
  private Display initializeDisplay (String displayType, Stage myStage) throws InvalidSimulationTypeError {
    try {

      Display newDisplay = (Display) Class.forName("cellsociety.display." + displayType + "Display")
          .getConstructor(Stage.class, Color.class)
          .newInstance(myStage, Color.color(.50, .50, .80));
      return newDisplay;
    } catch (Exception e) {
      throw new InvalidSimulationTypeError(displayType);
    }
  }

  //Initializes all the buttons in the display.
  private void initializeButtons(Display display, LogicController logicController) {
    ButtonManager.initializeButtons(display, logicController, this, myStage);


  }

  /**
   * Saves the display's grid to a CVS file.
   */
  public void saveCurrentGrid(LogicController logicController) {
    FileHandler.saveFile(logicController.getActiveGrid(), myStage);
  }

  /**
   * Loads a new simulation using the specified file.
   *
   * @param file the SIM file with the simulation's information.
   */
  public void loadFile(File file, LogicController logicController, Stage stage) {
    try {
      logicController.resetDisplay();
      logicController.initializeFromFile(file);
      logicController.setDisplay(initializeDisplay( logicController.getMetaData().get("Shape"), stage));

    } catch (Exception e) {
      myDisplay.showError(e);
    }
  }

  public void loadNewDisplay(File file) {
    try {
      LogicController newLogicController = new LogicController();
      newLogicController.initializeFromFile(file);
      String shape = newLogicController.getMetaData().get("Shape");
      Stage newStage = new Stage();
      newStage.setScene(new Scene(new Group(), Main.WINDOW_DEFAULT_WIDTH, Main.WINDOW_DEFAULT_HEIGHT));
      newStage.initModality(Modality.WINDOW_MODAL);
      newStage.setTitle(Main.WINDOW_NAME);
      Display newDisplay = initializeDisplay(shape, newStage);
      initializeButtons(newDisplay, newLogicController);
      newLogicController.setDisplay(newDisplay);
      myLogicControllers.add(newLogicController);
      newStage.show();
      //initializeButtons(myDisplay);
    } catch (Exception e) {
      myDisplay.showError(e);
    }
  }

  /**
   *  Executes every program tick to allow the Simulation and Display to update.
   */
  public void update() {
    for (LogicController lc: myLogicControllers) {
      lc.update();
    }

  }

}

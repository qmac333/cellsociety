package cellsociety.controller;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.errors.UnhandledExceptionError;
import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.GameOfLife;
import cellsociety.logic.Grid;
import cellsociety.logic.Simulation;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controls the Simulation portion of Cell Society, allowing the different
 * algorithms to be loaded, unloaded, and updated. Also communicates
 * the current grid state of the loaded algorithm.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class LogicController {

  public static final String TYPE = "Type";
  public static final String INITIAL_STATE = "InitialStates";
  public static final int CYCLE_DELAY = 2;

  private Runnable cycleRunnable;
  private ScheduledExecutorService cycleExecutor;

  //The current algorithm with which the grid should be updated.
  private Simulation currentSimulation;

  //The current program grid to display to the user.
  private Grid gridToDisplay;

  //Keeps track of whether or not the simulation is paused.
  private boolean isPaused;

  /**
   * Constructs a new LogicController.
   */
  public LogicController () {
    initializeCycles(CYCLE_DELAY);
  }

  //Initializes a cycle executor to run the simulation's update method at a specified interval.
  private void initializeCycles(int delay) {
    this.isPaused = true;
    this.cycleRunnable = new Runnable() {
      @Override
      public void run() {
        if (currentSimulation!=null && !isPaused) {
          currentSimulation.update();
        }
      }
    };
    cycleExecutor = Executors.newScheduledThreadPool(1);
    cycleExecutor.scheduleAtFixedRate(cycleRunnable, delay, delay, TimeUnit.SECONDS);
  }

  /**
   * Initializes a new simulation based on a SIM file input.
   *
   * @param filename the name of the SIM file with the initialization configuration data.
   * @throws Exception if the file cannot be found or is improperly formatted.
   */
  public void initializeFromFile (String filename) throws FileNotFoundError, InvalidSimulationTypeError, MissingSimulationArgumentError, UnhandledExceptionError {
    Map<String, String> metadata;
    Grid grid;
    try {
      metadata = SIMFileReader.getMetadataFromFile(filename);
      grid = CSVFileReader.readFile(metadata.get(INITIAL_STATE));
    } catch (FileNotFoundError e) {
      throw e;
    } catch (UnhandledExceptionError e) {
      throw e;
    }
    try {
      currentSimulation = loadLogicClass(grid, metadata);
      gridToDisplay = currentSimulation.getGrid();
    } catch (NoSuchMethodException e) {
      throw new InvalidSimulationTypeError(metadata.get(TYPE));
    } catch (MissingSimulationArgumentError e) {
      throw e;
    } catch (InvocationTargetException|IllegalAccessException e){
      throw new UnhandledExceptionError();
    }

  }

  private Simulation loadLogicClass(Grid grid, Map<String, String> metadata)
      throws NoSuchMethodException, MissingSimulationArgumentError, InvocationTargetException, IllegalAccessException {
    return (Simulation)getClass().getMethod(metadata.get(TYPE), Grid.class, Map.class).invoke(this,grid, metadata);
  }

  private Simulation loadGameOfLife(Grid grid, Map<String, String> metadata) {
    return new GameOfLife(grid, metadata);
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

  public void pauseSimulation() {
    this.isPaused = true;
  }

  public void playSimulation() {
    this.isPaused = false;
  }

  public void update() {

  }

}

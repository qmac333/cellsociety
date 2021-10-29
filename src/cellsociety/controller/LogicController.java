package cellsociety.controller;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidFileFormatError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.errors.UnhandledExceptionError;
import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.simulations.GameOfLife;
import cellsociety.logic.simulations.Simulation;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Controls the Simulation portion of Cell Society, allowing the different
 * algorithms to be loaded, unloaded, and updated. Also communicates
 * the current grid_LEGACY state of the loaded algorithm.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class LogicController {

  public static final ResourceBundle FILE_ARGUMENT_PROPERTIES = ResourceBundle.getBundle("cellsociety.controller.FileArguments");
  public static final String TYPE = FILE_ARGUMENT_PROPERTIES.getString("Type");
  public static final String INITIAL_STATE_FILE =FILE_ARGUMENT_PROPERTIES.getString("InitialStates");
  public static final int DEFAULT_SPEED = 1;

  private Runnable cycleRunnable;
  private ScheduledExecutorService cycleExecutor;

  //The current algorithm with which the grid_LEGACY should be updated.
  private Simulation currentSimulation;

  //Keeps track of whether the simulation is paused.
  private boolean isPaused;

  private int currentSpeed = -1;

  /**
   * Constructs a new LogicController.
   */
  public LogicController () {
    setSpeed(DEFAULT_SPEED);
    pauseSimulation();
  }

  //Initializes a cycle executor to run the simulation's update method at a specified interval.
  private void initializeCycles(int delay) {
    boolean oldPauseState = isPaused;
    this.isPaused = true;
    if (cycleExecutor != null) {
      cycleExecutor.shutdownNow();
    }
    this.cycleRunnable = () -> {
      if (currentSimulation!=null && !isPaused) {
        currentSimulation.update();
      }
    };
    cycleExecutor = Executors.newScheduledThreadPool(1);
    cycleExecutor.scheduleAtFixedRate(cycleRunnable, delay, delay, TimeUnit.MILLISECONDS);
    this.isPaused = oldPauseState;
  }

  /**
   * Initializes a new simulation based on a SIM file input.
   *
   * @param file the SIM file with the initialization configuration data.
   * @throws Exception if the file cannot be found or is improperly formatted.
   */
  public void initializeFromFile (File file)
      throws FileNotFoundError, InvalidSimulationTypeError, MissingSimulationArgumentError, UnhandledExceptionError, InvalidFileFormatError {
    Map<String, String> metadata;
    int[][] grid;
    try {
      metadata = SIMFileReader.getMetadataFromFile(file);
      grid = CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE)));
    } catch (FileNotFoundError | UnhandledExceptionError | InvalidFileFormatError e) {
      throw e;
    }
    try {

      currentSimulation = loadLogicClass(grid,Class.forName("cellsociety.logic.neighborhoodpatterns" + metadata.get("Neighborhood")).getConstructor(), metadata);

    } catch (NoSuchMethodException e) {
      throw new InvalidSimulationTypeError(metadata.get(TYPE));
    } catch (MissingSimulationArgumentError e) {
      throw e;
    } catch (InvocationTargetException|IllegalAccessException e){
      e.printStackTrace();
     // throw new UnhandledExceptionError();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  //Initiates the proper simulation type and loads it into the currentSimulation variable.
  private Simulation loadLogicClass(Grid grid, NeighborhoodPattern np, Map<String, String> metadata)
      throws NoSuchMethodException, MissingSimulationArgumentError, InvocationTargetException, IllegalAccessException {
    return (Simulation)getClass().getMethod(metadata.get(TYPE), int[][].class, Map.class).invoke(this,grid, np, metadata);
    //return GameOfLife(grid_LEGACY, metadata);
  }

  //Returns a new GameOfLife simulation.
  public Simulation GameOfLife(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
    return new GameOfLife(grid, np , metadata);
  }
//
//  //Returns a new ModelOfSegregation simulation.
//  public Simulation ModelOfSegregation(int[][] grid, Map<String, String> metadata) {
//    return new ModelOfSegregation(grid, metadata);
//  }
//
//  //Returns a new Percolation simulation.
//  public Simulation Percolation(int[][] grid, Map<String, String> metadata) {
//    return new Percolation(grid, metadata);
//  }
//
//  //Returns a new FireSpreading simulation.
//  public Simulation FireSpreading(int[][] grid, Map<String, String> metadata) {
//    return new FireSpreading(grid, metadata);
//  }
//
//  //Returns a new WaTorWorld simulation.
//  public Simulation Wator(int[][] grid, Map<String, String> metadata) {
//    return new WaTorWorld(grid, metadata);
//  }
  /**
   * Returns the current grid_LEGACY state of the currently loaded
   * algorithm.
   *
   * @return the grid_LEGACY state of  the currently loaded algorithm.
   */
  public Grid getActiveGrid() {
    if (currentSimulation != null) {
      return currentSimulation.getCurrentGrid();
    }
    return null;
  }

  public void pauseSimulation() {
    this.isPaused = true;
  }

  public void playSimulation() {
    this.isPaused = false;
  }

  public Simulation getCurrentSimulation(){
    return currentSimulation;
  }

  public void update() {

  }

  public void setSpeed(int speed) {
    if (currentSpeed != speed) {
      currentSpeed = speed;
      initializeCycles((5-speed) * 200);
    }

  }

  public int getSimulationDefaultValue(){
    return currentSimulation.getDefaultValue();
  }

}

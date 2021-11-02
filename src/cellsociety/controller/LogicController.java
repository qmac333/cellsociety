package cellsociety.controller;

import cellsociety.display.Display;
import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidFileFormatError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.errors.UnhandledExceptionError;
import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import cellsociety.logic.simulations.*;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.stage.Stage;

/**
 * Controls the Simulation portion of Cell Society, allowing the different
 * algorithms to be loaded, unloaded, and updated. Also communicates
 * the current grid_LEGACY state of the loaded algorithm.
 *
 * @author William Convertino
 * @author Tim Jang
 * @since 0.0.1
 */
public class LogicController {

    public static final ResourceBundle FILE_ARGUMENT_PROPERTIES = ResourceBundle.getBundle("cellsociety.controller.FileArguments");
    public static final String TYPE = FILE_ARGUMENT_PROPERTIES.getString("Type");
    public static final String INITIAL_STATE_FILE = FILE_ARGUMENT_PROPERTIES.getString("InitialStates");
    public static final int DEFAULT_SPEED = 1;

    private Runnable cycleRunnable;
    private ScheduledExecutorService cycleExecutor;
    private Display myDisplay;

    //The current algorithm with which the grid_LEGACY should be updated.
    private Simulation currentSimulation;

    //Keeps track of whether the simulation is paused.
    private boolean isPaused;

    private int currentSpeed = -1;

    /**
     * Constructs a new LogicController.
     */
    public LogicController() {
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
            if (currentSimulation != null && !isPaused) {
              try {
                currentSimulation.update();
              } catch (Exception e) {
                e.printStackTrace();
              }

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
    public void initializeFromFile(File file)
            throws FileNotFoundError, InvalidSimulationTypeError, MissingSimulationArgumentError, UnhandledExceptionError, InvalidFileFormatError, ClassNotFoundException, NoSuchMethodException {
        Map<String, String> metadata = null;
        Grid grid = null;
        try {
            metadata = SIMFileReader.getMetadataFromFile(file);
            Class cls[] = new Class[] { int[][].class, ShapeManager.class };
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE))),
                    sm};
            grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
        } catch (FileNotFoundError | UnhandledExceptionError | InvalidFileFormatError | ClassNotFoundException | NoSuchMethodException e) {
            throw e;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Class cls[] = new Class[] { };
            currentSimulation = loadLogicClass(grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor(cls).newInstance(), metadata);

        } catch (NoSuchMethodException e) {
            throw new InvalidSimulationTypeError(metadata.get(TYPE));
        } catch (MissingSimulationArgumentError e) {
        } catch (InvocationTargetException | IllegalAccessException e) {
            // throw new UnhandledExceptionError();
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        }

    }

    //Initiates the proper simulation type and loads it into the currentSimulation variable.
    private Simulation loadLogicClass(Grid grid, NeighborhoodPattern np, Map<String, String> metadata)
            throws NoSuchMethodException, MissingSimulationArgumentError, InvocationTargetException, IllegalAccessException {
        return (Simulation) getClass().getMethod(metadata.get(TYPE), Grid.class, NeighborhoodPattern.class, Map.class).invoke(this, grid, np, metadata);
        //return GameOfLife(grid_LEGACY, metadata);
    }

    //Returns a new GameOfLife simulation.
    public Simulation GameOfLife(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
        return new GameOfLife(grid, np, metadata);
    }

  //Returns a new ModelOfSegregation simulation.
  public Simulation ModelOfSegregation(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
    return new ModelOfSegregation(grid,np, metadata);
  }

  //Returns a new Percolation simulation.
  public Simulation Percolation(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
    return new Percolation(grid, np, metadata);
  }

  //Returns a new FireSpreading simulation.
  public Simulation FireSpreading(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
    return new FireSpreading(grid, np, metadata);
  }

//  //Returns a new WaTorWorld simulation.
  public Simulation Wator(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) {
    return new WaTorWorld(grid, np, metadata);
  }


  public void setDisplay(Display display) {
      this.myDisplay = display;
  }

  public Stage getStage() {
      return myDisplay.getMyStage();
  }

    /**
     * Returns the current grid_LEGACY state of the currently loaded
     * algorithm.
     *
     * @return the grid_LEGACY state of  the currently loaded algorithm.
     */
    public Grid getActiveGrid() {
        if (currentSimulation != null) {
            return currentSimulation.getGrid();
        }
        return null;
    }

    public void pauseSimulation() {
        this.isPaused = true;
    }

    public void playSimulation() {
        this.isPaused = false;
    }

    public Simulation getCurrentSimulation() {
        return currentSimulation;
    }

    public void update() {
      if (getActiveGrid() != null && getActiveGrid().getCellsToUpdate().size() > 0 ) {
        myDisplay.updateScene(getActiveGrid().getCellsToUpdate());
      }
    }

    public void resetDisplay() {
      myDisplay.resetGrid();
    }

    public void setSpeed(int speed) {
        if (currentSpeed != speed) {
            currentSpeed = speed;
            initializeCycles((5 - speed) * 200);
        }

    }

    public Map<String, String> getMetaData() {
        return currentSimulation.getMetaData();
    }

    public int getSimulationDefaultValue(){
        return currentSimulation.getDefaultValue();
    }
}

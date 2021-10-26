package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;

import cellsociety.logic.grid.Grid;
import cellsociety.logic.simulations.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModelOfSegregation extends Simulation {

  private List<int[]> empty;
  private double satisfactionRate;
  /**
   * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
   * values.
   *
   * @param grid     the starting grid of the simulation.
   * @param metadata the user-specified values used by the simulation.
   * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
   *                                        simulation.
   */
  public ModelOfSegregation(Grid grid, Map<String, String> metadata) throws MissingSimulationArgumentError {
    super(grid, metadata);
    satisfactionRate = Double.parseDouble(metadata.get("SatisfactionRate"));
    empty = findEmptyCells(grid);
    setDefaultValue(1);
  }

  private List findEmptyCells(Grid grid){
    empty = new ArrayList<>();
    for (int i = 0; i < grid.getWidth(); i++) {
      for (int j = 0; j < grid.getHeight(); j++) {
        if (grid.getCell(i, j) == 0) {
          empty.add(new int[]{i, j});
        }
      }
    }
    //randomize the empty points
    Collections.shuffle(empty);
    return empty;
  }

  private void relocateToEmptyCell(int x, int y){
    getGrid().setCell(empty.get(0)[0], empty.get(0)[1], getGrid().getCell(x, y));
    empty.remove(0);
    empty.add(new int[]{x, y});
  }

  @Override
  public void update() {
    for (int x = 0; x < getGrid().getWidth(); x++) {
      for (int y = 0; y < getGrid().getHeight(); y++) {
        if (Collections.frequency(getGrid().getAllNeighbors(x, y), getGrid().getCell(x, y))/8 < satisfactionRate
                && getGrid().getCell(x, y) != 0) {
          relocateToEmptyCell(x, y);
        }
      }
    }
    Collections.shuffle(empty);
    getGrid().updateGrid();
  }
}

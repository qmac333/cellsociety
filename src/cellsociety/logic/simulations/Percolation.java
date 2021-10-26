package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.logic.grid.Grid;

import java.util.Collections;
import java.util.Map;

public class Percolation extends Simulation {

  /**
   * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
   * values.
   *
   * @param grid     the starting grid of the simulation.
   * @param metadata the user-specified values used by the simulation.
   * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
   *                                        simulation.
   */
  public Percolation(Grid grid, Map<String, String> metadata)
      throws MissingSimulationArgumentError {
    super(grid, metadata);
    setDefaultValue(0);
  }

  @Override
  public void update() {
    for (int x = 0; x < getGrid().getWidth(); x++) {
      for (int y = 0; y < getGrid().getHeight(); y++) {
        if (getGrid().getFourNeighbors(x, y).get(0) == 1 && getGrid().getCell(x, y) == 0) {
          getGrid().setCell(x, y, 1);
        } else if (getGrid().getFourNeighbors(x, y).get(0) == -1 && getGrid().getCell(x, y) == 0) {
          getGrid().setCell(x, y, 1);
        } else {
          getGrid().setCell(x, y, getGrid().getCell(x, y));
        }
      }
    }
    getGrid().updateGrid();
  }
}

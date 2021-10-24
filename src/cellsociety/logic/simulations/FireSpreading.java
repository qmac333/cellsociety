package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.logic.grid.Grid;
import java.util.Map;

public class FireSpreading extends Simulation {

  /**
   * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
   * values.
   *
   * @param grid     the starting grid of the simulation.
   * @param metadata the user-specified values used by the simulation.
   * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
   *                                        simulation.
   */
  public FireSpreading(Grid grid, Map<String, String> metadata)
      throws MissingSimulationArgumentError {
    super(grid, metadata);
  }

  @Override
  public void update() {

  }
}
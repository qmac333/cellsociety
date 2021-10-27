package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;

import cellsociety.logic.grid.Cell;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class simulates Schelling's Model of Segregation. For each cell, it will attempt to move
 * to an adjacent empty cell if the ratio of similar to different states among its neighbors is less
 * than the satisfaction rate set by the user.
 *
 * @author William Convertino
 * @since 0.0.2
 */
public class ModelOfSegregation extends Simulation {

  //The ratio at which a cell will not attempt to move.
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
  public ModelOfSegregation(int[][] grid, Map<String, String> metadata)
          throws MissingSimulationArgumentError {
    super(grid, metadata);
    satisfactionRate = Double.parseDouble(metadata.get("SatisfactionRate"));
    setDefaultValue(2);
  }

  /**
   * @see Simulation#updateNextGridFromCell(Cell)
   */
  @Override
  protected void updateNextGridFromCell(Cell cell) {
    List<Cell> neighbors = currentGrid.getNeighbors_Eight(cell);
    neighbors.removeIf(e->e.getState() == 0);

    if(Collections.frequency(neighbors, cell.getState())/neighbors.size() < satisfactionRate && cell.getState() != 0){
      Cell c = currentGrid.getNextEmptyCell();
      if(c != null){
        System.out.println(c.getRow() + " " + c.getColumn() + " " + cell.getState());
        nextGrid.setCellState(c.getRow(),c.getColumn(), cell.getState());
        nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
      }


    }
  }

}

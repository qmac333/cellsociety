//package cellsociety.logic.simulations;
//
//import cellsociety.errors.MissingSimulationArgumentError;
//import cellsociety.logic.grid_LEGACY.Cell;
//
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @author Alexis Cruz-Ayala
// *
// * @since 0.0.2
// */
//public class Percolation extends Simulation {
//
//  /**
//   * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
//   * values.
//   *
//   * @param grid     the starting grid_LEGACY of the simulation.
//   * @param metadata the user-specified values used by the simulation.
//   * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
//   *                                        simulation.
//   */
//  public Percolation(int[][] grid, Map<String, String> metadata)
//          throws MissingSimulationArgumentError {
//    super(grid, metadata);
//    setDefaultValue(0);
//  }
//
//  @Override
//  protected void updateNextGridFromCell(Cell cell) {
//
//    List<Cell> neighbors = currentGrid.getNeighbors_Four(cell);
//
//    if ((neighbors.get(0).getState() == 1 || cell.getRow() == 0 ) && cell.getState() == 0) {
//      nextGrid.setCellState(cell.getRow(), cell.getColumn(), 1);
//    }  else{
//      nextGrid.setCellState(cell.getRow(), cell.getColumn(), cell.getState());
//    }
//
//  }
//
//}

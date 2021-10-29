//package cellsociety.logic.simulations;
//
//import cellsociety.errors.MissingSimulationArgumentError;
//
//import cellsociety.logic.grid_LEGACY.Cell;
//import java.util.Map;
//import java.util.Random;
//
///**
// * This class implements the FireSpreading simulation. For each cell of state 1, it will
// * "catch on fire" (change to state 2) with a probability set by the user for each fire
// * adjacent to it. Each fire will burn out (to state 0) after the cycle is completed.
// *
// * @author William Convertino
// * @since 0.0.2
// */
//public class FireSpreading extends Simulation {
//
//    //The probability of a fire catching.
//    private double probCatch;
//
//    //Our random instance.
//    private Random rand;
//
//    /**
//     * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
//     * values.
//     *
//     * @param grid     the starting grid_LEGACY of the simulation.
//     * @param metadata the user-specified values used by the simulation.
//     * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
//     *                                        simulation.
//     */
//    public FireSpreading(int[][] grid, Map<String, String> metadata)
//            throws MissingSimulationArgumentError {
//        super(grid, metadata);
//        this.probCatch = Double.parseDouble(metadata.get("ProbCatch"));
//        rand = new Random();
//        setDefaultValue(2);
//    }
//
//    /**
//     * @see Simulation#updateNextGridFromCell(Cell)
//     */
//    @Override
//    protected void updateNextGridFromCell(Cell cell) {
//        if (rand.nextDouble() < probCatch * getNumAdjacentFires(cell) && cell.getState() == 1) {
//            nextGrid.setCellState(cell.getRow(), cell.getColumn(), 2);
//        } else if (cell.getState() == 2) {
//            nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
//        } else {
//            nextGrid.setCellState(cell.getRow(), cell.getColumn(), cell.getState());
//        }
//    }
//
//    //Returns the number of adjacent fires to a cell.
//    private int getNumAdjacentFires(Cell cell) {
//        int total = 0;
//        if (currentGrid.getNeighborUp(cell) != null && currentGrid.getNeighborUp(cell).getState() == 2) {
//            total ++;
//        }
//        if (currentGrid.getNeighborDown(cell) != null && currentGrid.getNeighborDown(cell).getState() == 2) {
//            total ++;
//        }
//        if (currentGrid.getNeighborLeft(cell) != null && currentGrid.getNeighborLeft(cell).getState() == 2) {
//            total ++;
//        }
//        if (currentGrid.getNeighborRight(cell) != null && currentGrid.getNeighborRight(cell).getState() == 2) {
//            total ++;
//        }
//        return total;
//    }
//
//}

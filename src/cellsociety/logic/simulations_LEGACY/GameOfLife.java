package cellsociety.logic.simulations;


import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.grid_LEGACY.Cell;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.simulations_LEGACY.Simulation;

import java.util.List;
import java.util.Map;

/**
 * This class simulates the game of life. For each cell, if it has fewer than 2 live neighbors (state 1), then
 * it will die (turn to state 0). If it has 2 or 3 live neighbors, it will keep its state. If it has any more than
 * 3 live neighbors, it will die.
 *
 * @author William Convertino
 * @author Alexis Cruz
 *
 * @since 0.0.1
 */
public class GameOfLife extends Simulation {

    public GameOfLife(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) throws MissingSimulationArgumentError {
        super(grid,np, metadata);
        setDefaultValue(1);
    }

    /**
     * @see Simulation#updateNextGridFromCell(Cell)
     */
    @Override
    protected void updateNextGridFromCell(Cell cell) {

        List<Cell> neighbors = getCurrentGrid().getNeighbors(cell, getNeighborhoodPattern());
        neighbors.removeIf(e->e.getState() == 0);

        if (neighbors.size() == 2) {
            nextGrid.setCellState(cell.getRow(), cell.getColumn(), cell.getState());
        } else if (neighbors.size() == 3) {
            nextGrid.setCellState(cell.getRow(), cell.getColumn(), 1);
        } else {
            nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
        }
    }

}

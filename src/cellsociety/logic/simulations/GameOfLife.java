package cellsociety.logic.simulations;


import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;

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

        List<Cell> neighbors = getGrid().getNeighbors(cell, getNeighborhoodPattern());
        neighbors.removeIf(e->e == null || e.getCurrentState() == 0);

        if (neighbors.size() == 2) {
            getGrid().changeCell(cell, cell.getCurrentState(), cell.getAltStates());
        } else if (neighbors.size() == 3) {
            getGrid().changeCell(cell, 1, cell.getAltStates());
        } else {
            getGrid().changeCell(cell, 0, cell.getAltStates());
        }
    }

}

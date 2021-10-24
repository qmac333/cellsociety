package cellsociety.logic;

import cellsociety.errors.MissingSimulationArgumentError;

import java.util.Collections;
import java.util.Map;

public class WaTorWorld extends Simulation {

    /**
     * Constructs a new Simulation with a specified starting Grid and a Map of simulation-specific data
     * values.
     *
     * @param grid     the starting grid of the simulation.
     * @param metadata the user-specified values used by the simulation.
     * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
     *                                        simulation.
     */
    public WaTorWorld(Grid grid, Map<String, String> metadata)
            throws MissingSimulationArgumentError {
        super(grid, metadata);
    }

    @Override
    public void update() {
        for (int x = 0; x < getGrid().getWidth(); x++) {
            for (int y = 0; y < getGrid().getHeight(); y++) {
                if (Collections.frequency(getGrid().getAllNeighbors(x, y), 1) <= 1) {
                    getGrid().setCell(x, y, 0);
                } else if (Collections.frequency(getGrid().getAllNeighbors(x, y), 1) == 3) {
                    getGrid().setCell(x, y, 1);
                } else if (Collections.frequency(getGrid().getAllNeighbors(x, y), 1) >= 4) {
                    getGrid().setCell(x, y, 0);
                } else {
                    getGrid().setCell(x, y, getGrid().getCell(x, y));
                }
            }
        }
        getGrid().updateGrid();
    }
}

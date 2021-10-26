package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;

import cellsociety.logic.grid.Grid;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class FireSpreading extends Simulation {

    private double probCatch;
    private Random rand;

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
        this.probCatch = Double.parseDouble(metadata.get("ProbCatch"));
        rand = new Random();
        setDefaultValue(2);

    }

    @Override
    public void update() {
        for (int x = 0; x < getGrid().getWidth(); x++) {
            for (int y = 0; y < getGrid().getHeight(); y++) {
                if (rand.nextDouble() < probCatch * Collections.frequency(getGrid().getFourNeighbors(x, y), 2)
                        && getGrid().getCell(x, y) == 1) {
                    getGrid().setCell(x, y, 2);
                } else if(getGrid().getCell(x, y) == 2) {
                    getGrid().setCell(x, y, 0);
                }else {
                    getGrid().setCell(x, y, getGrid().getCell(x, y));
                }
            }
        }
        getGrid().updateGrid();
    }
}

package cellsociety.logic.simulations;

import cellsociety.errors.MissingSimulationArgumentError;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;

import java.util.Objects;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

/**
 * @author Quentin MacFarlane
 *
 * @since 0.0.2
 */
public class WaTorWorld extends Simulation {

    public static final String LIFE = "Life";
    public static final String ENERGY = "Energy";
    public static final int WATER_STATE = 0;
    public static final int FISH_STATE = 1;
    public static final int SHARK_STATE = 2;

    private int initEnergy;
    private int reproductionTime;
    private int energyPerFish;

    /**
     * Constructs a new WaTorWorld Simulation with a specified starting Grid and a Map of simulation-specific data
     * values.
     *
     * @param grid     the starting grid of the simulation.
     * @param metadata the user-specified values used by the simulation.
     * @throws MissingSimulationArgumentError if the metadata is missing a required argument for the
     *                                        simulation.
     */
    public WaTorWorld(Grid grid, NeighborhoodPattern np, Map<String, String> metadata) throws MissingSimulationArgumentError {
        super(grid, np, metadata);
        this.initEnergy = Integer.parseInt(metadata.get("InitialEnergy"));
        this.reproductionTime = Integer.parseInt(metadata.get("ReproductionTime"));
        this.energyPerFish = Integer.parseInt(metadata.get("EnergyPerFish"));
        setDefaultValue(2);
    }

    /**
     * @see Simulation#updateNextGridFromCell(Cell)
     */
    @Override
    protected void updateNextGridFromCell(Cell cell) {
        if (cell.getCurrentState() == WATER_STATE) {
            return;
        } else if (cell.getCurrentState() == FISH_STATE) {
            List<Cell> neighbors = getGrid().getNeighbors(cell, getNeighborhoodPattern());
            neighbors.removeIf(Objects::isNull);
            incrementFishLife(cell);

            neighbors.removeIf(e->e.getCurrentState() == FISH_STATE || e.getCurrentState() == SHARK_STATE);
            moveFish(neighbors, cell);
        } else if (cell.getCurrentState() == SHARK_STATE) {
            incrementSharkLife(cell);

            List<Cell> neighborsFish = getGrid().getNeighbors(cell, getNeighborhoodPattern());
            neighborsFish.removeIf(Objects::isNull);
            neighborsFish.removeIf(e->e.getCurrentState() == WATER_STATE || e.getCurrentState() == SHARK_STATE);

            List<Cell> neighborsEmpty = getGrid().getNeighbors(cell, getNeighborhoodPattern());
            neighborsEmpty.removeIf(Objects::isNull);
            neighborsEmpty.removeIf(e->e.getCurrentState() == FISH_STATE || e.getCurrentState() == SHARK_STATE);

            eatFishOrMove(neighborsFish, neighborsEmpty, cell);
        }
    }

    /**
     * Increments time a fish has been alive, starts at 1 if the fish has just been created
     * @param cell the current fish cell we are iterating over
     */
    private void incrementFishLife(Cell cell) {
        if (cell.getAltStates() == null) {
            cell.addState(LIFE, 1);
        } else {
            cell.addState(LIFE, cell.getAltStates().getOrDefault(LIFE,0) + 1);
        }
    }

    /**
     * Increments time a shark has been alive, starts at 1 and creates energy if the shark has just been created
     * @param cell the current shark cell we are iterating over
     */
    private void incrementSharkLife(Cell cell) {
        if (cell.getAltStates() == null) {
            cell.addState(LIFE, 1);
            cell.addState(ENERGY, initEnergy);
        } else {
            cell.addState(LIFE, cell.getAltStates().getOrDefault(LIFE, 0) + 1);
        }
    }

    /**
     * Moves the fish if there is an empty water spot next to the fish
     * @param neighbors a list of the neighbor cells of the cell we are iterating over
     * @param cell the cell we are iterating over
     */
    private void moveFish(List<Cell> neighbors, Cell cell) {
        boolean reproduce = checkIfReproduce(cell);
        neighbors.removeIf(e->e.getNextState() != WATER_STATE);

        if (neighbors.size() > 0) {
            Collections.shuffle(neighbors);
            getGrid().moveCellTo(cell, neighbors.get(0));
            cell.setAltStates(new HashMap<>());
            reproduceFish(reproduce, cell);
        } else {
            getGrid().changeCell(cell, cell.getCurrentState(), cell.getAltStates());
        }
    }

    /**
     * Checks to see if the current animal is eligible to reproduce or not
     * @param cell current animal cell we are iterating over
     * @return boolean telling program whether to reproduce or not
     */
    private boolean checkIfReproduce(Cell cell) {
        if (cell.getAltStates().get(LIFE) == reproductionTime) {
            return true;
        }
        return false;
    }

    /**
     * Creates a new fish at the current cell's position if the old fish has survived for long enough
     * @param reproduce boolean to tell whether to reproduce or not
     * @param cell the current fish cell we are iterating over
     */
    private void reproduceFish(boolean reproduce, Cell cell) {
        if (reproduce) {
            getGrid().changeCell(cell, FISH_STATE, new HashMap<>());
            cell.addState(LIFE, 1);
            cell.setNextAltStates(cell.getAltStates());
            cell.setAltStates(new HashMap<>());
        }
    }

    /**
     * Creates a new shark at the current cell's position if the old shark has survived for long enough
     * @param reproduce boolean to tell whether to reproduce or not
     * @param cell the current shark cell we are iterating over
     */
    private void reproduceShark(boolean reproduce, Cell cell) {
        if (reproduce) {
            getGrid().changeCell(cell, SHARK_STATE, new HashMap<>());
            cell.addToNextState(LIFE, 1);
            cell.addToNextState(ENERGY, initEnergy);
        }
    }

    /**
     * Shark method to determine whether to eat a fish or move to an empty water spot or to not move at all
     * @param neighborsFish the list of fish cells around the shark cell
     * @param neighborsEmpty the list of water cells around the shark cell
     * @param cell the current shark cell we are iterating over
     */
    private void eatFishOrMove(List<Cell> neighborsFish, List<Cell> neighborsEmpty, Cell cell) {
        boolean reproduce = checkIfReproduce(cell);
        neighborsEmpty.removeIf(e->e.getNextState() != WATER_STATE);

        if (neighborsFish.size() > 0) { // if there is a fish next to the shark
            Collections.shuffle(neighborsFish);

            int prevEnergy = cell.getAltStates().get(ENERGY);
            cell.addState(ENERGY, prevEnergy + energyPerFish - 1);
            getGrid().moveCellTo(cell, neighborsFish.get(0));
            neighborsFish.get(0).setCurrentState(0);
            neighborsFish.get(0).setAltStates(new HashMap<>());
            reproduceShark(reproduce, cell);

        } else if (neighborsEmpty.size() > 0) { // if there is an empty water spot next to the shark
            Collections.shuffle(neighborsEmpty);
            decrementEnergyOnMove(cell, neighborsEmpty);
            reproduceShark(reproduce, cell);

        } else {
            decrementEnergyOnStay(cell);
        }
    }

    /**
     * Reduces the energy of a shark by 1 if it moved
     * @param cell shark cell we are iterating over
     */
    private void decrementEnergyOnMove(Cell cell, List<Cell> neighborsEmpty) {
        int prevEnergy = cell.getAltStates().get(ENERGY);
        cell.addState(ENERGY, prevEnergy - 1);
        if (cell.getAltStates().get(ENERGY) == 0) {
            getGrid().moveCellTo(cell, cell);
        } else {
            getGrid().moveCellTo(cell, neighborsEmpty.get(0));
        }
    }

    /**
     * Reduces the energy of a shark by 1 if it stayed still
     * @param cell shark cell we are iterating over
     */
    private void decrementEnergyOnStay(Cell cell) {
        int prevEnergy = cell.getAltStates().get(ENERGY);
        cell.addState(ENERGY, prevEnergy - 1);
        if (cell.getAltStates().get(ENERGY) == 0) {
            getGrid().moveCellTo(cell, cell);
        } else {
            getGrid().changeCell(cell, cell.getCurrentState(), cell.getAltStates());
        }
    }
}

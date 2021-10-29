//package cellsociety.logic.simulations;
//
//import cellsociety.errors.MissingSimulationArgumentError;
//
//
//import cellsociety.logic.grid_LEGACY.Cell;
//import cellsociety.logic.grid_LEGACY.WaTorCell;
//import cellsociety.logic.grid_LEGACY.*;
//
//import java.util.*;
//
///**
// * @author Quentin MacFarlane
// *
// * @since 0.0.2
// */
//public class WaTorWorld extends Simulation {
//
//    private int initEnergy;
//    private int reproductionTime;
//    private int energyPerFish;
//
//    public WaTorWorld(int[][] grid, Map<String, String> metadata) throws MissingSimulationArgumentError {
//        super(grid, metadata);
//        initEnergy = Integer.parseInt(metadata.get("InitialEnergy"));
//        reproductionTime = Integer.parseInt(metadata.get("ReproductionTime"));
//        energyPerFish = Integer.parseInt(metadata.get("EnergyPerFish"));
//        changeToWatorCells(); // changes the grid_LEGACY to use WaTorCells instead of regular Cells
//    }
//
//    private void changeToWatorCells() {
//        // setting the cells with '1' to a WaTorCell w/ a Fish object and the cells with '2' to a WaTorCell w/ a Shark object
//        for (int r = 0; r < getGrid().getHeight(); r++) {
//            for (int c = 0; c < getGrid().getWidth(); c++) {
//                if (getGrid().getCell(r,c).getState() == 1) { // if the cell's state is a 1 (meaning it's a fish)
//                    getGrid().setCell(r, c, new WaTorCell(r,c,getGrid().getCellState(r,c), new Fish()));
//                }
//                else if (getGrid().getCell(r,c).getState() == 2) { // if the cell's state is a 2 (meaning it's a shark)
//                    getGrid().setCell(r, c, new WaTorCell(r,c,getGrid().getCellState(r,c), new Shark(initEnergy)));
//                }
//            }
//        }
//    }
//
//    @Override
//    protected void updateNextGridFromCell(Cell cell) {
//        List<Cell> neighbors = currentGrid.getNeighbors_Four(cell);
//        List<Cell> neighborsFish = currentGrid.getNeighbors_Four(cell);
//        List<Cell> neighborsEmpty = currentGrid.getNeighbors_Four(cell);
//
//        // if the cell right now is just water, or if the original cell was just water, do nothing
//        if (cell.getState() == 0) {
//            return;
//        } else if (cell.getState() == 1) { // if the cell is a fish
//            // increase the time survived for the fish by 1
//            ((WaTorCell) cell).getAnimal().setTimeSurvived(((WaTorCell) cell).getAnimal().getTimeSurvived() + 1);
//
//            neighbors.removeIf(e->e.getState() == 1 || e.getState() == 2); // find only the empty water around the fish
//            System.out.println("NEIGHBORS");
//            for (Cell c : neighbors) {
//                System.out.println("row: "+c.getRow()+" col: "+c.getColumn());
//            }
//
//            // moves the fish if it can move
//            moveFish(neighbors, cell);
//
//        } else if (cell.getState() == 2) { // if the cell is a shark
//            neighborsFish.removeIf(e->e.getState() == 0 || e.getState() == 2); // find only the fish around the shark
//
//            // if there are no fish around the shark, look for empty water
//            neighborsEmpty.removeIf(e->e.getState() == 1 || e.getState() == 2); // find the empty water around the shark
//
//            // eats a fish, if it can, or moves to empty water spot
//            eatFishOrMove(neighborsFish, neighborsEmpty, cell);
//
//            Shark currentShark = ((Shark) ((WaTorCell) cell).getAnimal());
//
//            // shark loses one unit of energy every tick of the program
//            currentShark.setEnergy(currentShark.getEnergy() - energyPerFish);
//
//            // updates the shark based on how much energy it has
//            checkEnergy(currentShark, neighborsEmpty, cell);
//        }
//    }
//
//    private void moveFish(List<Cell> neighbors, Cell cell) {
//        if (neighbors.size() > 0) { // if there is a (water) spot for the fish to move
//            // first, figure out where to move the fish (random spot from the adjacent water spots)
//            Collections.shuffle(neighbors);
//
//            // check to see if the nextGrid has an empty water spot for the cell we want to move to
//            if (nextGrid.getCell(neighbors.get(0).getRow(), neighbors.get(0).getColumn()).getState() == 0) {
//                // move the fish to the spot identified
//                System.out.println("next row: "+neighbors.get(0).getRow());
//                System.out.println("next col: "+neighbors.get(0).getColumn());
//                nextGrid.setCell(neighbors.get(0).getRow(), neighbors.get(0).getColumn(), ((WaTorCell) cell));
//                // sets the previous spot where the fish just was to empty water
////                nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
//
//                // reproduce a new fish if the fish has survived for "reproductionTime" amount of time
//                if (((WaTorCell) cell).getAnimal().getTimeSurvived() == reproductionTime) {
//                    System.out.println("row of new fish: "+cell.getRow());
//                    System.out.println("col of new fish: "+cell.getColumn());
//
//                    nextGrid.setCell(cell.getRow(), cell.getColumn(),
//                            new WaTorCell(cell.getRow(),cell.getColumn(),
//                                    getGrid().getCellState(cell.getRow(),cell.getColumn()), new Fish()));
//                }
//            }
//        }
//        else { // if the fish cannot move
//            nextGrid.setCell(cell.getRow(), cell.getColumn(), (WaTorCell) cell);
//        }
//    }
//
//    private void eatFishOrMove(List<Cell> neighborsFish, List<Cell> neighborsEmpty, Cell cell) {
//        if (neighborsFish.size() > 0) { // if there is a fish next to the shark
//            // first, figure out which fish to eat
//            Collections.shuffle(neighborsFish);
//
//            // checking to see if a shark has occupied the next spot already, or if the fish already moved away
//            if (nextGrid.getCell(neighborsFish.get(0).getRow(), neighborsFish.get(0).getColumn()).getState() != 2
//                    || nextGrid.getCell(neighborsFish.get(0).getRow(), neighborsFish.get(0).getColumn()).getState() == 0) {
//                // move the shark to the spot identified
//                nextGrid.setCell(neighborsFish.get(0).getRow(), neighborsFish.get(0).getColumn(), ((WaTorCell) cell));
//                // sets the previous spot where the shark just was to empty water
////                nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
//
//                Shark currentShark = ((Shark) ((WaTorCell) cell).getAnimal());
//                // increase the energy for the shark by "energy" amount
//                currentShark.setEnergy(currentShark.getEnergy() + energyPerFish);
//            }
//        }
//        else if (neighborsEmpty.size() > 0) { // if there is an empty water spot next to the shark
//            // first, figure out which water spot to move to
//            Collections.shuffle(neighborsEmpty);
//
//            // check to see if the nextGrid has an empty water spot for the cell we want to move to
//            if (nextGrid.getCell(neighborsFish.get(0).getRow(), neighborsFish.get(0).getColumn()).getState() == 0) {
//                // move the shark to the spot identified
//                nextGrid.setCell(neighborsEmpty.get(0).getRow(), neighborsEmpty.get(0).getColumn(), ((WaTorCell) cell));
//                // sets the previous spot where the shark just was to empty water
////                nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
//            }
//        }
//        else { // if there is no fish AND no empty water for the shark to move (basically the shark is surrounded by 4 sharks)
//            nextGrid.setCell(cell.getRow(), cell.getColumn(), (WaTorCell) cell);
//        }
//    }
//
//    private void checkEnergy(Shark currentShark, List<Cell> neighborsEmpty, Cell cell) {
//        // if the shark ran out of energy and moved, set it to empty water space
//        if (currentShark.getEnergy() == 0 && neighborsEmpty.size() > 0) {
//            nextGrid.setCellState(neighborsEmpty.get(0).getRow(), neighborsEmpty.get(0).getColumn(), 0);
//        }
//        // else if the shark ran out of energy but didn't move, set it to empty water space
//        else if (currentShark.getEnergy() == 0 && neighborsEmpty.isEmpty()) {
//            nextGrid.setCellState(cell.getRow(), cell.getColumn(), 0);
//        }
//        else if (currentShark.getEnergy() != 0){ // if shark didn't run out of energy, increase the time survived by 1
//            // increase the time survived for the shark by 1
//            ((WaTorCell) cell).getAnimal().setTimeSurvived(((WaTorCell) cell).getAnimal().getTimeSurvived() + 1);
//
//            // reproduce a new shark if the shark has survived for "reproductionTime" amount of time
//            if (((WaTorCell) cell).getAnimal().getTimeSurvived() == reproductionTime) {
//                nextGrid.setCell(cell.getRow(), cell.getColumn(),
//                        new WaTorCell(cell.getRow(),cell.getColumn(),
//                                getGrid().getCellState(cell.getRow(),cell.getColumn()), new Shark(initEnergy)));
//            }
//        }
//    }
//
//
//}

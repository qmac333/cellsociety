package cellsociety.logic;


import java.util.Arrays;
import java.util.Collections;

/**
 * The GameOfLife logic class for running the backend logic necessary
 * for the GameOfLife game.
 * @author Alexis Cruz
 */
public class GameOfLife extends Logic {

    public GameOfLife(int width, int height) {
        super(width, height);
    }

    /**
     * the update function to be run every tick of the game
     * checks each cell for its neighbors and changes the cell appropriatly
     */
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

package cellsociety.logic;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class GameOfLife extends Logic {

    public GameOfLife(int width, int height) {
        super(width, height);
    }

    public void update() {
        for (int x = 0; x < getGrid().getWidth(); x++) {
            for (int y = 0; y < getGrid().getHeight(); y++) {
                if (Collections.frequency(Arrays.asList(getGrid().getAllNeighbors(x, y)), 1) <= 1) {
                    getGrid().setCell(x, y, 0);
                } else if (Collections.frequency(Arrays.asList(getGrid().getAllNeighbors(x, y)), 1) == 3) {
                    getGrid().setCell(x, y, 1);
                } else if (Collections.frequency(Arrays.asList(getGrid().getAllNeighbors(x, y)), 1) >= 4) {
                    getGrid().setCell(x, y, 0);
                } else {
                    getGrid().setCell(x, y, getGrid().getCell(x, y));
                }
            }
        }
        getGrid().updateGrid();
    }





}

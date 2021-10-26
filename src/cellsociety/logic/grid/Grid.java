package cellsociety.logic.grid;

import java.util.ArrayList;


/**
 * The Grid class for the grid of cells in each of the games
 * @author Alexis Cruz
 */
public class Grid {
    // the current grid is used for getCell
    private int[][] currentGrid;
    // the future grid is used for setCell
    private int[][] futureGrid;
    private int width;
    private int height;

    //constructor that sets the width and height of the current
    //and previous grid and initializes all values to 0
    public Grid(int width, int height) {
        currentGrid = new int[width][height];
        futureGrid = new int[width][height];
        this.width = width;
        this.height = height;
        currentGrid = zeroGrid();
        futureGrid = zeroGrid();

    }

    //constructor that sets the width and height of the current
    // and previous grid and initializes all values to initialValue
    public Grid(int width, int height, int initialValue) {
        currentGrid = new int[width][height];
        futureGrid = new int[width][height];
        this.width = width;
        this.height = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                currentGrid[i][j] = initialValue;
                futureGrid[i][j] = initialValue;
            }
        }
    }

    /**
     * Gets the value of the cell at (x,y) from the current grid
     * @param x the x position of the desired cell
     * @param y the y position of the desired cell
     * @return the value held within that cell
     */
    public int getCell(int x, int y) {
        return currentGrid[x][y];
    }

    /**
     * sets the value of the cell at (x,y) for the future grid
     * @param x the x position of the desired cell
     * @param y the y position of the desired cell
     * @param newVal the value to be held within that cell
     */
    public void setCell(int x, int y, int newVal) {
        futureGrid[x][y] = newVal;
    }

    /**
     * Gets all 8 neighbors in the cell at (x,y) from the current grid
     * @param x the x position of the desired cell
     * @param y the y position of the desired cell
     * @return the 8 neighbors of the specified cell in an arraylist,
     *          ordered from top left most cell being 0 to bottom right
     *          most cell being 7
     */
    public ArrayList<Integer> getAllNeighbors(int x, int y) {
        ArrayList<Integer> result = new ArrayList();
        for (int i = 0; i < 8; i++) {
            result.add(0);
        }
        if (x == 0) {
            result.set(0, -1);
            result.set(1, -1);
            result.set(2, -1);
        } else {
            if (y == 0) {
                result.set(0, -1);
                result.set(1, currentGrid[x - 1][y]);
                result.set(2, currentGrid[x - 1][y + 1]);
            } else if (y == width - 1) {
                result.set(0, currentGrid[x - 1][y - 1]);
                result.set(1, currentGrid[x - 1][y]);
                result.set(2, -1);
            } else {
                result.set(0, currentGrid[x - 1][y - 1]);
                result.set(1, currentGrid[x - 1][y]);
                result.set(2, currentGrid[x - 1][y + 1]);
            }
        }

        if (x == width - 1) {
            result.set(5, -1);
            result.set(6, -1);
            result.set(7, -1);
        } else {
            if (y == 0) {
                result.set(5, -1);
                result.set(6, currentGrid[x + 1][y]);
                result.set(7, currentGrid[x + 1][y + 1]);
            } else if (y == width - 1) {
                result.set(5, currentGrid[x + 1][y - 1]);
                result.set(6, currentGrid[x + 1][y]);
                result.set(7, -1);
            } else {
                result.set(5, currentGrid[x + 1][y - 1]);
                result.set(6, currentGrid[x + 1][y]);
                result.set(7, currentGrid[x + 1][y + 1]);
            }
        }

        if (y == 0) {
            result.set(3, -1);
            result.set(4, currentGrid[x][y + 1]);
        } else if (y == height - 1) {
            result.set(3, currentGrid[x][y - 1]);
            result.set(4, -1);
        } else {
            result.set(3, currentGrid[x][y - 1]);
            result.set(4, currentGrid[x][y + 1]);
        }


        return result;
    }

    /**
     * gets the four neighbors in the up, down, left, right directions
     * @param x the x position of the desired cell
     * @param y the y position of the desired cell
     * @return the 4 neighbors of the specified cell in an arraylist,
     *         ordered from top left most cell being 0 to bottom right
     *         most cell being 3
     */
    public  ArrayList<Integer> getFourNeighbors(int x, int y) {
        ArrayList<Integer> result = new ArrayList();
        for (int i = 0; i < 4; i++) {
            result.add(0);
        }
        if (x == 0) {
            result.set(0, -1);
            result.set(3, currentGrid[x + 1][y]);
        }else if (x == width - 1) {
            result.set(0, currentGrid[x - 1][y]);
            result.set(3, -1);
        } else{
            result.set(0, currentGrid[x - 1][y]);
            result.set(3, currentGrid[x + 1][y]);
        }

        if(y == 0){
            result.set(1, -1);
            result.set(2, currentGrid[x][y + 1]);
        }else if (y == height - 1) {
            result.set(1, currentGrid[x][y - 1]);
            result.set(2, -1);
        } else{
            result.set(1, currentGrid[x][y - 1]);
            result.set(2, currentGrid[x][y + 1]);
        }

        return result;
    }

    /**
     * sets the current grid to the future grid
     * and zeros the future grid
     */
    public void updateGrid() {
        currentGrid = futureGrid;
        futureGrid = zeroGrid();
    }

    /**
     * returns a 2d array full of 0s
     * @return a 2d array full of 0s
     */
    private int[][] zeroGrid() {
        int[][] result = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = 0;
            }
        }

        return result;
    }

    /**
     * @return the width of the Grid
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the grid
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return gets the current grid
     */
    public int[][] getCurrentGrid(){
        return currentGrid;
    }
}

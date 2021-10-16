package cellsociety.logic;

import java.util.ArrayList;

public class Grid {
    private int[][] currentGrid;
    private int[][] futureGrid;
    private int width;
    private int height;

    //constructor that sets the width and height of the current
    // and previous grid and initializes all values to 0
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

    //gets the value of the cell at (x,y)
    public int getCell(int x, int y) {
        return currentGrid[x][y];
    }

    //sets the value of the cell at (x,y)
    public void setCell(int x, int y, int newVal) {
        futureGrid[x][y] = newVal;
    }

    //gets an array of the values of the 8 neighbors
    //of the cell at (x,y)
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

    //gets an array of the values of the 4 neighbors
    //directly above, below, to the left and to the right
    //of the cell at (x,y)
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

    public void updateGrid() {
        currentGrid = futureGrid;
        futureGrid = zeroGrid();
    }

    private int[][] zeroGrid() {
        int[][] result = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                result[i][j] = 0;
            }
        }

        return result;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

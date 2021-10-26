package cellsociety.logic;

import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.simulations.FireSpreading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FireSpreadingTest {

    private FireSpreading fs;

    @Test
    void simulationOfSingleFireTest() {
        try {
            Grid grid = CSVFileReader.readFile("data/fire_spreading/single_fire.csv");
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/single_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            fs = new FireSpreading(grid, metadata);
            fs.update();
            int x = 0;
            int y = 0;
            assertEquals(1, fs.getGrid().getCell(x, y));
            x = 4;
            y = 4;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 3;
            y = 4;
            assertEquals(2, fs.getGrid().getCell(x, y));
            x = 5;
            y = 4;
            assertEquals(2, fs.getGrid().getCell(x, y));
            x = 4;
            y = 3;
            assertEquals(2, fs.getGrid().getCell(x, y));
            x = 4;
            y = 5;
            assertEquals(2, fs.getGrid().getCell(x, y));
        } catch (Exception e) {
            fail("File Not Found");
        }

    }


    @Test
    void simulationOfDiagonalFireTest() {
        try {
            Grid grid = CSVFileReader.readFile("data/fire_spreading/diagonal_fire.csv");
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/diagonal_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            fs = new FireSpreading(grid, metadata);
            fs.update();
            int x = 0;
            int y = 0;
            assertEquals(1, fs.getGrid().getCell(x, y));
            x = 0;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 9;
            y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 8;
            y = 0;
            assertEquals(2, fs.getGrid().getCell(x, y));
            x = 0;
            y = 8;
            assertEquals(2, fs.getGrid().getCell(x, y));
        } catch (Exception e) {
            fail("File Not Found");
        }

    }

    @Test
    void simulationOfRingOfFireTest() {
        try {
            Grid grid = CSVFileReader.readFile("data/fire_spreading/ring_of_fire.csv");
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/ring_of_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            fs = new FireSpreading(grid, metadata);
            fs.update();
            int x = 0;
            int y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 0;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 9;
            y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 9;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y));
            x = 1;
            y = 1;
            assertEquals(2, fs.getGrid().getCell(x, y));
            x = 8;
            y = 8;
            assertEquals(2, fs.getGrid().getCell(x, y));
        } catch (Exception e) {
            fail("File Not Found");
        }

    }
}
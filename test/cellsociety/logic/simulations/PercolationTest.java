//package cellsociety.logic.simulations;
//
//import cellsociety.io.CSVFileReader;
//import cellsociety.io.SIMFileReader;
//import cellsociety.logic.grid_LEGACY.Grid;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PercolationTest {
//    private Percolation p;
//    @Test
//    void simplePipeTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/percolation/simple_pipe.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/simple_pipe.sim"));
//            p = new Percolation(grid.getCellStates(), metadata);
//            p.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(2, p.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//    @Test
//    void longPipeTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/percolation/long_pipe.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/long_pipe.sim"));
//            p = new Percolation(grid.getCellStates(), metadata);
//            p.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//    @Test
//    void volcanoTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/percolation/volcano.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/volcano.sim"));
//            p = new Percolation(grid.getCellStates(), metadata);
//            p.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, p.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 5;
//            assertEquals(2, p.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//}
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
//class WaTorWorldTest {
//    private WaTorWorld ww;
//    @Test
//    void simplePipeTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/wator/random_assortment.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/random_assortment.sim"));
//            ww = new WaTorWorld(grid.getCellStates(), metadata);
//            ww.update();
//            int x = 0;
//            int y = 0;
//            System.out.println(grid);
//            assertEquals(2, ww.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//    @Test
//    void longPipeTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/wator/single_fish.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/single_fish.sim"));
//            ww = new WaTorWorld(grid.getCellStates(), metadata);
//            ww.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//    @Test
//    void volcanoTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/wator/random_assortment.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/random_assortment.sim"));
//            ww = new WaTorWorld(grid.getCellStates(), metadata);
//            ww.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, ww.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 5;
//            assertEquals(2, ww.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//}
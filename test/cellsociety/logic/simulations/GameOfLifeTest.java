//package cellsociety.logic.simulations;
//
//import cellsociety.errors.FileNotFoundError;
//import cellsociety.errors.InvalidFileFormatError;
//import cellsociety.errors.UnhandledExceptionError;
//import cellsociety.io.CSVFileReader;
//import cellsociety.io.SIMFileReader;
//import cellsociety.logic.grid_LEGACY.Grid;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class GameOfLifeTest {
//    private GameOfLife gol;
//
//    @Test
//    void simulationOfBoatTest() throws FileNotFoundError, UnhandledExceptionError {
//
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/game_of_life/boat.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/boat.sim"));
//            gol = new GameOfLife(grid.getCellStates(), metadata);
//            gol.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 0;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//
//    @Test
//    void simulationOfLightWeightShipTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/game_of_life/light_weight_ship.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/light_weight_ship.sim"));
//            gol = new GameOfLife(grid.getCellStates(), metadata);
//            gol.update();
//            int x = 0;
//            int y = 5;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 4;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 3;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 2;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 1;
//            assertEquals(0, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 5;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 5;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 3;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//        } catch (FileNotFoundException | UnhandledExceptionError | InvalidFileFormatError e) {
//            fail();
//        }
//
//    }
//
//    @Test
//    void simulationOfToadTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/game_of_life/oscillator_toad.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/oscillator_toad.sim"));
//            gol = new GameOfLife(grid.getCellStates(), metadata);
//            gol.update();
//            int x = 2;
//            int y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 3;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 3;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 4;
//            y = 2;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 4;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//    }
//
//    @Test
//    void simulationOfStillLifeBlockTest() {
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/game_of_life/still_life_square.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/still_life_square.sim"));
//            gol = new GameOfLife(grid.getCellStates(), metadata);
//            gol.update();
//            int x = 1;
//            int y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 2;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 1;
//            assertEquals(1, gol.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 0;
//            assertEquals(0, gol.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//    }
//
//}

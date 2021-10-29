//package cellsociety.io;
//
//import cellsociety.errors.InvalidFileFormatError;
//import cellsociety.logic.grid_LEGACY.Grid;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class FileHandlerTest {
//
//    @Test
//    void saveGridToCsv() throws IOException, InvalidFileFormatError {
//        Grid grid = new Grid(CSVFileReader.readFile(new File("./data/game_of_life/still_life_square.csv")));
//        assertNotEquals(null, grid);
//
//        String fileName = "testExample.csv";
//        FileHandler.saveFile(grid.getCellStates(), fileName);
//
//        assertTrue(new File("./data/game_of_life/testExample.csv").isFile());
//    }
//}

package cellsociety.io;

import cellsociety.errors.UnhandledExceptionError;
import cellsociety.logic.grid.Grid;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void saveGridToCsv() throws IOException, UnhandledExceptionError {
        Grid grid = CSVFileReader.readFile("./data/game_of_life/still_life_square.csv");
        assertNotEquals(null, grid);

        String fileName = "testExample.csv";
        FileHandler.saveFile(grid, fileName);

        assertTrue(new File("./data/game_of_life/testExample.csv").isFile());
    }
}

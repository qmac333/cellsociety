package cellsociety.io;

import cellsociety.errors.InvalidFileFormatError;
import cellsociety.logic.grid.FiniteGrid;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.shapes.SquareShapeManager;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @Test
    void saveGridToCsv() throws IOException, InvalidFileFormatError {
        Grid grid = new FiniteGrid(CSVFileReader.readFile(new File("./data/game_of_life/still_life_square.csv")), new SquareShapeManager());
        assertNotEquals(null, grid);
    }
}

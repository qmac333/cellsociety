package cellsociety.io;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.UnhandledExceptionError;
import cellsociety.logic.grid.Grid;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTest {

    @Test
    void configurationWithCSVTest() throws FileNotFoundError, UnhandledExceptionError {
        Grid grid = CSVFileReader.readFile("data/game_of_life/still_life_square.csv");
        assertNotEquals(null, grid);

        assertEquals(4, grid.getWidth());
        assertEquals(4, grid.getHeight());
        try {
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader("data/game_of_life/still_life_square.csv");

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            int i = 0, j = 0;
            //this is merely to skip the line with the dimensions
            String[] nextRecord = csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    assertEquals(Integer.parseInt(cell), grid.getCell(i % grid
                            .getWidth(), j % grid
                            .getHeight()));
                    j++;
                }
                i++;
            }

        } catch (Exception e) {
        }

        int xPos = 0, yPos = 0;
        assertEquals(0, grid.getCell(xPos, yPos));
        xPos = 0;
        yPos = 3;
        assertEquals(0, grid.getCell(xPos, yPos));
        xPos = 1;
        yPos = 1;
        assertEquals(1, grid.getCell(xPos, yPos));
        xPos = 1;
        yPos = 2;
        assertEquals(1, grid.getCell(xPos, yPos));
        xPos = 2;
        yPos = 1;
        assertEquals(1, grid.getCell(xPos, yPos));
        xPos = 2;
        yPos = 2;
        assertEquals(1, grid.getCell(xPos, yPos));
        xPos = 3;
        yPos = 0;
        assertEquals(0, grid.getCell(xPos, yPos));
        xPos = 3;
        yPos = 3;
        assertEquals(0, grid.getCell(xPos, yPos));
    }



}
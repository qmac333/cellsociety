package cellsociety.logic;

import com.opencsv.CSVReader;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {

    private Logic logic;

    @BeforeEach
    void setUp() {
        logic = new Logic(100, 100) {
        };
    }

    @Test
    void initializeWithCSVFile() {
        assertEquals(true, logic.initializeWithCSVFile("data/game_of_life/blinkers.csv"));

        assertEquals(10, logic.getGrid().getWidth());
        assertEquals(10, logic.getGrid().getHeight());
        try{
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader("data/game_of_life/blinkers.csv");

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            int i = 0, j = 0;
            //this is merely to skip the line with the dimensions
            String[] nextRecord = csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    assertEquals(Integer.parseInt(cell), logic.getGrid().getCell(i, j));
                    j++;
                }
                i++;
            }

        }catch (Exception e){

        }

    }

    @Test
    void initializeWithSimFile() {
    }

    @Test
    void getGrid() {
    }
}
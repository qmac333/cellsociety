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
//class ModelOfSegregationTest {
//
//    private ModelOfSegregation mos;
//
//    @Test
//    void linesTest(){
//        try {
//            Grid grid = new Grid(CSVFileReader.readFile(new File("data/segregation_model/lines.csv")));
//            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/segregation_model/lines.sim"));
//            mos = new ModelOfSegregation(grid.getCellStates(), metadata);
//            mos.update();
//            int x = 0;
//            int y = 0;
//            assertEquals(2, mos.getGrid().getCell(x, y).getState());
//            x = 0;
//            y = 1;
//            assertEquals(1, mos.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 0;
//            assertEquals(2, mos.getGrid().getCell(x, y).getState());
//            x = 2;
//            y = 1;
//            assertEquals(1, mos.getGrid().getCell(x, y).getState());
//            x = 1;
//            y = 2;
//            assertEquals(2, mos.getGrid().getCell(x, y).getState());
//        } catch (Exception e) {
//            fail("File Not Found");
//        }
//
//    }
//}
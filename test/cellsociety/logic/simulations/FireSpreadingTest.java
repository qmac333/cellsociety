package cellsociety.logic.simulations;

import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static cellsociety.controller.LogicController.INITIAL_STATE_FILE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FireSpreadingTest {

    private FireSpreading fs;

    @Test
    void simulationOfSingleFireTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/single_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape")).getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            fs = (FireSpreading) FireSpreading.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            for(int i = 0; i < fs.getGrid().getWidth(); i++){
                for(int j = 0; j < fs.getGrid().getHeight(); j++){
                    System.out.print(fs.getGrid().getCell(i,j).getCurrentState() + ",");
                }
                System.out.println("");
            }
            fs.update();
            int x = 0;
            int y = 0;
            for(int i = 0; i < fs.getGrid().getWidth(); i++){
                for(int j = 0; j < fs.getGrid().getHeight(); j++){
                    System.out.print(fs.getGrid().getCell(i,j).getCurrentState() + ",");
                }
                System.out.println("");
            }

            assertEquals(1, fs.getGrid().getCell(x, y).getCurrentState());
            x = 4;
            y = 4;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 3;
            y = 4;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
            x = 5;
            y = 4;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
            x = 4;
            y = 3;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
            x = 4;
            y = 5;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            e.printStackTrace();
            fail("File Not Found");
        }

    }


    @Test
    void simulationOfDiagonalFireTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/single_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            fs = (FireSpreading) getClass().getMethod(metadata.get("Type"), Grid.class, NeighborhoodPattern.class, Map.class).invoke(
                    this,
                    CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE))),
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            fs.update();
            int x = 0;
            int y = 0;
            assertEquals(1, fs.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 9;
            y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 8;
            y = 0;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 8;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }

    @Test
    void simulationOfRingOfFireTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/fire_spreading/single_fire.sim"));
            metadata.put("ProbCatch", "1.00");
            fs = (FireSpreading) getClass().getMethod(metadata.get("Type"), Grid.class, NeighborhoodPattern.class, Map.class).invoke(
                    this,
                    CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE))),
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            fs.update();
            int x = 0;
            int y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 9;
            y = 0;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 9;
            y = 9;
            assertEquals(0, fs.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 1;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
            x = 8;
            y = 8;
            assertEquals(2, fs.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }
}
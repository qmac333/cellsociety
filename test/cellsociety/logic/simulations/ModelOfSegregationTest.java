package cellsociety.logic.simulations;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidFileFormatError;
import cellsociety.errors.UnhandledExceptionError;
import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static cellsociety.controller.LogicController.INITIAL_STATE_FILE;
import static org.junit.jupiter.api.Assertions.*;

class ModelOfSegregationTest {

    private ModelOfSegregation mos;

    @Test
    void linesTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/segregation_model/lines.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape")  + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get(INITIAL_STATE_FILE))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);

            mos = (ModelOfSegregation) ModelOfSegregation.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            mos.update();
            int x = 0;
            int y = 0;
            assertEquals(2, mos.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 1;
            assertEquals(1, mos.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 0;
            assertEquals(2, mos.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 1;
            assertEquals(1, mos.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(2, mos.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }
}
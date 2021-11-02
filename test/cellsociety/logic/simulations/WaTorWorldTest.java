package cellsociety.logic.simulations;

import cellsociety.io.CSVFileReader;
import cellsociety.io.SIMFileReader;
import cellsociety.logic.grid.Grid;
import cellsociety.logic.neighborhoodpatterns.NeighborhoodPattern;
import cellsociety.logic.shapes.ShapeManager;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static cellsociety.controller.LogicController.INITIAL_STATE_FILE;
import static org.junit.jupiter.api.Assertions.*;

class WaTorWorldTest {
    private WaTorWorld ww;

    @Test
    void randomAssortmentTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/random_assortment.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            ww = (WaTorWorld) WaTorWorld.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            ww.update();
            int x = 0;
            int y = 0;
            System.out.println(grid);
            assertEquals(2, ww.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 1;
            assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }

    @Test
    void bigOceanTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/big_ocean.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            ww = (WaTorWorld) WaTorWorld.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            ww.update();
            int x = 0;
            int y = 0;
            assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 2;
            assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
            x = 11;
            y = 11;
            assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }

    @Test
    void volcanoTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/wator/divided.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            ww = (WaTorWorld) WaTorWorld.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            ww.update();
            int x;
            int y;
            for (int j=0; j<5; j++) {
                for (int k=0; k<5; k++) {
                    x=j;
                    y=k;
                    assertEquals(0, ww.getGrid().getCell(x, y).getCurrentState());
                }
            }
        } catch (Exception e) {
            fail("File Not Found");
        }

    }
}
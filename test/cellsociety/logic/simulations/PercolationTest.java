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

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    private Percolation p;
    @Test
    void simplePipeTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/simple_pipe.sim"));
            metadata.put("ProbCatch", "1.00");
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            p = (Percolation) Percolation.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            p.update();
            int x = 0;
            int y = 0;
            assertEquals(2, p.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 2;
            assertEquals(1, p.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(1, p.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            e.printStackTrace();
            //fail("File Not Found");
        }

    }

    @Test
    void longPipeTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/long_pipe.sim"));
            metadata.put("ProbCatch", "1.00");
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape")  + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            p = (Percolation) Percolation.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            p.update();
            int x = 0;
            int y = 0;
            assertEquals(1, p.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 2;
            assertEquals(1, p.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(1, p.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }

    @Test
    void volcanoTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/percolation/volcano.sim"));
            metadata.put("ProbCatch", "1.00");
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape")  + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            p = (Percolation) Percolation.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            p.update();
            int x = 0;
            int y = 0;
            assertEquals(0, p.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 2;
            assertEquals(0, p.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 5;
            assertEquals(2, p.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }

    }
}
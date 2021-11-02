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
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private GameOfLife gol;

    @Test
    void simulationOfBoatTest() throws FileNotFoundError, UnhandledExceptionError {

        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/boat.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") +  "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            gol = (GameOfLife) GameOfLife.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            gol.update();
            int x = 0;
            int y = 0;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 0;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            e.printStackTrace();
            fail("File Not Found");
        }

    }

    @Test
    void simulationOfLightWeightShipTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/light_weight_ship.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape")  + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            gol = (GameOfLife) GameOfLife.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            gol.update();
            int x = 0;
            int y = 5;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 4;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 3;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 2;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 1;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 5;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 5;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
            x = 3;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
        } catch (FileNotFoundException | UnhandledExceptionError | InvalidFileFormatError | ClassNotFoundException | NoSuchMethodException e) {
            fail();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Test
    void simulationOfToadTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/oscillator_toad.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            gol = (GameOfLife) GameOfLife.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            gol.update();
            int x = 2;
            int y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 3;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 3;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 4;
            y = 2;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 4;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }
    }

    @Test
    void simulationOfStillLifeBlockTest() {
        try {
            HashMap<String, String> metadata = (HashMap<String, String>) SIMFileReader.getMetadataFromFile(new File("data/game_of_life/still_life_square.sim"));
            Class cls[] = new Class[]{int[][].class, ShapeManager.class};
            ShapeManager sm = (ShapeManager) Class.forName("cellsociety.logic.shapes." + metadata.get("Shape") + "ShapeManager").getConstructor().newInstance();
            Object[] params = {CSVFileReader.readFile(new File(metadata.get("InitialStates"))),
                    sm};
            Grid grid = (Grid) Class.forName("cellsociety.logic.grid." + metadata.get("GridType")).getConstructor(cls).newInstance(params);
            gol = (GameOfLife) GameOfLife.class.getConstructor(Grid.class, NeighborhoodPattern.class, Map.class).newInstance(
                    grid,
                    (NeighborhoodPattern) Class.forName("cellsociety.logic.neighborhoodpatterns." + metadata.get("Neighborhood"))
                            .getConstructor().newInstance(),
                    metadata);
            gol.update();
            int x = 1;
            int y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 2;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 1;
            y = 2;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 2;
            y = 1;
            assertEquals(1, gol.getGrid().getCell(x, y).getCurrentState());
            x = 0;
            y = 0;
            assertEquals(0, gol.getGrid().getCell(x, y).getCurrentState());
        } catch (Exception e) {
            fail("File Not Found");
        }
    }

}

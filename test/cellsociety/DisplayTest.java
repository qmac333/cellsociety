package cellsociety;

import cellsociety.display.Display;
import org.junit.jupiter.api.Test;
import javafx.scene.paint.Color;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DisplayTest {
    /**
     * Test a method from Display.
     */
    @Test
    void DisplayUpdateTest () {
        Display display = new Display(Color.BLACK, 15, 1000, 600);
        // different ways to test double results
        int[][] testGrid = new int[15][15];
        for(int i = 0; i < testGrid.length; i++){
            for(int j = 0; j < testGrid[i].length; j++){
                testGrid[i][j] =  (int) Math.round( Math.random());
            }
        }
        display.updateScene(testGrid);

    }
}

package cellsociety.display;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Display {
    public final static int LEFT_OFFSET_GRID = 350;
    public final static int TOP_OFFSET_GRID = 50;
    public final static double CELL_LENGTH = 29;
    public final static double CELL_OFFSET = 1.5;
    private Scene scene;
    private Rectangle[][] rectGrid;


    /**
     * Create display based on given background color and Grid Cell length.
     */
    public Display ( Paint background, int gridSize, int sceneWidth, int sceneHeight) {
        Group root = new Group();

        rectGrid = new Rectangle[gridSize][gridSize];

        int xPos = 0;
        gridSize --;
        for(int x = 0; x < (CELL_LENGTH + CELL_OFFSET)*gridSize; x += CELL_LENGTH + CELL_OFFSET){
            int yPos = 0;
            for(int y = 0; y < (CELL_LENGTH + CELL_OFFSET)*gridSize; y += CELL_LENGTH + CELL_OFFSET){
                Rectangle cell = new Rectangle(x + LEFT_OFFSET_GRID, y + TOP_OFFSET_GRID, CELL_LENGTH, CELL_LENGTH);
                cell.setFill(Color.WHITE);
                rectGrid[xPos][yPos] = cell;
                root.getChildren().add(cell);
                yPos++;
            }
            xPos++;
        }

        Button lifeButton = new Button("Game of Life");
        Button fireButton = new Button("Spreading of Fire");
        Button segregationButton = new Button("Schelling's model of segregation");
        Button sharkButton = new Button("Wa-Tor World model of predator-prey relationships");

        root.getChildren().add(lifeButton);
        root.getChildren().add(fireButton);
        root.getChildren().add(segregationButton);
        root.getChildren().add(sharkButton);

        scene = new Scene(root,sceneWidth, sceneHeight, background);
    }

    /**
     * Return current Scene
     */
    public Scene getScene () {
        return scene;
    }

    /**
     * Update Scene
     */
    public void updateScene (int[][] grid) {
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                if(grid[i][j] == 0){
                    rectGrid[i][j].setFill(Color.WHITE);
                }else if(grid[i][j] == 1){
                    rectGrid[i][j].setFill(Color.LIGHTBLUE);
                }
            }
        }
    }
}

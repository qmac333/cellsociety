package cellsociety.display;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;
import cellsociety.io.FilePickerEventHandler;
import java.io.File;
import java.util.*;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;


public class Display {

    public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.resources.";
    public static final String DEFAULT_RESOURCE_FOLDER =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");

    public static Map<Integer, Color> COLOR_MAP = new HashMap();
    static {
        COLOR_MAP.put(0, Color.WHITE);
        COLOR_MAP.put(1, Color.BLUE);
        COLOR_MAP.put(2, Color.RED);
    }
    public final static int TOP_OFFSET_GRID = 50;
    public final static double CELL_LENGTH = 29;
    public final static double CELL_OFFSET = 1.5;
    public final static int BUTTON_OFFSET = 50;
    public final static int BUTTON_OFFSET_TOP = 30;
    private Stage myStage;
    private Rectangle[][] displayGrid;

    private Group root;
    protected ResourceBundle myResources;
    protected ResourceBundle propertyResources;
    private int gridLeftOffset;
    private int gridTopOffset;
    private int cellLength;
    private double cellOffset;
    private int buttonOffset;
    private int buttonOffsetTop;

    /**
     * Create display based on given background color and Grid Cell length.
     */
    public Display (Stage myStage, Color background) {
        this.myStage = myStage;
        root = (Group)myStage.getScene().getRoot();
        myStage.getScene().setFill(background);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + ResourceBundle.getBundle("cellsociety.ProgramSettings").getString("Language"));
        propertyResources = ResourceBundle.getBundle("cellsociety.display.Display");

        gridLeftOffset = Integer.parseInt(propertyResources.getString("GRID_LEFT_OFFSET"));
        gridTopOffset = Integer.parseInt(propertyResources.getString("GRID_TOP_OFFSET"));
        cellLength = Integer.parseInt(propertyResources.getString("CELL_LENGTH"));
        cellOffset = Double.parseDouble(propertyResources.getString("CELL_OFFSET"));
        buttonOffset = Integer.parseInt(propertyResources.getString("BUTTON_OFFSET"));
        buttonOffsetTop = Integer.parseInt(propertyResources.getString("BUTTON_OFFSET_TOP"));
    }

    public void initializeGrid(int[][] grid) {
        resetGrid();
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return;
        }
        displayGrid = new Rectangle[grid.length][grid[0].length];

        for(int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                Rectangle cell = new Rectangle(x*(cellLength + cellOffset) + gridLeftOffset,
                    y*(cellOffset + cellLength) + gridTopOffset, cellLength, cellLength);
                displayGrid[x][y] = cell;
                root.getChildren().add(cell);
            }
        }
        updateScene(grid);
    }

    /**
     * Removes all elements of the displayGrid from the display.
     */

    public void resetGrid() {
        if (displayGrid != null) {
            for (int i = 0; i < displayGrid.length; i++) {
                for (int j = 0; j < displayGrid[0].length; j++) {
                    root.getChildren().remove(displayGrid[i][j]);
                }
            }
        }
    }

    /**
     * Update Scene
     */
    public void updateScene (int[][] grid) {
        if (displayGrid == null || grid.length != displayGrid.length) {
            initializeGrid(grid);
            return;
        }
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                displayGrid[i][j].setFill(COLOR_MAP.get(grid[i][j]));
            }
        }
    }

    public void addButtons(Button saveButton, Button playButton, Button pauseButton, Button resetButton, Button loadButton){
        loadButton.setLayoutX(buttonOffset);
        loadButton.setLayoutY(buttonOffsetTop);
        loadButton.setText("Load");

        playButton.setLayoutX(buttonOffset);
        playButton.setLayoutY(buttonOffsetTop + buttonOffset);
        playButton.setText("Play");

        pauseButton.setLayoutX(buttonOffset);
        pauseButton.setLayoutY(buttonOffsetTop + buttonOffset*2);
        pauseButton.setText("Pause");

        resetButton.setLayoutX(buttonOffset);
        resetButton.setLayoutY(buttonOffsetTop + buttonOffset*3);
        resetButton.setText("Reset");

        saveButton.setLayoutX(buttonOffset);
        saveButton.setLayoutY(buttonOffsetTop + buttonOffset*4);
        saveButton.setText("Save");

        root.getChildren().add(saveButton);
        root.getChildren().add(playButton);
        root.getChildren().add(pauseButton);
        root.getChildren().add(resetButton);
        root.getChildren().add(loadButton);
    }

    public void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String errorTitle = "Error";
        String errorMessage = myResources.getString(errorTitle);
        if (e instanceof FileNotFoundError) {
            errorTitle = "FileNotFoundError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((FileNotFoundError)e).getFilename());
        } else if (e instanceof InvalidSimulationTypeError) {
            errorTitle = "InvalidSimulationTypeError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((InvalidSimulationTypeError)e).getType());
        } else if (e instanceof MissingSimulationArgumentError) {
            errorTitle = "MissingSimulationArgumentError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((MissingSimulationArgumentError)e).getArgument());
        }
        alert.setTitle(errorTitle);
        alert.setContentText(errorMessage);
        alert.show();
    }
}

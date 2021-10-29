package cellsociety.display;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;

import java.util.*;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Grid;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Tim Jang
 * @author William Convertino
 * @author Quentin MacFarlane
 * @author Alexis Cruz-Ayala
 */
public abstract class Display {

    public static final String DEFAULT_RESOURCE_PACKAGE = "cellsociety.resources.";
    public static final String DEFAULT_RESOURCE_FOLDER =
            "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");

    public static Map<Integer, Color> COLOR_MAP = new HashMap();

    static {
        COLOR_MAP.put(0, Color.WHITE);
        COLOR_MAP.put(1, Color.BLUE);
        COLOR_MAP.put(2, Color.RED);
    }

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
    public Display(Stage myStage, Color background) {
        this.myStage = myStage;
        root = (Group) myStage.getScene().getRoot();
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

    public void initializeGrid(Grid grid) {
        resetGrid();

        if (grid == null || grid.getHeight() == 0 || grid.getWidth() == 0) {
            return;
        }
        displayGrid = new Rectangle[grid.getHeight()][grid.getWidth()];

        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                Rectangle cell = new Rectangle(x * (cellLength + cellOffset) + gridLeftOffset,
                        y * (cellOffset + cellLength) + gridTopOffset, cellLength, cellLength);
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
            for (Rectangle[] rectangles : displayGrid) {
                for (int j = 0; j < displayGrid[0].length; j++) {
                    root.getChildren().remove(rectangles[j]);
                }
            }
        }
    }

    /**
     * Update Scene
     * @param grid
     */
    public void updateScene(Grid grid) {
        if (displayGrid == null || grid.getHeight() != displayGrid.length) {
            initializeGrid(grid);
            return;
        }
        for (int r = 0; r < grid.getHeight(); r++) {
            for (int c = 0; c < grid.getWidth(); c++) {
                displayGrid[r][c].setFill(COLOR_MAP.get(grid.getCell(r,c)));
            }
        }
    }

    public int[] changeCell(double mouseX, double mouseY, Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            for (int y = 0; y < grid.getHeight(); y++) {
                double corrX = x * (cellLength + cellOffset) + gridLeftOffset;
                double corrY = y * (cellOffset + cellLength) + gridTopOffset;
                if(((corrX + cellLength) >= mouseX
                        && (corrY + cellLength) >= mouseY
                        && corrX <= mouseX + cellLength
                        && (corrY <= (mouseY + cellLength)))){
                    int[] s = new int[]{x, y};
                    return s;
                }
            }
        }
        return null;
    }

    public void addButtons(Button saveButton, Button playButton, Button pauseButton, Button resetButton, Button loadButton, Slider speedSlider) {
        loadButton.setLayoutX(buttonOffset);
        loadButton.setLayoutY(buttonOffsetTop);
        loadButton.setText("Load");

        playButton.setLayoutX(buttonOffset);
        playButton.setLayoutY(buttonOffsetTop + buttonOffset);
        playButton.setText("Play");

        pauseButton.setLayoutX(buttonOffset);
        pauseButton.setLayoutY(buttonOffsetTop + buttonOffset * 2);
        pauseButton.setText("Pause");

        resetButton.setLayoutX(buttonOffset);
        resetButton.setLayoutY(buttonOffsetTop + buttonOffset * 3);
        resetButton.setText("Reset");

        saveButton.setLayoutX(buttonOffset);
        saveButton.setLayoutY(buttonOffsetTop + buttonOffset * 4);
        saveButton.setText("Save");

        speedSlider.setLayoutX(buttonOffset);
        speedSlider.setLayoutY(buttonOffsetTop + buttonOffset * 5);
        speedSlider.setShowTickLabels(true);

        root.getChildren().add(saveButton);
        root.getChildren().add(playButton);
        root.getChildren().add(pauseButton);
        root.getChildren().add(resetButton);
        root.getChildren().add(loadButton);
        root.getChildren().add(speedSlider);
    }

    public void showError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String errorTitle = "Error";
        String errorMessage = myResources.getString(errorTitle);
        if (e instanceof FileNotFoundError) {
            errorTitle = "FileNotFoundError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((FileNotFoundError) e).getFilename());
        } else if (e instanceof InvalidSimulationTypeError) {
            errorTitle = "InvalidSimulationTypeError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((InvalidSimulationTypeError) e).getType());
        } else if (e instanceof MissingSimulationArgumentError) {
            errorTitle = "MissingSimulationArgumentError";
            errorMessage = String.format("%s %s", myResources.getString(errorTitle), ((MissingSimulationArgumentError) e).getArgument());
        }
        alert.setTitle(errorTitle);
        alert.setContentText(errorMessage);
        alert.show();
    }
}

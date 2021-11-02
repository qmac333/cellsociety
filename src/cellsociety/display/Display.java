package cellsociety.display;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidSimulationTypeError;
import cellsociety.errors.MissingSimulationArgumentError;

import java.util.*;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.grid.Grid;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Tim Jang
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

    protected Group root;
    protected ResourceBundle myResources;
    protected ResourceBundle propertyResources;
    protected int gridLeftOffset;
    protected int gridTopOffset;
    protected int cellLength;
    protected double cellOffset;
    protected int buttonOffset;
    protected int buttonOffsetTop;

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


    public abstract void initializeGrid(List<Cell> cells);

    /**
     * Removes all elements of the displayGrid from the display.
     */

    public abstract void resetGrid();

    public abstract void updateScene(List<Cell> cells);

    public abstract Coordinate changeCell(double mouseX, double mouseY, List<Cell> cells);


    public void addButtons(Node...nodes){// saveButton, Button playButton, Button pauseButton, Button resetButton, Button loadButton, Slider speedSlider) {
        int scalar = 0;
        for(Node node : nodes){
            node.setLayoutX(buttonOffset);
            node.setLayoutY(buttonOffsetTop + buttonOffset*scalar);
            root.getChildren().add(node);
            scalar++;
        }

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

    public Stage getMyStage() {
        return myStage;
    }
}

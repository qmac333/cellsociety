package cellsociety.display;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.grid.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tim Jang
 */

public class RectangleDisplay extends Display{
    Map<Coordinate, Rectangle> cellMap;

    public RectangleDisplay(Stage stage, Color background){
        super(stage, background);
    }

    public void initializeGrid(List<Cell> cells){
        resetGrid();

        if(cells.size() == 0) return;

        for(int i = 0; i < cells.size(); i++){
            resetGrid();

            cellMap = new HashMap<>();
            Rectangle rectCell = new Rectangle(cells.get(i).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset,
                    cells.get(i).getCoordinates().c() * (cellOffset + cellLength) + gridTopOffset, cellLength, cellLength);

            cellMap.put(cells.get(i).getCoordinates(), rectCell);
            root.getChildren().add(rectCell);
        }

        updateScene(cells);
    }

    /**
     * Removes all elements of the rectangle objects from the display.
     */
    public void resetGrid(){
        if (cellMap != null) {
            for (Coordinate coordinate : cellMap.keySet()) {
                root.getChildren().remove(cellMap.get(coordinate));
            }
        }
    }

    /**
     * Update Scene
     * @param cells
     */
    public void updateScene(List<Cell> cells) {
        if (cells == null || cells.size() == 0 || cellMap == null) {
            return;
        }

        for (int i = 0; i < cells.size(); i++) {
            Coordinate cellCoordinate = cells.get(i).getCoordinates();
            int currentState = cells.get(i).getCurrentState();

            cellMap.get(cellCoordinate).setFill(COLOR_MAP.get(currentState));
        }
    }

}

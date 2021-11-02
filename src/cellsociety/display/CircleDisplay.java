package cellsociety.display;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tim Jang
 */
public class CircleDisplay extends Display {
    Map<Coordinate, Circle> cellMap;

    public CircleDisplay(Stage stage, Color background){
        super(stage, background);
    }

    public void initializeGrid(List<Cell> cells){
        resetGrid();

        cellMap = new HashMap<>();

        for(int i = 0; i < cells.size(); i++){
            Circle circleCell = new Circle(cells.get(i).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset,
                    cells.get(i).getCoordinates().c() * (cellOffset + cellLength) + gridTopOffset,
                    cellLength/2);

            cellMap.put(cells.get(i).getCoordinates(), circleCell);

            root.getChildren().add(circleCell);
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
        if (cellMap == null || cellMap.size() != cells.size()) {
            initializeGrid(cells);
            return;
        }

        for (int i = 0; i < cells.size(); i++) {
            Coordinate cellCoordinate = cells.get(i).getCoordinates();
            int currentState = cells.get(i).getCurrentState();

            cellMap.get(cellCoordinate).setFill(COLOR_MAP.get(currentState));
        }

        //System.out.println(cellMap);
    }

    public Coordinate changeCell(double mouseX, double mouseY, List<Cell> cells) {
//        for (int x = 0; x < grid.getWidth(); x++) {
//            for (int y = 0; y < grid.getHeight(); y++) {
//                double corrX = x * (cellLength + cellOffset) + gridLeftOffset;
//                double corrY = y * (cellOffset + cellLength) + gridTopOffset;
//                if(((corrX + cellLength) >= mouseX
//                        && (corrY + cellLength) >= mouseY
//                        && corrX <= mouseX + cellLength
//                        && (corrY <= (mouseY + cellLength)))){
//                    int[] s = new int[]{x, y};
//                    return s;
//                }
//            }
//        }
        return null;
    }

}

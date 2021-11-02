package cellsociety.display;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.grid.Grid;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.*;

/**
 * @author Tim Jang
 */

public class SquareDisplay extends Display{
    Map<Coordinate, Rectangle> cellMap;

    public SquareDisplay(Stage stage, Color background){
        super(stage, background);
    }

    public void initializeGrid(List<Cell> cells){
        resetGrid();


        cellMap = new HashMap<>();

        for(int i = 0; i < cells.size(); i++){

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
        if (cellMap == null || cellMap.size() != cells.size()) {
//            for(Cell cell: cells){
//                System.out.println(cell.getCoordinates());
//            }
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
        for(int index = 0; index < cells.size(); index++){
            //cells.get(i).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset
            double corrX = cells.get(index).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset;
            double corrY = cells.get(index).getCoordinates().c() * (cellOffset + cellLength) + gridTopOffset;
            if(((corrX + cellLength) >= mouseX
                && (corrY + cellLength) >= mouseY
                && corrX <= mouseX + cellLength
                && (corrY <= (mouseY + cellLength)))){
                    return cells.get(index).getCoordinates();
                }
        }
        return null;
    }

}

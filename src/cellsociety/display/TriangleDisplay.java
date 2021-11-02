package cellsociety.display;

import cellsociety.logic.grid.Cell;
import cellsociety.logic.grid.Coordinate;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tim Jang
 */
public class TriangleDisplay extends Display{
    Map<Coordinate, Polygon> cellMap;

    public TriangleDisplay(Stage stage, Color background){
        super(stage, background);
    }

    public void initializeGrid(List<Cell> cells){
        resetGrid();

        cellMap = new HashMap<>();

        for(int i = 0; i < cells.size(); i++){

            Polygon triangleCell = new Polygon();

            double posX = cells.get(i).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset;
            double posY = cells.get(i).getCoordinates().c() * (cellOffset + cellLength) + gridTopOffset;

            Double[] trianglePositions = new Double[6];

            if(cells.get(i).getCoordinates().c() % 2 == 0){
                trianglePositions[0] = posX;
                trianglePositions[1] = posY;
                trianglePositions[2] = posX - cellLength/2;
                trianglePositions[3] = posY + cellLength;
                trianglePositions[4] = posX + cellLength/2;
                trianglePositions[5] = posY + cellLength;

                if(cells.get(i).getCoordinates().r() % 2 == 1){
                    trianglePositions[1] = posY + cellLength;
                    trianglePositions[3] = posY;
                    trianglePositions[5] = posY;
                }
            }else{
                trianglePositions[0] = posX;
                trianglePositions[1] = posY + cellLength;
                trianglePositions[2] = posX - cellLength/2;
                trianglePositions[3] = posY;
                trianglePositions[4] = posX + cellLength/2;
                trianglePositions[5] = posY;

                if(cells.get(i).getCoordinates().r() % 2 == 1){
                    trianglePositions[1] = posY;
                    trianglePositions[2] = posX - cellLength/2;
                    trianglePositions[3] = posY + cellLength;
                    trianglePositions[5] = posY + cellLength;
                }
            }

            triangleCell.getPoints().addAll(trianglePositions);

            cellMap.put(cells.get(i).getCoordinates(), triangleCell);

            root.getChildren().add(triangleCell);
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
            double corrX = cells.get(index).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset;
            double corrY = cells.get(index).getCoordinates().r() * (cellOffset + cellLength) + gridTopOffset;
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

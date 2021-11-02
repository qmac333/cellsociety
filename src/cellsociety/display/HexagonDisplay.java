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

public class HexagonDisplay extends Display{
    Map<Coordinate, Polygon> cellMap;

    public HexagonDisplay(Stage stage, Color background){
        super(stage, background);
        cellOffset =  cellLength*Math.cos(Math.toRadians(60)) + 10;
    }

    public void initializeGrid(List<Cell> cells){
        resetGrid();

        cellMap = new HashMap<>();
        double extraOffset = 0;
        for(int i = 0; i < cells.size(); i++){
            Polygon hexagon = new Polygon();
            extraOffset = (cells.get(i).getCoordinates().c()%2 == 1) ? cellLength : 0;
            double startX = cells.get(i).getCoordinates().r() * (cellLength + cellOffset) + gridLeftOffset + extraOffset;
            double startY = cells.get(i).getCoordinates().c() * (cellLength + cellLength*Math.sin(Math.toRadians(60))) + gridTopOffset;
            hexagon.getPoints().addAll(new Double[]{
                    startX, startY,
                    startX + cellLength*Math.cos(Math.toRadians(30)), startY - cellLength*Math.sin(Math.toRadians(30)),
                    startX + 2*cellLength*Math.cos(Math.toRadians(30)), startY,
                    startX + 2*cellLength*Math.cos(Math.toRadians(30)), startY + cellLength,
                    startX + cellLength*Math.cos(Math.toRadians(30)), startY + cellLength + cellLength*Math.sin(Math.toRadians(30)),
                    startX, startY + cellLength,
            });
            cellMap.put(cells.get(i).getCoordinates(), hexagon);

            root.getChildren().add(hexagon);
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

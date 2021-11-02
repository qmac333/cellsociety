package cellsociety.io;

import cellsociety.logic.grid.Coordinate;
import cellsociety.logic.grid.Grid;
import com.opencsv.CSVWriter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quentin MacFarlane
 * @since 0.0.2
 */
public class FileHandler {

    private static FileChooser fChooser = new FileChooser();

    /**
     * saves the current grid_LEGACY configuration to a CSV file for the user to upload later
     * @param grid the grid_LEGACY configuration to be saved to a new CSV file
     * @param stage the window that we are currently in
     */
    public static void saveFile(Grid grid, Stage stage) {
        List<String[]> csvData = createCsvData(grid);

        fChooser.setTitle("Save File Dialog");
        fChooser.setInitialFileName("mysave");
        fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv file", "*.csv"));
        File file = fChooser.showSaveDialog(stage);
        // default separator is a comma
        try (FileWriter outputFile = new FileWriter(file);
             CSVWriter writer = new CSVWriter(outputFile, ',',
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates the CSV data for the grid_LEGACY configuration
     * @param grid the grid_LEGACY configuration to be saved in a CSV file
     * @return a list of string arrays where each string array represents one row of the grid_LEGACY. Each string array is
     * a list of 1's and 0's except for the first row, which has the dimensions of the grid_LEGACY
     */
    private static List<String[]> createCsvData(Grid grid) {
        List<String[]> list = new ArrayList<>();
        String[] dimensions = {String.valueOf(grid.getWidth()), String.valueOf(grid.getHeight())};
        list.add(dimensions);

        String[] eachRow = new String[grid.getWidth()];
        for (int i = 0; i < grid.getHeight(); i++) {
            for (int j = 0; j < grid.getWidth();j++) {
                eachRow[j] = String.valueOf(grid.getCell(i, j).getCurrentState());
            }
            list.add(eachRow);
            eachRow = new String[grid.getWidth()];
        }
        return list;
    }
}

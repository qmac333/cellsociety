package cellsociety.io;

import cellsociety.logic.grid.Grid;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Quentin MacFarlane
 * @since 0.0.2
 */
public class FileHandler {

    /**
     * saves the current grid configuration to a CSV file for the user to upload later
     * @param grid the grid configuration to be saved to a new CSV file
     * @param fileName the file name of the file that will be saved
     */
    public static void saveFile(int[][] grid, String fileName) {
        List<String[]> csvData = createCsvData(grid);


        // default separator is a comma
        try (FileWriter outputfile = new FileWriter(fileName);
             CSVWriter writer = new CSVWriter(outputfile, ',',
                     CSVWriter.NO_QUOTE_CHARACTER,
                     CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                     CSVWriter.DEFAULT_LINE_END)) {
            writer.writeAll(csvData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * creates the CSV data for the grid configuration
     * @param grid the grid configuration to be saved in a CSV file
     * @return a list of string arrays where each string array represents one row of the grid. Each string array is
     * a list of 1's and 0's except for the first row, which has the dimensions of the grid
     */
    private static List<String[]> createCsvData(int[][] grid) {
        List<String[]> list = new ArrayList<>();
        String[] dimensions = {String.valueOf(grid[0].length), String.valueOf(grid.length)};
        list.add(dimensions);

        String[] eachRow = new String[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length;j++) {
                eachRow[j] = String.valueOf(grid[i][j]);
            }
            list.add(eachRow);
            eachRow = new String[grid[0].length];
        }
        return list;
    }
}

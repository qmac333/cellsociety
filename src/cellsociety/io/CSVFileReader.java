package cellsociety.io;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.InvalidFileFormatError;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



/**
 * A tool to read CSV files and convert their data into a 2d integer array.
 *
 * @author William Convertino
 * @author Alexis Cruz
 *
 * @since 0.0.1
 */
public class CSVFileReader {

  /**
   * Reads a CSV file, and returns its content as a 2d array of integers.
   *
   * @param file the CSV file to read.
   * @return a 2d array that contains the values of the CSV file.
   * @throws FileNotFoundError if the CSV file cannot be found.
   * @throws InvalidFileFormatError if the CSV file is improperly formatted.
   */
  public static int[][] readFile(File file)
      throws FileNotFoundError, InvalidFileFormatError {
    try {
      CSVReader csvReader = new CSVReader(new FileReader(file));

      int[] dimensions = toIntArray(csvReader.readNext());

      int[][] myGrid = new int[dimensions[1]][dimensions[0]];

      int[] fileRow;
      for (int r = 0; r < dimensions[1]; r++) {
        fileRow = toIntArray(csvReader.readNext());
        myGrid[r] = fileRow;
      }
      return myGrid;
    } catch (FileNotFoundException e) {
      throw new FileNotFoundError(file.getName());
    } catch (Exception e) {
      throw new InvalidFileFormatError(file.getName());
    }
  }

  /**
   * Converts an array of strings to an array of integers.
   *
   * @param myArray the array to convert.
   * @return an array of integers that have the same values as those in the string array.
   */
  public static int[] toIntArray(String[] myArray) {
    int[] myIntArray = new int[myArray.length];
    for (int i = 0; i < myArray.length; i++) {
      myIntArray[i] = Integer.parseInt(myArray[i]);
    }
    return myIntArray;
  }


}

package cellsociety.errors;

import java.io.FileNotFoundException;

/**
 * Signals that the user attempted to open a file that could not be found.
 *
 * @author William Convertino
 */
public class FileNotFoundError extends FileNotFoundException {

  //The name of the file that was accessed.
  private String filename;

  /**
   * Constructs a new FileNotFoundError with the specified
   * filename that was attempted.
   *
   * @param filename the name of the file that the system attempted to open.
   */
  public FileNotFoundError (String filename) {
    this.filename = filename;
  }

  /**
   * Returns the name of the file that the user attempted to open.
   *
   * @return the name of the file that the user attempted to open.
   */
  public String getFilename() {
    return filename;
  }

}

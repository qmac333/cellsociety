package cellsociety.errors;

/**
 * Signals that the user passed a file that was improperly formatted.
 *
 * @author William Convertino
 * @since 0.0.2
 */
public class InvalidFileFormatError extends Exception {

  //The name of the file that was invalidly formatted.
  private String filename;

  /**
   * Constructs a new InvalidFileFormatError with the specified
   * filename of the file that was passed.
   *
   * @param filename the name of the file that the system attempted to read.
   */
  public InvalidFileFormatError (String filename) {
    this.filename = filename;
  }

  /**
   * Returns the name of the file that the user passed.
   *
   * @return the name of the file that the user passed.
   */
  public String getFilename() {
    return filename;
  }

}

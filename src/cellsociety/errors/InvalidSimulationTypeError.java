package cellsociety.errors;

import java.io.FileNotFoundException;

/**
 * Signals that the user attempted to run a non-supported simulation type.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class InvalidSimulationTypeError extends Exception {

  //The type of simulation that was given.
  private String type;

  /**
   * Constructs a new InvalidSimulationTypeError with the type of simulation
   * that was attempted.
   *
   * @param type the type of simulation that the user attempted to run.
   */
  public InvalidSimulationTypeError(String type) {
    this.type = type;
  }

  /**
   * Returns the type of simulation that the user attempted to run.
   *
   * @return the type of simulation that the user attempted to run.
   */
  public String getType() {
    return type;
  }

}

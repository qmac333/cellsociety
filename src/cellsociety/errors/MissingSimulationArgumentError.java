package cellsociety.errors;

/**
 * Signals that the user attempted to run a simulation while missing a required argument.
 *
 * @author William Convertino
 * @since 0.0.1
 */
public class MissingSimulationArgumentError extends IllegalArgumentException {

  //The name of the argument that was missing.
  private String argument;

  /**
   * Constructs a new MissingSimulationArgumentError with the missing
   * argument.
   *
   * @param argument the argument that the simulation was not passed.
   */
  public MissingSimulationArgumentError(String argument) {
    this.argument = argument;
  }

  /**
   * Returns the name of the argument that the user was missing.
   *
   * @return the name of the argument that the user was missing.
   */
  public String getArgument() {
    return argument;
  }

}

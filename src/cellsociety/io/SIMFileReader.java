package cellsociety.io;

import cellsociety.errors.FileNotFoundError;
import cellsociety.errors.UnhandledExceptionError;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * A tool to read SIM files and convert their data into a metadata Map.
 *
 * @author Alexis Cruz
 * @author William Convertino
 *
 * @since 0.0.1
 */
public class SIMFileReader {

  /**
   * Reads in a Sim file and converts the values into a Map of keys and values.
   *
   * @param file the name/path of the SIM file.
   * @return a Map containing the metadata held in the SIM file.
   */
  public static Map<String,String> getMetadataFromFile(String file) throws FileNotFoundError, UnhandledExceptionError {

    HashMap metadata = new HashMap();
    try {
      Properties prop = readPropertiesFile(file);
      if(prop == null) return null;
      for (final String name : prop.stringPropertyNames()) {
        metadata.put(name, prop.getProperty(name));
      }
      return metadata;
    } catch (FileNotFoundException e) {
      throw new FileNotFoundError(file);
    } catch (IOException e) {
      throw new UnhandledExceptionError();
    }
  }

  /**
   * Reads a SIM file and returns a Properties file with its values.
   *
   * @param filename the path of the SIM file being used.
   * @return a Properties class containing the data held in the SIM file.
   */
  private static Properties readPropertiesFile(String filename) throws IOException {
    FileInputStream fis;
    fis = new FileInputStream(filename);
    Properties myProperties = new Properties();
    myProperties.load(fis);
    fis.close();
    return myProperties;
  }

}

package cellsociety.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SIMFileReader {

  public static Map<String,String> getMetadataFromFile(String file) {

    Map metadata = new HashMap();

    Properties prop = readPropertiesFile(file);
    for (final String name : prop.stringPropertyNames()) {
      metadata.put(name, prop.getProperty(name));
    }

    return metadata;
  }

  public static Properties readPropertiesFile(String fileName){
    FileInputStream fis;
    Properties prop = null;
    try {
      fis = new FileInputStream(fileName);
      prop = new Properties();
      prop.load(fis);
      fis.close();
    } catch(FileNotFoundException fnfe) {
    } catch(IOException ioe) {
    }
    return prop;
  }

}

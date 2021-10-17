package cellsociety.logic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * The base Logic class for the individual games
 * @author Alexis Cruz
 */
public abstract class Logic {
    private Grid grid;
    // a hashmap containing the data collected from sim files
    private HashMap<String, String> metadata;

    public Logic(int width, int height){
        grid = new Grid(width, height);
        metadata = new HashMap<>();
    }


    /**
     * initializes the game using a specified CSV file
     * @param file the string of the CSV file to be used
     * @return returns true if the CSV file was read successfully, false other wise
     */
    public boolean initializeWithCSVFile(String file){
        try{
            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            int i = 0, j = 0;

            String[] nextRecord;
            nextRecord = csvReader.readNext();
            //make a dimensions array to just read the width and height first
            int[] dimensions = new int[2];
            for (String cell : nextRecord) {
                    dimensions[i] = Integer.parseInt(cell); i++;
            }
            //set i =0 because we'll be using it to iterate
            i = 0;
            //dimensions[0] is width and dimensions[1] is height
            this.grid = new Grid(dimensions[0], dimensions[1]);
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    getGrid().setCell(i% grid.getWidth(), j% grid.getHeight(), Integer.parseInt(cell));
                    j++;
                }
                i++;
            }
            grid.updateGrid();
            return true;
        } catch (FileNotFoundException e){
            return false;
        } catch (CsvValidationException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * initializes the game using a specified Sim file
     * and sets the parameters to the metadata map
     * @param file the string of the Sim file to be used
     * @return returns true if the Sim file was read successfully, false otherwise
     */
    public boolean initializeWithSimFile(String file){
        Properties prop = readSimFile(file);
        if(prop == null){
            return false;
        }
        for (final String name: prop.stringPropertyNames())
            metadata.put(name, prop.getProperty(name));
        return true;
    }

    /**
     * reads in a Sim file as a properties file since they follow the same structure
     * @param fileName the string of the Sim file to be used
     * @return a Properties class containing the data held in the Sim file
     */
    private Properties readSimFile(String fileName){
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

    /**
     * @return the grid held by the logic
     */
    public Grid getGrid(){
        return grid;
    }

    /**
     * @return the metadata of the Logic
     */
    public HashMap getMetaData(){
        return metadata;
    }

    /**
     * the update function to be run every tick of the game
     */
    public abstract void update();

}

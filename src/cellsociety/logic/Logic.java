package cellsociety.logic;

import cellsociety.errors.FileNotFoundError;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public abstract class Logic {
    private Grid grid;
    private HashMap<String, String> metadata;

    public Logic(int width, int height){
        grid = new Grid(width, height);
    }


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

    public void initializeWithSimFile(String file){
        Properties prop = readPropertiesFile(file);
        for (final String name: prop.stringPropertyNames())
            metadata.put(name, prop.getProperty(name));
    }

    private Properties readPropertiesFile(String fileName){
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

    public Grid getGrid(){
        return grid;
    }


}

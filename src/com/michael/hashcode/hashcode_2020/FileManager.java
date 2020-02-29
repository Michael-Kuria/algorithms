package com.michael.hashcode.hashcode_2020;


/**
 * This class will perform all the file work needed for the application i.e Reading the files
 * and writing the solution as required.
 *
 */
public class FileManager {

    // This represents the file we will be reading and from it we will form an output file
    public String inputFile;
    public String outputFile;
    //This object will be returned once the inputFile has been read
    public BookScanningSimulation simulation;

    public FileManager(String inputFile){
        this.inputFile = inputFile;
        this.outputFile = inputFile.substring(0,inputFile.indexOf(".")) +".out";
        simulation = null;
    }

    /**
     * Reads the file and returns a BookScanningSimulation object
     */
    public BookScanningSimulation readFile(){

        return simulation;
    }

    /**
     * Writes the solution from the BookScanningSimulation object after the simulation has taken place
     */
    public void writeSolution(){

    }
}

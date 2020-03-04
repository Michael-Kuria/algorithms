package com.michael.hashcode.hashcode_2020;


import java.io.*;
import java.util.Scanner;

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
        Scanner scanner = null;
        try{
            scanner = new Scanner(new FileReader(new File("src/com/michael/hashcode/hashcode_2020/resources/" + inputFile)));
            //Read the Information about this set of libraries
            String [] fileInfo = scanner.nextLine().split(" ");
            int b = Integer.parseInt(fileInfo[0]);
            int l = Integer.parseInt(fileInfo[1]);
            int d = Integer.parseInt(fileInfo[2]);

            simulation = new BookScanningSimulation(b,l,d);

            // Read all the books
            String [] books = scanner.nextLine().split(" ");

            for(int i = 0 ; i < b; i ++){
                int score = Integer.parseInt(books[i]);
                simulation.allBooks.add(new Book(i,score));
            }

            //Read all the libraries and record their details
            for(int j = 0 ; j < l ; j ++){
                String [] libInfo = scanner.nextLine().split(" ");
                int n = Integer.parseInt(libInfo[0]);
                int t = Integer.parseInt(libInfo[1]);
                int m = Integer.parseInt(libInfo[2]);

                Library library = new Library(j, n, t, m );

                String [] bookIds = scanner.nextLine().split(" ");

                for(int k = 0; k < n; k ++){
                    int id = Integer.parseInt(bookIds[k]);
                    simulation.allBooks.get(id).count += 1;
                    library.books.add(simulation.allBooks.get(id));

                }
                library.books.sort((o1, o2) -> ((Integer)o2.score).compareTo(o1.score));
                library.updateScoreList();
                simulation.allLibraries.add(library);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            scanner.close();
        }

        return simulation;
    }

    /**
     * Writes the solution from the BookScanningSimulation object after the simulation has taken place
     */
    public void writeSolution(){
        PrintWriter writer = null;
        try{

            writer = new PrintWriter(new File("src/com/michael/hashcode/hashcode_2020/solution/" + outputFile));

            if(!simulation.solution.isEmpty()){
                //System.out.println("Am here");
                writer.println(simulation.solution.size() );
                for(Library lib : simulation.solution){
                    writer.println(lib);
                }
            }else{
                System.out.println("The solution is null");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }

    }
}

package com.michael.hashcode.hashcode_2020;

import java.util.*;

public class BookScanningSimulation {

    public int B; // number of books
    public int L; // number of libraries
    public int D; // number of days

    public int counter = D; // keeps track of the number of days remaining

    public LinkedList<Library> allLibraries; // stores all the libraries as contained in the file.
    public ArrayList<Library> simulationLibraries; // an updated list of libraries
    public ArrayList<Book> allBooks; // stores all the books according to their id
    public LinkedList<Library> solution; // will contain the final solution
    public HashMap<Integer,Boolean> scannedBooks; // a set that will keep track of the scanned books
    public int score = 0;


    // a constructor that will initialize the above variables
    public BookScanningSimulation(int B, int L, int D){
        this.B = B;
        this.L = L;
        this.D = D;

        allBooks = new ArrayList<>();
        allLibraries = new LinkedList<>();
        simulationLibraries = new ArrayList<>();
        solution = new LinkedList<>();
        scannedBooks = new HashMap<>(); //  this will improve our search for the scanned books
    }

    public void intializeScannedBooks(){
        for(Book bk : allBooks){
            if(bk.count > 0){
                scannedBooks.put(bk.id , false);
            }
        }
    }

    /**
     * This function will update our simulation Libraries once the list of libraries {allLibraries} have been sorted or
     * shuffled.
     * It will update it by ensuring that no book is contained more than once in all libraries.
     * The update is happening linearly. {NOT the best approach for some of the data set}
     */
    public void updateSimulationLibraries(){

        intializeScannedBooks();
        simulationLibraries = new ArrayList<>();
        simulationLibraries.addAll(allLibraries);

        for(Library lib : simulationLibraries){

            List<Book> newList = new LinkedList<>();
            for(Book bk: lib.books){

                int bookId = bk.id;
                if(allBooks.get(bookId).count > 1){ // Check if the book is contained more than once in the libraries

                    if(!scannedBooks.get(bookId)){ // check if the book is already added in any other library
                        newList.add(bk);
                    }
                }else{

                    newList.add(bk);
                }
                scannedBooks.put(bookId,true);
            }

            // Let this library have a new list of books and update the list of scores and set scanned to false
            lib.books = newList;
            lib.updateScoreList();
            lib.scanned = false;
            //System.out.println(lib);

        }
        //System.out.println(" >>>>>>>>>");
        // ensure the there are no scanned books, for the scanning process to begin



    }



    /**
     * This function will go through the simulation Libraries List (linearly) ensuring that the score is maximised
     * i.e the books scanned will have the best score considering the number of scanning days remaining
     */
    public void scan(){

        int i = 0;
        int prevScore = 0;
        int bestIndex = 0;
        int tempCounter;
        // This loop will go through the list of libraries picking the best libraries first linearly
        while(i < simulationLibraries.size()){

            tempCounter = counter;
            int currentScore = 0;
            Library lib = simulationLibraries.get(i);

            tempCounter -= lib.T;

            if(tempCounter < 0){
                //currentScore = 0;
            }else{

                int size = lib.scoreList.size();
                currentScore = size > tempCounter? lib.scoreList.get(tempCounter ) : lib.scoreList.isEmpty() ? 0 : lib.scoreList.get(size - 1);
                //System.out.println("Score CURRENT " + currentScore);
            }

            if(currentScore > prevScore){

                prevScore = currentScore;
                bestIndex = i;

            }else{
                if( currentScore == 0 && prevScore == 0){
                    bestIndex = i;
                }
                Library prevLibrary = simulationLibraries.get(bestIndex);
                if(prevLibrary.books.size() > 0){

                    counter -= prevLibrary.T;
                    solution.add(prevLibrary);
                    simulationLibraries.get(bestIndex).scanned = true;
                    i = bestIndex;
                    score += prevScore;
                    prevScore = 0;

                }


            }

            i ++;
        }

       // System.out.println("Counter " + counter);

        // This loop will do the clean up { ensuring we have completely maximised our score}
        if (counter > -1){
            for(Library lib : simulationLibraries){
                if(!lib.isScanned() && (counter - lib.T) > -1){

                    if(lib.books.size() > 0){
                        counter -= lib.T;
                        //expected score =
                        int size = lib.scoreList.size();
                        int score = size > counter? lib.scoreList.get(counter) : lib.scoreList.isEmpty() ? 0 : lib.scoreList.get(size - 1);
                        this.score += score;
                        solution.add(lib);
                        lib.scanned = true;
                    }


                }

                if(counter < 0){
                    break;
                }
            }

        }

    }


    /**
     * This function will shuffle, sort the array several times and pick the best solution of all
     */
    public void solve(){

        int i = 0;
        LinkedList<Library> bestSolution = null;
        int bestScore = 0;


        while(i < 10){


            bestSolution = new LinkedList<>();
            solution = new LinkedList<>();
            score = 0;
            counter = D;

            if(i == 0){
                allLibraries.sort((o1, o2) -> ((Integer)o1.T).compareTo(o2.T));
            }else if( i == 1){

                allLibraries.sort((o1, o2) -> ((Integer)o2.N).compareTo(o1.N));

            }else if(i == 2){

                allLibraries.sort((o1, o2) -> ((Integer)o2.scoreList.get(o2.scoreList.size() - 1)).compareTo(o1.scoreList.get(o1.scoreList.size() - 1)));

            }else{

                Collections.shuffle(allLibraries, new Random());
            }

            updateSimulationLibraries();
            scan();

            System.out.println("Score at "+ i + " is " + score);
            if(score > bestScore){
                System.out.println("Score at " + i + " is " + score);

                bestScore = score;
                if(!solution.isEmpty())
                   bestSolution = solution;
            }

            i ++;
        }
        score = bestScore;
        if(!bestSolution.isEmpty())
            solution = new LinkedList<>(bestSolution);

    }

    public static void main(String [] args){
        //{"a_example.txt","b_read_on.txt","c_incunabula.txt","d_tough_choices.txt", "e_so_many_books.txt","f_libraries_of_the_world.txt"}

        String [] fileNames = {"f_libraries_of_the_world.txt"};

        for(int i = 0; i < fileNames.length; i ++){
            String fileName = fileNames[i];
            System.out.println("Solving >>>>> " + fileName);

            FileManager fileManager = new FileManager(fileName);
            BookScanningSimulation simulation = fileManager.readFile();

            simulation.solve();
            fileManager.writeSolution();
            System.out.println("Score >>> : " + simulation.score);
        }
    }



}

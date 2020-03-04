package com.michael.hashcode.hashcode_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a single library
 */
public class Library {
    public int id;
    public int T; //sign up days
    public int N; // number of books in the library
    public int M; // number of books that can be scanned in a day
    public boolean scanned; // true if the library is scanned, false otherwise
    public List<Book> books; // list of books contained in the library
    public ArrayList<Integer> scoreList; // keep track of the score for each scanning day i.e from day 0 till all books are scanned


    public Library(int id, int N, int T, int M){
        this.id = id;
        this.N = N;
        this.T = T;
        this.M = M;
        this.scanned = false;

        this.books = new ArrayList<>();
        this.scoreList = new ArrayList<>();
    }

    /**
     * This function will keep the scoreList
     */
    public void updateScoreList(){
        boolean completed = false;
        int prevIndex = 0;
        int score = 0;
        int j = 0;
        while (!completed){


            for(int i = 0 ; i < M ; i ++){

                if((i + prevIndex) < books.size()){
                    score += books.get(i + prevIndex).score;
                }else{
                    completed = true;
                    break;
                }

            }
            prevIndex += M;
            //System.out.print("Score " + score +" and j " + j + " ");
            scoreList.add(j, score);
            j ++;
        }
        //System.out.println();
    }

    public boolean isScanned(){
        return scanned;
    }


    @Override
    public String toString(){
        String lib = "";
        lib += id + " "+ books.size()  +"\n";

        for(Book bk : books){
            lib += bk+" ";
        }

        return lib ;
    }

}

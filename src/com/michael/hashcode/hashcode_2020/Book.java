package com.michael.hashcode.hashcode_2020;

/**
 * This class contains the details of a single book
 */
public class Book {
    public int id;
    public int score;
    public int count;

    public Book(int id, int score){
        this.id = id;
        this.score = score;
        this.count = 0;
    }


    @Override
    public String toString(){

        return id +"";
    }
}

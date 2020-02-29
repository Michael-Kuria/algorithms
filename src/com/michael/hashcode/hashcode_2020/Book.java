package com.michael.hashcode.hashcode_2020;

/**
 * This class contains the details of a single book
 */
public class Book {
    public int id;
    public int score;

    public Book(int id, int score){
        this.id = id;
        this.score = score;
    }


    @Override
    public String toString(){
        return id + "";
    }
}

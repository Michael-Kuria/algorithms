package com.michael.hashcode.hashcode_2020;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represent a single library
 */
public class Library {
    public int id;
    public int T; //sign up days
    public int N; // number of books in the library
    public int M; // number of books that can be scanned in a day
    public List<Book> books; // a list of books contained in the library

    public Library(int id, int N, int T, int M){
        this.id = id;
        this.N = N;
        this.T = T;
        this.M = M;

        this.books = new LinkedList<>();
    }


    @Override
    public String toString(){
        String lib = "";
        lib += books.size() +"\n";

        for(Book bk : books){
            lib += bk +" ";
        }

        return lib + "\n";
    }

}

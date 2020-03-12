package com.michael.Inversions;

public class Inversions {

    /**
     * This function will incrementally scan the array and check for inversions i.e
     * where i < j and array[i] > array[j]
     * @param array array to check for inversions
     * @return the number of inversions
     */
    public static int inversionsIncrementally(int [] array){
        int count = 0;

        for(int i = 0; i < array.length; i ++){

            for(int j = i + 1; j < array.length; j ++){
                if(array[i] > array[j]){
                    count ++;
                }
            }
        }

        return count;
    }


    public static int inversionsDivideAndConquer(int [] array){
        int count = 0;

        return count;
    }

    public static void main(String [] args){
        int [] array = {2, 3, 8, 6, 1};

        System.out.println("Number of inversion using Incremental Method " + inversionsIncrementally(array));
    }
}

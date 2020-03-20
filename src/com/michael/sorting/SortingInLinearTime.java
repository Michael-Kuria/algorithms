package com.michael.sorting;

import java.util.Arrays;

public class SortingInLinearTime {
    /**
     * This sorting algorithm is used in sorting special cases of int arrays, the range of this values is known to be
     * from 0 - k.
     * @param array the array to be sorted
     * @param k the largest number in the {@code array}
     */
    public static void countingSort(int [] array,int k){

        int [] arrayCopy = Arrays.copyOf(array,array.length);
        // keep count of each number in the array, with it's index
        int [] keyCount = new int[k + 1];

        for(int i = 0; i < arrayCopy.length; i ++)
            keyCount[arrayCopy[i]] ++;

        for(int i = 1; i <= k; i ++){
            keyCount[i] = keyCount[i] + keyCount[i - 1];
        }

        for(int i = arrayCopy.length - 1; i >= 0 ; i --){
            array[keyCount[arrayCopy[i]] - 1] = arrayCopy[i];
            keyCount[arrayCopy[i]] --;

        }


    }



    public static void main(String [] args){
        int [] array = {6,0,2,0,1,3,4,6,1,3,2};
        System.out.println("Unsorted Array >>>");
        Sorting.print(array);
        countingSort(array, 6);
        System.out.println("Sorted Array >>>>");
        Sorting.print(array);
    }
}

package com.michael.duplicateNumFinder;

import java.util.HashSet;
import java.util.Set;

/**
 * A Java program for solving the FirstDuplicate problem. The problem definition {First Duplicate.pdf} can be obtained in this package.
 *
 * The following solutions will be presented :
 *  - O(n^2) with constant space
 *  - O(n) with O(n) space
 *  - O(n) with constant space
 *
 *
 */
public class FirstDuplicate {

    /**
     * A brute force solution: takes O(n^2).
     *
     * Go through the {@code array} incrementally keeping track of the second duplicate index for any number
     * then return the one with the smallest index.
     * @param array
     * @return the first duplicate found and -1 otherwise
     */
    public static int bruteFirstDuplicate(int [] array){

        int sol = array.length;
        for(int i = 0; i < array.length - 1; i ++){
            for(int j = i + 1; j < array.length; j ++){
                if(array[i] == array[j] && j < sol){
                    sol = j;
                }
            }
        }
        return sol == -1 ? -1 : array[sol];
    }

    /**
     * This function takes linear time O(n) but has O(n) space.
     *
     * @param array
     * @return the first duplicate found and -1 otherwise
     */
    public static int extraSpaceFirstDuplicate(int [] array){
        Set<Integer> set = new HashSet<>(); // finding an item in a hash-set will take O(1) for a small input

        for(int i = 0; i < array.length; i ++){
            if(set.contains(array[i])){
                return array[i];
            }else{
                set.add(array[i]);
            }
        }

        return -1;
    }


    /**
     * This function will take O(n) time.
     *
     * Since the numbers available are in a given range i.e [1,n], then any number in the array (x - 1) can
     * act as an index of the array. Thus once a number is visited change the corresponding index to -ve,
     * this implies that when a -ve value is found this mean that it is a duplicate.
     *
     * @param array argument
     * @return the first duplicate found and -1 otherwise
     */
    public static int linearFirstDuplicate(int [] array){

        for(int i = 0; i < array.length; i ++){

            int key = Math.abs(array[i]) - 1;
            if(array[key] < 0){
                return Math.abs(array[i]);
            }else{
                array[key] = - array[key];
            }
        }

        return -1;
    }

    public static void main(String [] args){
        int [] array = {1,2,3,4,3,2};
        System.out.println("First duplicate: \nBrute-Approach " + bruteFirstDuplicate(array) +"\nExtra Space " + extraSpaceFirstDuplicate(array)
        + "\nLinear Time " + linearFirstDuplicate(array));
    }
}

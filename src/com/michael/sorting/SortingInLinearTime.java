package com.michael.sorting;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Sorting in linear time
 *
 * @author Kush
 */
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



    /**
     * This function will be used as a step in bucketSort. In cases where the linkedList has a size greater than 1
     * convert the List to an array, and then sort the array using insertion sort.
     *
     * @param a List to be sorted
     * @return if {@code a} size == 1 return the list, else a sorted list
     */
    public static LinkedList<Double> insertionSort(LinkedList<Double> a){
        if(a.size() == 1){
            // already sorted hence no need to sort
            return a;
        }

        double [] b = new double[a.size()];

        int i = 0;
        // place every element in @param a to array b
        for(Double d : a){
            b[i] = d;
            i ++;
        }
        //print(b);


        // sort using insertion sort
        for(int j = 1; j < b.length ; j ++){
            for(int k = 0; k < j ; k ++ ){
                if(b[j] < b [k]){
                    double temp = b[k];
                    b[k] = b[j];
                    b[j] = temp;

                }
            }
        }

        // place the sorted elements to a List
        LinkedList<Double> ans = new LinkedList<>();
        for(int m = 0; m < b.length; m ++){
            ans.addLast(b[m]);
        }

        return ans;
    }


    /**
     * This sorting algorithm is used in special cases when the elements to be sorted are
     * uniformly and independently distributed in the range [0,1).
     *
     * The elements will first be placed in an array of 'buckets' (LinkedList) based on their value i.e 0.11 and 0.12 will be placed on the
     * same 'bucket', ordered according to how they follow one another in {@code a}
     *
     * @param a array to be sorted
     */
    public static void bucketSort(double [] a){
        LinkedList<Double> [] b = new LinkedList[a.length];

        // Place the elements of the array in an array of LinkedList based on their value
        for(int i = 0; i < a.length ; i ++){
            int index = (int)(a[i] * a.length);

            if(b[index] == null){

                b[index] = new LinkedList<>();
            }

            b[index].addLast(a[i]);
        }


        int k = 0;
        for(int j = 0; j < b.length; j ++){
            if(b[j] != null){
                b[j] = insertionSort(b[j]);
                for(Double d : b[j]){
                    a[k] = d;
                    k ++;
                }
            }
        }

    }


    /**
     * Print the elements of a double array
     * @param array
     */
    public static void  print(double [] array){
        for(int i = 0; i < array.length; i ++){
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }




    public static void main(String [] args){
       /* int [] array = {6,0,2,0,1,3,4,6,1,3,2};
        System.out.println("Unsorted Array >>>");
        Sorting.print(array);
        countingSort(array, 6);
        System.out.println("Sorted Array >>>>");
        Sorting.print(array);*/

        double [] a = {.78, .17,.39,.26,.72,.94,.24,.12,.27,.68,.99,.32,.25,.17,.21,.11,.87,.56,.46,.54};
        System.out.println("Unsorted Array");
        print(a);
        bucketSort(a);
        System.out.println("Sorted Array");
        print(a);
    }
}

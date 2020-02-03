package com.michael.sorting;

/**
 * The sorting algorithms presented in this class will sort the given int array in an ascending order
 *
 */
public class Sorting {

    public static void bubbleSort(int [] array){
        // i = -1 since array till the ith position is already sorted{avoids comparison between the jth and ith position}
        for(int i = -1; i < array.length-1; i ++){
            for(int j = array.length - 1; j > i+1; j --){
                if(array[j] < array[j-1]){
                    //swap
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                }


            }
        }
    }


    public static void insertionSort(int [] array){
        for(int i = 1; i < array.length; i ++){
            for(int j = 0; j < i; j++){
                if(array[j] > array[i]){
                    //swap
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }

            }
            /*System.out.println("When i = "+ i);
            print(array);*/
        }
    }


    /**
     * This sorting algorithm will first go through the entire array look for the smallest element
     * then place it in it's right position
     * @param array
     */
    public static void selectionSort(int[] array){
        for(int i = 0; i < array.length ; i ++){
            int smallestIndex = i;
            int j;
            for( j = i; j < array.length ; j ++){
                if(array[j] < array[smallestIndex]){
                    smallestIndex = j;
                }
            }
            //swap the element at the ith and jth position
            int temp = array[i];
            array[i] = array[smallestIndex];
            array[smallestIndex] = temp;

        }

    }



    /**
     * Merge sort will recursively divide the array into 2 until it is trivial(one element in the array) ->
     * then merge the array's section while sorting them
     *
     * @param array
     */

    public static void mergeSort(int [] array){
        //base case
        if(array.length == 1){
            return;
        }

        while(){
            //int start =
            int mid = array.length/2;
        }

    }

    /**
     * Merge the given array
     */

    public static void merge(int[]array, int start, int mid, int end){

    }

    /**
     * The following function prints the array in the params
     * @param array
     */

    public static void print(int[] array){
        for(int k= 0; k < array.length; k ++){
            System.out.print(array[k] + "   ");
        }
        System.out.println();

    }

    public static void main(String [] args){
        int[] array = {20,5,9,0,1,3,80,15,67,54,200,23,0,400};

        print(array);
        selectionSort(array);
        print(array);

    }
}

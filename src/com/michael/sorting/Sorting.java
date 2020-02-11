package com.michael.sorting;

import java.util.Arrays;

/**
 * The sorting algorithms presented in this class will sort the given int array in an ascending order
 *
 */
public class Sorting {

    /**
     * This algorithm sorts the array by finding the smallest element from the back of the array
     * and then push it until it's in the right position
     *
     * @param array array to be sorted
     */
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


    /**
     * This algorithm ensures that a section of the array is sorted i.e from 0 - j
     *
     * @param array the array to be sorted
     */
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
        int [] newArray = new int[array.length];
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

    public static void  mergeSort(int [] array,int start, int end){


        if(end <= start ) {
            return;
        }
        int mid = (start+end)/2;

        mergeSort(array, start,mid);
        mergeSort(array,mid+1,end);
        merge(array,start,mid,end);

    }

    /**
     * This function will merge & sort the @Param array from [start:mid] & [mid+1:end]
     * @param array
     * @param start the 0 index of the given portion of the array
     * @param mid the middle index of the given portion of the array
     * @param end the final index  "   "    "   "        "   "  "
     */

    public static void merge(int[]array, int start, int mid, int end){
        //make a copy of the array from index start:end
        int[] newArray = Arrays.copyOf(array,array.length);

        int i = start;
        int j = mid + 1;
        int k = start;

        while(i <= mid  && j <= end ){
            if(newArray[i] < newArray[j]){
                array[k] = newArray[i];
                i ++;
                k ++;
            }else {
                array[k] = newArray[j];
                j ++;
                k ++;
            }
        }

        while(i <= mid){
            array[k] = newArray[i];
            k ++;
            i ++;
        }

        while(j <= mid){
            array[k] = newArray[j];
            k ++;
            j ++;
        }
        printTheDifference(array, newArray);

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

    /**
     * This function show the difference between 2 arrays {Good for visualizing merge-sort}
     * @param array
     * @param array2
     */

    public static void printTheDifference(int[] array, int [] array2){
        for(int i = 0; i < array.length; i ++){
            if(array[i] == array2[i]){
                System.out.print("::" + "   ");
            }else{
                System.out.print(array[i] + "   ");
            }

        }
        System.out.println();
    }

    public static void main(String [] args){
        int[] array = {20,5,9,0,1,3,80,15,67,54,200,23,0,400,67,454,35662,45,687,45,0};

        print(array);
        //selectionSort(array);
        mergeSort(array,0,array.length-1);
        print(array);


    }
}

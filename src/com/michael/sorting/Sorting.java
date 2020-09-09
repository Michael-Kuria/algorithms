package com.michael.sorting;

import java.util.Arrays;

/**
 * The sorting algorithms presented in this class will sort the given int array in ascending order.
 * For simplicity the algorithms are sorting an array of integers.
 *
 * @author Kush
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
     * This sorting algorithm will first go through the entire array looking for the smallest element
     * then place it in it's right position
     *
     * @param array array to be sorted
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
        mergeTwo(array,start,mid,end);

    }

    /**
     * This function will merge & sort the @Param array from [start:mid] & [mid+1:end]
     * @param array
     * @param start the 0 index of the given portion of the array
     * @param mid the middle index of the given portion of the array
     * @param end the final index  "   "    "   "        "   "  "
     */

    public static void merge(int[]array, int start, int mid, int end){
        //make a copy of the original array
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

        /*
            In case any section of the array completed before the other the following loops will handle that.
            Note with the mergeSentinel & mergeTwo functions this step is skipped since the sentinel values will take care of it.
         */

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
     * In the following version of merge. Copy just the required section of the right and left array.
     * Put a sentinel value at the end of each array, i.e a big integer value. This will remove the need
     * of checking if the left or right array is empty so that we can update the original array accordingly
     *
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static void mergeWithSentinel(int[] array, int start, int mid , int end){

        /*
        The Arrays.copyRange function will copy the given range from ( start) to (mid + 1)
         */
        int [] leftArray = Arrays.copyOfRange(array, start, mid + 2); // to is exclusive
        int [] rightArray = Arrays.copyOfRange(array,mid + 1, end + 2);

        leftArray[leftArray.length - 1] = Integer.MAX_VALUE; // replacing the last value (mid + 1) value with the sentinel value
        rightArray[rightArray.length - 1] = Integer.MAX_VALUE;


        int i = 0;
        int j = 0;
        int k = start;

        while(k <= end){
            if(leftArray[i] <= rightArray[j]){
                array[k] = leftArray[i];
                i ++;

            }else{
                array[k] = rightArray[j];
                j ++;
            }

            k ++;
        }

    }

    /**
     * This version of merge avoids adding the sentinel values and makes use of the if statement to ensure
     * that all elements are processed.
     *
     * @param array
     * @param start
     * @param mid
     * @param end
     */
    public static void mergeTwo(int [] array, int start, int mid, int end){

        int [] leftSubArray = Arrays.copyOfRange(array, start, mid + 1);
        int [] rightSubArray = Arrays.copyOfRange(array, mid + 1, end + 1 );

        int i = 0; // for left sub array
        int j = 0; // for right sub array
        int k = start; // for updating {@code array}

        while(i < leftSubArray.length || j < rightSubArray.length){

            if((j >= rightSubArray.length) || i < leftSubArray.length && leftSubArray[i] <= rightSubArray[j]){
                array[k] = leftSubArray[i];
                i ++;
            }else{
                array[k] = rightSubArray[j];
                j ++;
            }
            k ++;
        }
    }


    /**
     * This function will ensure that the elements less than the pivot will be on the left and elements
     * greater than the pivot will be on thee right.
     *
     * The pivot will be selected as the first element on the section of the array i.e @param end
     *
     * @param array
     * @param start
     * @param end
     * @return the index of the pivot element
     */
    public static int quickSortStep(int [] array, int start, int end){
        if(end == start || end < start){
            return -1;
        }
        int pivot = array[end];

        for(int i = start ; i < end ; i ++){

            //@param start will keep track of the element greater than the pivot, and store it for later for the final swap.
            // i will always go linearly but not to the @param end
            if(array[i] < pivot){
                if(start != i ){
                    int temp = array[i];
                    array[i] = array[start];
                    array[start] = temp;
                }

                start ++; // note that this is only updated when there is a swap to be done
            }
        }

        //Remember to keep the pivot at it right position
        array[end] = array[start];
        array[start] = pivot;

        return start;

    }

    /**
     * Recursively call the quickSortStep function until the array is sorted
     * @param array
     * @param start
     * @param end
     */
    public static void quickSort(int[] array,int start, int end){

        int pivotIndex = quickSortStep(array, start, end);

        if(pivotIndex == -1){
            return;
        }

        quickSort(array,start,pivotIndex - 1 );
        quickSort(array,pivotIndex + 1 ,end);

    }

    /**
     * The following function prints the array in the params
     * @param array
     */

    public static void print(int[] array){

        if(array == null || array.length == 0){
            System.out.println("Empty Array ");
            return;
        }

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
        /*int[] array = {20,5,9,0,1,3,80,15,67,54,200,23,0,400,67,454,35662,45,687,45,0};

        print(array);
        //selectionSort(array);
        mergeSort(array,0,array.length-1);
        //quickSort(array,0,array.length-1);
        print(array);*/

        int a [] = {5,3,6,7,1,2,4,0,8};

        System.out.println(Arrays.toString(a));
        quickSort(a,0 , a.length - 1);
        System.out.println(Arrays.toString(a));


    }
}

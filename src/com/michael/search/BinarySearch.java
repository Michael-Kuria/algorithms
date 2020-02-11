package com.michael.search;

/**
 * This class implements the binary search algorithm on an integer array.
 * @author Kush
 */
public class BinarySearch {

    /**
     * For the function to work the array needs to be <code>sorted</code>
     *
     * The worst case complexity for the function is O(log n)
     *
     * @param value the value to be searched
     * @param array the array to search in
     * @param start the index in the array to start from
     * @param end the end index
     * @return the index of the value and -1 otherwise
     */
    public static int binarySearch(int value, int[] array, int start, int end){
        //base case
        if(start > end){
            return -1;
        }

        int mid = (start + end)/ 2;

        if(array[mid] == value){
            return mid;
        }else if(array[mid] > value){
            //search the lower-half of the array i.e start -> (mid - 1)
            return binarySearch(value,array,start,mid-1);
        }else if(array[mid] < value){
            //search the upper-half of the array i.e (mid + 1) -> end
            return binarySearch(value,array,mid+1, end);
        }

        return -1;
    }



    /**
     * The function is similar to the binary search algorithm only that in this case we are looking for the best index i where
     * array[i] < value > array[i+1]
     * @param value
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int binarySearchBestIndex(int value, int[] array, int start, int end){
        //base case
        if(start > end){
            return 0;
        }

        int mid = (start + end)/ 2;

        if(mid != end){
            if(array[mid] <= value && array[mid+1] >= value) {
                //System.out.println("Am in");
                return mid;
            }
        }else{
            return  mid;
        }

        if(array[mid] > value){
            //search the lower-half of the array i.e start -> (mid - 1)
            return binarySearchBestIndex(value,array,start,mid-1);
        }else if(array[mid] < value){
            //search the upper-half of the array i.e (mid + 1) -> end
            return binarySearchBestIndex(value,array,mid+1, end);
        }

        return 0;
    }



    /**
     * This <code>insertionSort</code> implementation makes use of binary search to avoid unnecessary comparison.
     * This implementation can have a complexity of O(n*log n) which is better than the normal <code>insertionSort</code>
     * which has a complexity of O(n * n)
     *
     *
     * @param array
     */
    public static void insertionSortWithBinarySearch(int[] array){
        for(int i = 0; i < array.length; i ++){
            int j = binarySearchBestIndex(array[i], array, 0, i);
            while(j < i){
                if(array[i] < array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
                j ++;
            }
        }
    }



    /**
     * Prints array in a pretty way
     * @param array
     */
    public static void printArray(int [] array){
        System.out.print("{");
        for(int i = 0; i < array.length; i ++){
            if(i != array.length - 1)
                System.out.print(array[i] + ", ");
            else
                System.out.print(array[i]);
        }
        System.out.println("}");
        System.out.println();
    }


    //Testing
    public static void main(String [] args){

        int [] array = {1,2,5,12,12,3,4,567,74,1,5,23,0};

        System.out.println("The unsorted array .......");
        printArray(array);
        insertionSortWithBinarySearch(array);
        System.out.println("The sorted array .......");
        System.out.println();
        printArray(array);

        int value = 120;
        int index = binarySearch(value,array,0,array.length-1);
        if(index == -1){
            System.out.println("The value : "+value+" could not be found");
        }else{
            System.out.println("The value : "+value+" was found on index : "+index);
        }


    }
}

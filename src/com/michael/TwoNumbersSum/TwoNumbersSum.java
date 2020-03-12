package com.michael.TwoNumbersSum;

import java.util.Arrays;

/**
 * A Java program to find two numbers in an array such that the sum of them will give a number X
 * {Look at the problem statement in the folder}
 *
 */
public class TwoNumbersSum {

    public int x; // the sum of the numbers
    public int[] array; // The array to search for the numbers;

    public TwoNumbersSum(int x, int[] array){
        this.x = x;
        this.array = array;

        Arrays.sort(array);
    }

    /**
     * Performs binary search on a section of the array, to look for num
     *
     * @param num the number we are looking for
     * @param start where to begin searching in the array
     * @param end end of the array
     * @return true if num has been found and false otherwise
     */
    public boolean binarySearch(int num,int start, int end){
        if(start > end){
            return false;
        }

        int mid = (start + end)/ 2;

        if(array[mid] == num){
            return true;
        }else if(array[mid] > num){
            return binarySearch(num,start, mid - 1);
        }else{
            return binarySearch(num,mid + 1, end);
        }

    }


    /**
     * Scans the entire array looking of a pair of numbers whose sum is <code>this.x</code>
     */
    public int[] find(){
        int [] answer = {-1,-1};
        for(int i = 0 ; i < array.length ; i ++){
            int key = array[i];
            int num = x - key;


            if(binarySearch(num,i + 1, array.length - 1)){
                answer[0] = key;
                answer[1] = num;
            }
        }

        return answer;
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
            System.out.print(array[k] + "  ");
        }
        System.out.println();

    }

    public static void main(String [] args){
        int [] array = {54,34,465,343,64,1,34,2,4,24,13,2,8,7,6,10,11,4,6,45};

        TwoNumbersSum sum = new TwoNumbersSum(88,array);

        int[] ans = sum.find();

        if(ans[0] != -1){
            print(ans);
            System.out.print("Will give a sum of " + sum.x);
        }else{
            System.out.println("No values found that could give a sum of " + sum.x);
        }
    }

}

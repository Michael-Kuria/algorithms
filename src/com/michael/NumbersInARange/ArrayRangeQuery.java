package com.michael.NumbersInARange;

import com.michael.sorting.Sorting;

import java.util.Scanner;

/**
 * Given an array containing numbers in a given range return the number of integers in the range [a ,b ]
 *
 * For more information on the problem definition, find the problem statement in the class folder.
 *
 */
public class ArrayRangeQuery {

    /**
     * Find the running sum of the given array.
     *
     * @param array
     * @param k
     * @return running sum of the ordered array.
     */
    public static int[] getRunningSum(int [] array, int k){
        int [] runningSum = new int[k + 1];

        for(int i = 0; i < array.length; i ++){
            runningSum[array[i]] ++;
        }

        for(int i = 1 ; i <= k ;i ++){
            runningSum[i] += runningSum[i - 1];
        }

        return runningSum;
    }




    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        int [] array = {6,0,2,0,1,3,4,6,1,3,2};
        int k = 6;
        int [] runningSum = getRunningSum(array,k);
        Sorting.print(runningSum);

        while(true){
            System.out.print("Input a ? ");
            int a = scanner.nextInt();
            System.out.print("Input b ? ");
            int b = scanner.nextInt();

            if(a <= b && a <= k && b <= k ){
                if(a == 0){
                    System.out.println("The number of integers in range ["+a+", "+b+"] is " + runningSum[b]);
                }else{
                    System.out.println("The number of integers in range ["+a+", "+b+"] is " + (runningSum[b] - runningSum[a - 1]));
                }

            }else{
                System.out.println("Enter the correct range a <= b <= "+k);
            }

        }


    }
}

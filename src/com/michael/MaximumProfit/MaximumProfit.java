package com.michael.MaximumProfit;

import javafx.util.Pair;

/**
 * A Java program to find the maximum profit given an array of stock prices
 *
 */
public class MaximumProfit {

    /**
     * A brute force approach of finding the maximum profit.
     *
     * Try all the possible pairs incrementally and return the highest profit
     *
     * @param array the stock prices
     * @return the indices indicating when to buy and when to sell and the expected profit
     */
    public static Pair<Pair<Integer,Integer>, Integer> bruteForce(int [] array){

        int bestProfit = 0;
        int leftIndex = -1;
        int rightIndex = -1;

        for(int i = 0; i < array.length - 1; i ++){
            for(int j = i + 1; j < array.length; j ++){

                int profit = array[j] - array[i];
                if(profit > bestProfit){
                    bestProfit = profit;
                    leftIndex = i;
                    rightIndex = j;
                }
            }

        }

        return new Pair<>(new Pair<>(leftIndex,rightIndex), bestProfit);

    }


    /**
     * This function will be called recursively by findMaxSubArray().
     *
     * find the maximum sum on the left of mid and on the right and then combine them.
     *
     * @param array array containing the differences of adjacent stock prices
     * @param low
     * @param mid
     * @param high
     * @return the indices demarcating the maxSubArray and the sum of the (maxSubArray) elements
     */
    public static Pair<Pair<Integer,Integer>, Integer> findMaxCrossingSubArray(int[] array, int low, int mid, int high){
        int leftSum = 0;
        int bestLeftSum = 0;
        int maxLeft = - 1;
        for(int i = mid; i >= low; i --){
            leftSum += array[i];
            //System.out.println(array[i]);
            if(leftSum > bestLeftSum){
                bestLeftSum = leftSum;
                maxLeft = i;
            }
        }

        int rightSum = 0;
        int bestRightSum = 0;
        int maxRight = - 1;

        for(int i = mid + 1; i <= high; i ++){
            rightSum += array[i];
            if(rightSum > bestRightSum){
                bestRightSum = rightSum;
                maxRight = i;
            }
        }

        return new Pair<>(new Pair(maxLeft,maxRight) , (bestLeftSum + bestRightSum));
    }

    /**
     * A recursive approach of finding the max sub-array
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static Pair<Pair<Integer,Integer>, Integer> findMaxSubArray(int [] array, int start, int end){

        if(end == start){
            return new Pair<>(new Pair<>(start, end),array[start]);
        }else{
            int mid = (start + end) / 2;

            Pair<Pair<Integer,Integer>, Integer> left = findMaxSubArray(array,start,mid);
            Pair<Pair<Integer,Integer>, Integer> right = findMaxSubArray(array,mid + 1,end);

            Pair<Pair<Integer,Integer>, Integer> crossing = findMaxCrossingSubArray(array,start,mid,end);

            if(left.getValue() >= right.getValue() && left.getValue() >= crossing.getValue()){
                return left;
            }else if(right.getValue() >= left.getValue() && right.getValue() >= crossing.getValue()){
                return right;
            }else{
                return crossing;
            }
        }

    }

    /**
     * This function implements Kandane's algorithm for finding the maximum sub-array
     * It is the fastest since it uses linear time.
     *
     * @param array
     * @return
     */
    public static Pair<Pair<Integer,Integer>, Integer> kadaneAlgorithm(int [] array){

        int currentProfit = 0;
        int bestProfit = 0;
        int leftIndex = -1;
        int rightIndex = -1;

        for(int i = 0 ; i < array.length; i ++){

            currentProfit = Math.max(array[i], currentProfit + array[i]);

            if(currentProfit == array[i]){
                leftIndex = i;
            }

            if(currentProfit > bestProfit){
                bestProfit = currentProfit;
                rightIndex = i;
            }

        }

        return new Pair<>(new Pair<>(leftIndex,rightIndex), bestProfit);

    }


    /**
     * Converts the given array, to an array containing difference of adjacent elements in @param array
     * @param array array to be converted
     */

    public static int[] convertArray(int[] array){
        int[] answer = new int[array.length - 1];

        for(int i = 1; i < array.length; i ++){
            answer[i - 1] = array[i] - array[i - 1];
        }

        return answer;

    }



    public static void main(String[] args){
        //{100,83,75,64,57,43,40,32,31,30,21,10,9};// {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
        int [] prices = {10,-2,3,-1,14,17,20,-23,10,-13};

        Pair<Pair<Integer, Integer>, Integer> answer = bruteForce(prices);

        if(answer.getKey().getValue() != -1){
            System.out.println("Maximum profit can be realized by buying at " + answer.getKey().getKey() +" and selling at " + answer.getKey().getValue());
            System.out.println("The profit will be " + (answer.getValue()));
        }else {
            System.out.println("Bad stock to trade with");
        }

        int [] priceDifferences = convertArray(prices);
        Pair<Pair<Integer,Integer>, Integer> answer2 = findMaxSubArray(prices, 0, priceDifferences.length - 1);


        System.out.println("Maximum profit with divide and conquer " + answer2.getValue());
        System.out.println("Start " + answer2.getKey().getKey() + " end " + answer2.getKey().getValue());

        Pair<Pair<Integer,Integer>, Integer> answer3 = kadaneAlgorithm(prices);

        System.out.println("Maximum profit with Kandane's Algorithm " + answer3.getValue());
        System.out.println("start " + answer3.getKey().getKey()+ "  end " +answer3.getKey().getValue());




    }




}

package com.michael.Inversions;

import java.util.Arrays;

public class Inversions {
    static int cnt = 0;

    /**
     * This function will incrementally scan the array and check for inversions i.e
     * where i < j and array[i] > array[j]
     * @param array array to check for inversions
     * @return the number of inversions
     */
    public static int inversionsIncrementally(int [] array){
        int count = 0;

        for(int i = 0; i < array.length; i ++){

            for(int j = i + 1; j < array.length; j ++){
                if(array[i] > array[j]){
                    count ++;
                }
            }
        }

        return count;
    }


    public static void mergeSort(int L, int H, int [] a){

        if(L < H){
            int mid = L + (H - L)/2;
            mergeSort(L, mid, a);
            mergeSort(mid + 1, H, a);
            merge(L,mid,H,a);
        }

    }

    public static void merge(int L, int mid, int H, int [] a){

        int tmp [] = Arrays.copyOf(a, a.length);

        int i = L, j = mid + 1, k = L;

        while(i <= mid || j <= H){

            if(i <= mid && j <= H && tmp[i] > tmp[j]){
                cnt += mid - i + 1;
            }

            if(j > H || (i <= mid && tmp[i] < tmp[j])){

                a[k] = tmp[i];
                i ++;
            }else{
                a[k] = tmp[j];
                j ++;
            }

            k ++;
        }

    }
    /*public static int inversionsDivideAndConquer(int [] array){
        int count = 0;

        return count;
    }*/



    public static void main(String [] args){
        int [] array = {2, 3, 8, 6, 1,0}; // {0,1,2,3,4,5,6};

        System.out.println("Number of inversion using Incremental Method " + inversionsIncrementally(array));

        mergeSort(0, array.length - 1, array);
        System.out.println(cnt);
    }
}

package com.michael.StringMatching;

/**
 * Knuth-Morris-Platt
 */
public class KMP {

    public static int[] computePrefix(String p){

        int m = p.length();
        int aux[] = new int[m];

        int q = 1, k = 0;

        for(;q < m; q ++){

            while(k > 0 && p.charAt(q) != p.charAt(k)) k = aux[k];

            if(p.charAt(q) == p.charAt(k)) k ++;
            aux[q] = k;
        }

        return aux;
    }

    public static void kmpMatcher(String t, String p){
        int n = t.length();
        int m = p.length();

        int aux[] = computePrefix(p);
        int q = 0, k = 0;
        for(;q < n; q ++){

            while( k > 0 && p.charAt(k) != t.charAt(q)) k = aux[k - 1];

            if(t.charAt(q) == p.charAt(k)) k ++;

            if(k == m){
                System.out.println("Pattern found beginning at : " + (q - m + 1));
                k = aux[k - 1];
            }

        }

    }


    public static void main(String [] args){

        String t = "abcxabcdabxabcdabcdabcy" , p = "abcdabcy";

        kmpMatcher(t,p);
    }
}

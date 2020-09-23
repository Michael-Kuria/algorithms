package com.michael.NumberTheory;

/**
 * computing x^n in O(log n) time
 */
public class BinaryExponentiation {


    public static long recursive(long x, long e){

        if(e == 0) return 1;

        long tmp = recursive(x, e/ 2);
        long result = tmp * tmp;

        if((e & 1) != 0)
            result *= x;

        return result;
    }


    /**
     * Iterative version
     * @param x
     * @param e the exponent
     * @return the result of x ^ e
     */
    public static long power(long x, long e){
        long result = 1;

        while(e > 0){
            if((e & 1) != 0)
                result *= x;
            x *= x;
            e /= 2;
        }

        return result;

    }



    public static void main(String [] args){
        int x = 3,  e = 17;

        System.out.println(recursive(x,e));
        System.out.println(power(x,e));
    }
}

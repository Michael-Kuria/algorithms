package com.michael.NumberTheory;

public class GCD {

    public static int euclid(int a, int b){

        if(b == 0) return a;

        return euclid(b, a % b);
    }


    public static int lcm(int a, int b){

        return (a * b)/euclid(a,b);
    }


    public static void main(String [] args){

        System.out.println(euclid(10,5));
        System.out.println(lcm(10,5));
    }
}

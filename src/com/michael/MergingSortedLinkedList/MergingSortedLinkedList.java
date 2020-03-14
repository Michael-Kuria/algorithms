package com.michael.MergingSortedLinkedList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class MergingSortedLinkedList {

    /**
     * This function will merge two sorted LinkedLinked into a single sorted LinkedList.
     *
     * {Implemented like the merge step in merge sort}
     *
     * @param a
     * @param b
     * @return
     */
    public static LinkedList<Integer> merge(LinkedList<Integer> a, LinkedList<Integer> b){

        LinkedList<Integer> merged = new LinkedList<>();

        boolean aVisited = false;
        boolean bVisited = false;

        Iterator<Integer> aItr = a.listIterator();
        Iterator<Integer> bItr = b.listIterator();
        Integer i = null, j = null;


        while(aItr.hasNext() && bItr.hasNext()){

            if(!aVisited){
                i = aItr.next();
                aVisited = true;
            }

            if(!bVisited){
                j = bItr.next();
                bVisited = true;
            }

            if(i.compareTo(j) <= 0){
                merged.addLast(i);
                aVisited = false;

            }else{
                merged.addLast(j);
                bVisited = false;
            }

        }

        /*if(!aItr.hasNext()){
            merged.addLast(i);
        }else{
            merged.addLast(j);
        }*/

        if(merged.getLast().equals(j)){
            merged.addLast(i);
        }else{
            merged.addLast(j);
        }

        aItr.forEachRemaining((integer) -> merged.addLast(integer));
        bItr.forEachRemaining((integer) -> merged.addLast(integer));

        /*while(aItr.hasNext()){
            merged.addLast(aItr.next());
        }

        while(bItr.hasNext()){
            merged.addLast(bItr.next());
        }*/

        return merged;

    }





    public static void main(String []  args){
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1,3,5,7,9,9));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(2,4,6,8,10,11,23,44,56));

        LinkedList<Integer> merged = merge(a,b);

        System.out.println(merged.toString());
    }
}

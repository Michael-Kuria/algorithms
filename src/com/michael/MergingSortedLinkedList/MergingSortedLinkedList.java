package com.michael.MergingSortedLinkedList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This problem posses problems in Java because of how LinkedList have been implemented.
 * The user has no direct access to the elements i.e the head and the tail.
 *
 * Attaining < O(n) solution will be difficult.
 */
public class MergingSortedLinkedList {


    /**
     * This function will combine the given list into a single List. Then sort the given list
     *
     * solution will take O((n+m) log (n+m)) and space O(n + m)
     * @param a
     * @param b
     * @return the merged List
     */
    public static  LinkedList<Integer> extraSpaceMerge(LinkedList<Integer> a, LinkedList<Integer> b){
        LinkedList<Integer> merge = new LinkedList<>();
        merge.addAll(a);
        merge.addAll(b);
        // This function will first convert the list in to an array then sort it using the binarySort= (insertion sort with binary search)
        Collections.sort(merge);
        return merge;
    }

    /**
     * This function will merge two sorted LinkedLinked into a single sorted LinkedList.
     *
     * {Implemented like the merge step in merge sort}
     *
     * solution will take O(n^2 + m^2) time and space O(n + m ) though the solution is wrong
     *
     * @param a
     * @param b
     * @return
     */
    public static LinkedList<Integer> mergeWithIterator(LinkedList<Integer> a, LinkedList<Integer> b){

        LinkedList<Integer> merged = new LinkedList<>();

        boolean aVisited = false;
        boolean bVisited = false;

        boolean aLast = false;
        boolean bLast = false;

        Iterator<Integer> aItr = a.listIterator();
        Iterator<Integer> bItr = b.listIterator();
        Integer i = aItr.next(), j = bItr.next();

        //int


        while(i != null && j != null){

            /*if(!aVisited && aItr.hasNext()){
                i = aItr.next();
                aVisited = true;
            }

            if(!bVisited && bItr.hasNext()){
                j = bItr.next();
                bVisited = true;
            }*/


            //System.out.println(" i : "+ i +" j : " + j +" List " + merged.toString());

            if(i.compareTo(j) <= 0){
                merged.addLast(i);
                //aVisited = false;
                aLast = true;
                bLast = false;
                if(aItr.hasNext()){
                    i = aItr.next();
                }
                else
                    break;


            }else{
                merged.addLast(j);
                bLast = true;
                aLast = false;
                if(bItr.hasNext()){
                    j = bItr.next();
                }
                else
                    break;
                //bVisited = false;

            }

            if(!aItr.hasNext() || !bItr.hasNext()){
                merged.addLast(i.compareTo(j) <= 0 ? i : j);
                /*System.out.println("Am here");
                System.out.println(merged);*/
            }

        }

        if(i.compareTo(j) >= 0){
            merged.addLast(i);

        }else{
            merged.addLast(j);

        }

        /*if(!aLast){
            merged.addLast(i);

        }

        if(!bLast){
            merged.addLast(j);
        }*/
        /*if(!aItr.hasNext()){
            merged.addLast(i);
        }else{
            merged.addLast(j);
        }*/

        /*if(merged.getLast().equals(j)){
            merged.addLast(i);
            System.out.println("Am here");
        }else{
            merged.addLast(j);
            System.out.println("Am here");
        }*/

        /*aItr.forEachRemaining((integer) -> merged.addLast(integer));
        bItr.forEachRemaining((integer) -> merged.addLast(integer));

        boolean foundSlot = false;
        while(aItr.hasNext()){
            Integer next = aItr.next();
            if(!foundSlot && remaining.compareTo(next) <= 0 )
                merged.addLast(remaining);
            else
                merged.addLast(next);
        }

        while(bItr.hasNext()){
            merged.addLast(bItr.next());
        }*/

        return merged;

    }


    /**
     * This function will attain the correct solution with O(n^2) time.
     *
     * This is implemented exactly like the merge step in merge sort.
     *
     * The get function will take O(n/2) time
     *
     * @param a
     * @param b
     * @return
     */
    public static LinkedList<Integer> mergeWithGet(LinkedList<Integer> a, LinkedList<Integer> b){

        LinkedList<Integer> merged = new LinkedList<>();

        int i = 0;
        int j = 0;

        Integer aInt = a.get(i);
        Integer bInt = b.get(j);

        while(i < a.size() && j < b.size()){

            if(aInt.compareTo(bInt) <= 0){
                merged.addLast(aInt);
                i ++;

                if(i < a.size()){
                    aInt = a.get(i);
                }
            }else{
                merged.addLast(bInt);
                j ++;

                if(j < b.size()){
                    bInt = b.get(j);
                }
            }

        }

        while(i < a.size()){
            merged.addLast(a.get(i));
            i ++;
        }

        while(j < b.size()){
            merged.addLast(b.get(j));
            j ++;
        }

        return merged;

    }





    public static void main(String []  args){
        //1,3,5,7,9,9 2,4,6,8,10,11,23,44,56
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1,1,1,3,3,4));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(1,1,2,2,2,4));

        LinkedList<Integer> merged = mergeWithIterator(a,b);

        System.out.println("Total size should be " + (a.size() + b.size()) +" merged size " + merged.size());
        System.out.println(merged.toString());

        System.out.println("Using extraSpaceMerge:");
        System.out.println(extraSpaceMerge(a,b));

        System.out.println("Using merge2");
        System.out.println(mergeWithGet(a,b));
    }
}

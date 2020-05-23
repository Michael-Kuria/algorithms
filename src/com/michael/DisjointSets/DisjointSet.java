package com.michael.DisjointSets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of Disjoint Set using trees in Java {Union with rank and path compression}
 * @param <T> the object
 */
public class DisjointSet<T> {

    public static class Node<T>{
        public Node p;
        public T data;
        public int rank;

        Node(T data){
            this.data = data;
            rank = 0;
        }

    }

    public Map<T,Node> ref = new HashMap<>();

    /**
     * Constructor with all the data that needs to be in the set
     * @param array
     */
    public DisjointSet(T ... array){

        for(int i = 0; i < array.length; i ++){

            makeSet(array[i]);
        }

    }

    /**
     * Given the data make a set
     * @param data
     */
    public void makeSet(T data){
        Node node = new Node(data);
        node.p = node;
        ref.put(data,node);
    }

    /**
     * Find set with path compression
     * @param data
     * @return
     */
    public Node<T> findSet(T data){
        Node array[] = new Node[50];

        int i = 0;

        Node n = ref.get(data);

        while(n != n.p){

            n = n.p;
            array[i] = n;
            i ++;
        }
        array[i] = new Node(null);

        // path compression
        for(int j = 0; j < array.length && array[j].data != null; j ++){

            array[j].p = n;
        }

        return  n;
    }

    /**
     * Union with rank
     * @param a
     * @param b
     */
    public void union(T a, T b){

        Node c = findSet(a);
        Node d = findSet(b);

        if(c == d){
            return;
        }

        if(c.rank == d.rank){

            d.p = c;
            c.rank += 1;
        }else if(c.rank > d.rank){
            d.p = c;
        }else{
            c.p = d;
        }

    }

    /**
     * Get a map of all connected components
     * @return
     */
    public Map<T,List<T>> getCurrentSet(){

        Map<T, List<T>> m = new HashMap<>();

        for(Map.Entry<T,Node> e : ref.entrySet()){
            Node node = e.getValue();
            T root = findSet((T) node.data).data;

            if(m.containsKey(root)){
                m.get(root).add((T) node.data);
            }else{
                List<T> l = new ArrayList<>();
                l.add((T) node.data);
                m.put(root, l);
            }

        }
        return m;
    }


    public static void main(String [] args){
        Character[] array = new Character[] {'a','b','c','d','e','f','g','h','i','j'};
        char[][] edges = new char[][] {{'a','b'},{'c','b'},{'a','c'},{'b','d'},{'e','f'},{'f','g'},{'h','i'}};

        DisjointSet<Character> ds = new DisjointSet<>(array);

        for(int i = 0; i < edges.length; i ++){

            ds.union(edges[i][0], edges[i][1]);
        }

        Map<Character, List<Character>> m = ds.getCurrentSet();

        for(Map.Entry<Character, List<Character>> e : m.entrySet()){

            for(Character c: e.getValue()){
                System.out.print(c + " ");
            }
            System.out.println();
        }


    }




}

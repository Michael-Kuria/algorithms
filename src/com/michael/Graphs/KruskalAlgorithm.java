package com.michael.Graphs;


import com.michael.DisjointSets.DisjointSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of kruskal's Algorithm using disjointSets
 *
 * Kruskal Algorithm is used to find the minimum spanning tree
 */
public class KruskalAlgorithm {

    public static class Triplex<T> implements Comparable<Triplex>{
        public T a,b;
        public int weight;

        public Triplex(T a, T b, int weight){

            this.a = a;
            this.b = b;
            this.weight = weight;
        }

        @Override
        public int compareTo(Triplex o) {
            return ((Integer)this.weight).compareTo(o.weight);
        }

        @Override
        public String toString(){
            return "("+a+","+b+", w = "+weight+")";
        }
    }


    public static List<Triplex<String>> kruskal(Triplex<String> [] edges, String[] array){

        List<Triplex<String>> ans = new ArrayList<>();
        DisjointSet<String> ds = new DisjointSet<>(array);

        Arrays.sort(edges);

        for(int i = 0; i < edges.length; i ++){
            Triplex<String> t = edges[i];

            if(ds.findSet(t.a).compareTo(ds.findSet(t.b)) != 0){
                ds.union(t.a,t.b);
                ans.add(t);
            }
        }

        return ans;
    }



    public static void main(String [] args){

        String [] c = new String[9];

        for(int i = 0; i < c.length; i ++){
            c[i] = Character.toString((char)('a' + i));
        }


        String [][] e = new String[][] {{"a","b","4"},
                {"b","c","8"},{"c","d","7"},
                {"d","e","9"},
                {"e","f","10"},
                {"f","g","2"},
                {"g","h","1"},
                {"h","i","7"},
                {"f","d","14"},
                {"f","c","4"},
                {"g","i","6"},
                {"i","c","2"},
                {"h","a","8"},
                {"h","b","11"}};

        Triplex<String> [] edges = new Triplex[e.length];

        for(int i = 0; i < e.length; i ++){

            edges[i] = new Triplex<>(e[i][0],e[i][1],Integer.parseInt(e[i][2]));

        }

        List<Triplex<String>> ans = kruskal(edges,c);

        int totalWeight = 0;
        for(Triplex<String> t :  ans){
            System.out.println(t);
            totalWeight += t.weight;
        }

        System.out.println("Total Weight " + totalWeight);

    }
}

package com.michael.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Topological Sort performed on a dag (a Graph without cycles)
 */
public class TopologicalSort {

    public static class Vertex{

        public int color, d,f;
        public String name;
        public Vertex p;
        public List<Vertex> adj;

        public Vertex(){
            color = 0;
            adj = new ArrayList<>();
        }

        @Override
        public String toString(){
            return "("+name+","+d+","+f+")";
        }
    }


    public static void dfsVisit(Vertex x, Counter c, LinkedList<Vertex> l){
        c.increament();
        x.d = c.a;

        x.color = 1;

        for(Vertex y: x.adj){

            if(y.color == 0){
                dfsVisit(y,c,l);
            }
        }
        c.increament();
        x.f = c.a;
        l.addFirst(x);
    }

    public static void dfs(Vertex [] v, Counter c, LinkedList<Vertex> l){

        for(int i = 0; i < v.length; i ++){

            if(v[i].color == 0){
                dfsVisit(v[i],c,l);
            }
        }
    }


    public static LinkedList<Vertex> topologicalSort(Vertex[] v){
        LinkedList<Vertex> l = new LinkedList<>();

        dfs(v,new Counter(),l);
        return l;
    }


    public static void print(LinkedList<Vertex> l){
        for(Vertex v: l){
            System.out.print(v + " ");
        }
        System.out.println();
    }


    public static void main(String [] args){
        Vertex [] v = new Vertex[9];

        for(int i = 0; i < v.length; i ++){
            v[i] = new Vertex();
        }

        v[0].name = "undershorts";
        v[0].adj.addAll(Arrays.asList(new Vertex[]{v[1],v[2]}));
        v[1].name = "pants";
        v[1].adj.addAll(Arrays.asList(new Vertex[]{v[2],v[3]}));
        v[2].name = "shoes";
        v[3].name = "belt";
        v[3].adj.add(v[4]);
        v[4].name = "jacket";
        v[5].name = "shirt";
        v[5].adj.addAll(Arrays.asList(new Vertex[]{v[3],v[6]}));
        v[6].name = "tie";
        v[6].adj.add(v[4]);
        v[7].name = "socks";
        v[7].adj.add(v[2]);
        v[8].name = "watch";


        print(topologicalSort(v));
    }
}

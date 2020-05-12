package com.michael.Graphs;

import java.util.Arrays;

public class DFS {

    public static void dfs(Vertex [] v, Counter c){

        for(int i = 0; i < v.length; i ++){

            if(v[i].color == Color.WHITE){
                dfsVisit(v[i], c);
            }
        }


    }

    public static void dfsVisit(Vertex x, Counter c){
        c.increament();

        x.d = c.a;
        x.color = Color.GRAY;

        for(Vertex y: x.adj){

            if(y.color == Color.WHITE){
                y.parent = x;
                dfsVisit(y,c);
            }
        }
        c.increament();
        x.f = c.a;
        x.color = Color.BLACK;

    }


    public static void printGraph(Vertex [] v){
        for(int i = 0; i < v.length; i ++){
            System.out.print(i +":"+v[i] + " ");
        }
        System.out.println();
    }

    public static void main(String [] args){
        Vertex[] v = new Vertex[10];

        for(int i = 0; i < v.length; i ++){
            v[i] = new Vertex();
        }

        /*//setting the 6 edges
        v[0].adj.addAll(Arrays.asList(new Vertex[]{v[1], v[3]}));
        v[1].adj.add(v[4]);
        v[2].adj.addAll(Arrays.asList(new Vertex[]{v[4], v[5]}));
        v[3].adj.add(v[1]);
        v[4].adj.add(v[3]);
        v[5].adj.add(v[5]);*/

        v[0].adj.addAll(Arrays.asList(new Vertex[]{v[2],v[3],v[6]}));
        v[1].adj.addAll(Arrays.asList(new Vertex[]{v[4],v[8]}));
        v[2].adj.add(v[5]);
        v[3].adj.addAll(Arrays.asList(new Vertex[]{v[7],v[8]}));
        v[4].adj.add(v[8]);
        v[5].adj.add(v[6]);
        v[7].adj.add(v[9]);
        v[8].adj.add(v[7]);
        

        //printGraph(v);
        dfs(v,new Counter());
        printGraph(v);
    }
}

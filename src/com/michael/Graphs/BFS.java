package com.michael.Graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Implementation of BFS on a graph that is implemented using Adjacency List
 */
public class BFS {

    /**
     *The implementation takes O(v + e) where v = the number of vertices and e = number of edges
     * @param v  the Graph
     */
    public static void bfs(Vertex[] v){
        int INF = (int)1e5;
        int n = v.length;
        for(int i = 1; i < n; i ++){

            v[i].d = INF;
        }

        Queue<Vertex> q = new ArrayDeque<>();
        v[0].color = Color.GRAY;
        q.offer(v[0]);

        while(!q.isEmpty()){
            Vertex x = q.poll();

            for(Vertex y : x.adj){

                if(y.color == Color.WHITE){
                    y.color = Color.GRAY;
                    y.d = x.d + 1;
                    y.parent = x;
                    q.offer(y);
                }
            }
            x.color = Color.BLACK;
        }

    }


    public static void printGraph(Vertex [] v){
        for(int i = 0; i < v.length; i ++){
            System.out.print(v[i] + " ");
        }
        System.out.println();
    }

    public static void main(String [] args){
        Vertex[] v = new Vertex[6];

        for(int i = 0; i < v.length; i ++){
            v[i] = new Vertex();
        }

        //setting the edges
        v[0].adj.addAll(Arrays.asList(new Vertex[]{v[1], v[3]}));
        v[1].adj.add(v[4]);
        v[2].adj.addAll(Arrays.asList(new Vertex[]{v[4], v[5]}));
        v[3].adj.add(v[1]);
        v[4].adj.add(v[3]);

        printGraph(v);
        bfs(v);
        printGraph(v);
    }
}

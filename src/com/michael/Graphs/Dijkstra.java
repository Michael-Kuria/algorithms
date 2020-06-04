package com.michael.Graphs;

import java.util.*;

/**
 * An implementation of dijkstra's algorithm in Java
 */
public class Dijkstra {

    public static final int INF = (int)(1e7);

    public static class Vertex implements Comparable<Vertex>{
        public int id;
        public int d = INF;
        public List<Edge> adj = new ArrayList<>();
        public Vertex p;

        public Vertex(int id){
            this.id = id;
        }

        @Override
        public String toString(){
            return "("+id +","+d+")";

        }

        @Override
        public int compareTo(Vertex o) {
            return ((Integer)d).compareTo(o.d);
        }
    }

    public static class Edge{
        public Vertex v;
        public int w;

        public Edge(Vertex v, int w){
            this.w  = w;
            this.v = v;
        }

    }


    public static void dijkstra(Vertex[] g){

        g[0].d = 0;
        PriorityQueue<Vertex> q = new PriorityQueue<>(Comparator.naturalOrder());

        for(Vertex v: g){
            q.offer(v);
        }

        while(!q.isEmpty()){
            Vertex u = q.poll();
            //System.out.println(u);

            for(Edge e : u.adj){
                //relax the edge
                if(e.v.d > u.d + e.w){
                    e.v.d = u.d + e.w;
                    e.v.p = u;
                }
            }
        }

    }

    public static void main(String [] args){
        Vertex[] g = new Vertex[5];

        for(int i = 0; i < g.length; i ++){
            g[i] = new Vertex(i);
        }

        g[0].adj.addAll(Arrays.asList(new Edge(g[1],10),new Edge(g[3],5)));
        g[1].adj.addAll(Arrays.asList(new Edge(g[3],2),new Edge(g[2],1)));
        g[2].adj.add(new Edge(g[4],4));
        g[3].adj.addAll(Arrays.asList(new Edge(g[2],9),new Edge(g[1],3),new Edge(g[4],2)));
        g[4].adj.addAll(Arrays.asList(new Edge(g[2],6),new Edge(g[0],7)));

        dijkstra(g);

        for(Vertex v: g)
            System.out.println(v);
    }
}

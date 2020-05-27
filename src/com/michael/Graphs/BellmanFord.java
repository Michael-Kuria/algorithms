package com.michael.Graphs;


import java.util.ArrayList;
import java.util.List;

/**
 * A java implementation of the Bellman-Ford Algorithm for single-source shortest paths
 */
public class BellmanFord<T> {

    public static final int INF = (int)(1e7);

    public static class Vertex<T>{
        public T name;
        public Vertex<T> p;
        public int d;
        public List<Vertex<T>> adj = new ArrayList<>();

        public Vertex(T name){
            this.name = name;
            d = INF;
        }

        @Override
        public String toString(){
            return "("+name+","+d+","+"p = "+ ((p == null)? "null" : p.name)+")";
        }
    }


    public static class Edge<T>{

        public Vertex<T> u;
        public Vertex<T> v;
        public int w;



        public Edge(Vertex<T> u,Vertex<T> v,int w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }


    public boolean bellmanFord(Vertex<T>[] g, Edge<T>[] e){
        int n = g.length;
        int k = e.length;

        g[0].d = 0;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < k; j ++){

                //relax the edge
                if(e[j].v.d > (e[j].u.d + e[j].w)){
                    e[j].v.d = e[j].u.d + e[j].w;
                    e[j].v.p = e[j].u;
                }
            }
        }

        for(int j = 0; j < k; j ++){

            if(e[j].v.d > (e[j].u.d + e[j].w))
                return false;
        }
        return true;
    }


    public static void main(String [] args){

        Vertex<String> [] g = new Vertex[5];

        for(int i = 0; i < 5; i ++){
            g[i] = new Vertex<>(Character.toString((char)('s' + i)));
        }

        //g[0].adj.addAll((Collection<? extends Vertex<String>>) Arrays.asList(new Vertex[]{g[1],g[3]}));
        g[0].adj.add(g[1]);
        g[0].adj.add(g[3]);

        //g[1].adj.addAll((Collection<? extends Vertex<String>>) Arrays.asList(new Vertex[]{g[2],g[3],g[4]}));
        g[1].adj.add(g[2]);
        g[1].adj.add(g[3]);
        g[1].adj.add(g[4]);
        g[2].adj.add(g[1]);
        //g[3].adj.addAll((Collection<? extends Vertex<String>>) Arrays.asList(new Vertex[]{g[2],g[4]}));
        g[3].adj.add(g[2]);
        g[3].adj.add(g[4]);
        //g[4].adj.addAll((Collection<? extends Vertex<String>>) Arrays.asList(new Vertex[]{g[0],g[2]}));
        g[4].adj.add(g[0]);
        g[4].adj.add(g[2]);


        Edge<String> e [] = new Edge[10];

        e[0] = new Edge<>(g[0],g[1],6);
        e[1] = new Edge<>(g[1],g[2],5);
        e[2] = new Edge<>(g[2],g[1],-2);
        e[3] = new Edge<>(g[4],g[2],7);
        e[4] = new Edge<>(g[3],g[4],9);
        e[5] = new Edge<>(g[0],g[3],7);
        e[6] = new Edge<>(g[3],g[2],-3);
        e[7] = new Edge<>(g[1],g[4],-4);
        e[8] = new Edge<>(g[4],g[0],2);
        e[9] = new Edge<>(g[1],g[4],-4);


        BellmanFord<String> bellmanFord = new BellmanFord<>();
        bellmanFord.bellmanFord(g,e);


        for(int i = 0; i < 5; i ++){
            System.out.println(g[i]);
        }
    }
}

package com.michael.Graphs;

import java.util.*;

/**
 * This class finds the strongly connected components of a graph and returns a map of all the components
 * with the key as the root of the depth first search tree
 *
 *
 * /**
 *  * Why does Kosaraju's algorithm work?
 *  *
 *  * Let G -> Graph, GT -> The transpose of G
 *  *
 *  * During the first dfs we calculate the finishing time of each vertex in G. if there exist an edge (u,v) from component C to C'
 *  * It is certain that the finishing time of at least one vertex in C will be greater than any of the finishing time of vertices in C'.
 *  * This posses a problem, since in such a case it will be difficult to obtain the strongly connected components of G.
 *  *
 *  * To eliminate this case we compute GT and order the vertices according to non-increasing finishing time of G. On performing a dfs on GT
 *  * we are guaranteed that all the vertices of C will be completely search before any vertex in C', this is because (u,v) will be reversed to (v,u)
 *  *
 *  * We perform a second dfs on the transposed graph ordered according to non-increasing finishing time of the graph
 *  */


public class StronglyConnectedComponents {

    public static void dfs(Vertex[] v, Counter c, HashMap<Integer,List<Integer>> m){

        int n = 0;
        for(int i = 0; i < v.length; i ++){
            if(v[i].color == 0){
                m.put(v[i].id,new ArrayList<>());
                dfsVisit(v[i],c,m,v[i].id);

            }
        }
    }

    public static void dfsVisit(Vertex x, Counter c, HashMap<Integer,List<Integer>> m,int index){
        c.increament();
        x.d = c.a;
        x.color = 1;
        for(Vertex y: x.adj){
            if(y.color == 0){
                m.get(index).add(y.id);
                y.p = x;
                dfsVisit(y,c,m,index);
            }
        }

        c.increament();
        x.f = c.a;
    }


    public static Vertex[] transpose(Vertex[] v){
        int n = v.length;
        Vertex[] w = new Vertex[n];

        for(int i = 0; i < n; i ++){
            w[i] = new Vertex(v[i]);
        }

        for(int i = 0; i < n; i ++){
            Vertex y = v[i];
            for(Vertex z : y.adj){

                w[z.id].adj.add(w[y.id]);
            }
        }

        // sorting according to non-increasing values of f
        Arrays.sort(w, (o1, o2) -> ((Integer)o2.f).compareTo(o1.f));

        return w;
    }

    public static void printGraph(Vertex [] v){

        for(int i = 0; i < v.length; i ++){
            System.out.println(v[i].id +" -> " + v[i].adj);
        }
    }
    public static void printForest(HashMap<Integer,List<Integer>> m){

        for(Map.Entry<Integer,List<Integer>> e: m.entrySet()){
            System.out.println(e.getKey() +" " + e.getValue());
        }
    }


    public static void main(String [] args){
        Vertex[] v = new Vertex[8];

        for(int i = 0; i < v.length; i ++){
            v[i] = new Vertex();
            v[i].id = i;
        }

        v[0].adj.add(v[1]);
        v[1].adj.addAll(Arrays.asList(v[2],v[4],v[5]));
        v[2].adj.addAll(Arrays.asList(v[3],v[6]));
        v[3].adj.addAll(Arrays.asList(v[2],v[7]));
        v[4].adj.addAll(Arrays.asList(v[0],v[5]));
        v[5].adj.add(v[6]);
        v[6].adj.addAll(Arrays.asList(v[5],v[7]));
        v[7].adj.add(v[7]);

        System.out.println("Un-searched Graph");
        printGraph(v);

        //First dfs to obtain the finishing times of each vertex
        HashMap<Integer,List<Integer>> m1 = new HashMap<>();
        dfs(v, new Counter(),m1);
        System.out.println("After first dfs ");
        printGraph(v);

        System.out.println("The map after 1st dfs:");
        printForest(m1);


        // Transposing(gt) and sorting the vertices according to their finishing times
        System.out.println("After transposing and sorting according to f");
        Vertex[] v1 = transpose(v);
        printGraph(v1);



        // Performing a dfs on the  transposed graph
        HashMap<Integer,List<Integer>> m = new HashMap<>();
        dfs(v1,new Counter(),m);
        System.out.println();
        System.out.println("The strongly connected components are: ");
        printForest(m);


    }





    public static class Vertex{

        public int d,f,id, color;
        public Vertex p;

        public List<Vertex> adj = new ArrayList<>();

        public Vertex(){}
        public Vertex(Vertex x){
            this.f = x.f;
            this.d = x.d;
            this.p = x.p;
            this.id = x.id;
        }

        public String toString(){
            return "(" +id +","+f +")";
        }
    }
}

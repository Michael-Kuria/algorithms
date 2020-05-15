package com.michael.Graphs;

import java.util.*;

/**
 * We will maintain a map that will contain components with the keys as the roots of the dfs trees.
 *
 * Then we will form the acyclic (without cycles) component graph, using the id's of the root.
 *
 * The map, just keeps the id's of each vertex.
 *
 */
public class CreatingAcyclicComponentGraph {


    public static void dfsVisit(Vertex x, Map<Integer, List<Integer>> m, int key, Counter c){

        c.increament();
        x.d = c.a;
        x.color = 1;

        for(Vertex y: x.adj){

            if(y.color == 0){
                m.get(key).add(y.id);
                y.p = x;
                dfsVisit(y,m,key,c);
            }
        }
        c.increament();
        x.f = c.a;

    }

    public static void dfs(Vertex[] g, Map<Integer, List<Integer>> m,Counter c){

        for(int i = 0; i < g.length; i ++){
            Vertex x = g[i];

            if(x != null && x.color == 0){
                m.put(x.id, new ArrayList<>());
                dfsVisit(x,m,x.id,c);
            }
        }

    }

    // calculate transpose
    public static Vertex[] transpose(Vertex[] g){
        int n = g.length;
        Vertex[] t = new Vertex[n];

        for(int i = 0; i < n; i ++){
            t[i] = new Vertex(g[i]);
        }

        for(int i = 0; i < n; i ++){
            Vertex x = g[i];

            for(Vertex y : x.adj){
                t[y.id].adj.add(t[x.id]);
            }
        }

        return t;
    }


    /**
     * Find the strongly connected components and store them in a map
     * @param g Graph
     * @return The map containing the Strongly connected components
     */
    public static  Map<Integer, List<Integer>>  scc(Vertex[] g){

        dfs(g,new HashMap<>(), new Counter());
        Vertex[] t = transpose(g);
        //printGraph(g);

        Arrays.sort(t , ((o1, o2) -> ((Integer)o2.f).compareTo(o1.f)));
        //printGraph(t);

        Map<Integer, List<Integer>> m = new HashMap<>();

        dfs(t,m,new Counter());

        return m;

    }

    /**
     * Given an id of the vertex, find the root of the dfs root it is contained in
     *
     * @param id id of a vertex
     * @param m Strongly connected components map
     * @return The id of the dfs root, -1 if not found
     */
    public static int getKey(int id, Map<Integer, List<Integer>> m){

        // O(V)
        //Vertex x = g[id];
        for(Map.Entry<Integer, List<Integer>> e : m.entrySet()){
            int key = e.getKey();
            /*Vertex root = g[key];

            if(x.d >= root.d && x.f <= root.f){
                return key;
            }*/

            if(key == id){
                return key;
            }

            for(Integer i : e.getValue()){

                if(i == id){
                    return key;
                }
            }
        }

        return -1;

    }


    /**
     * This function is used as a subroutine in the creation of a component graph.
     * It checks that there are no edges to self i.e (u,u) and that no edge is inserted twice
     *
     *
     * @param cg the component graph being formed
     * @param m The map containing the strongly connected components
     * @param key The id of the dfs tree root, where we will be adding the edge (key, d)
     * @param d  value of the edge (key,d)
     * @return True if we can insert the edge, False otherwise
     */
    public static boolean checkEdge(Vertex [] cg,Map<Integer, List<Integer>> m,int key, int d){

        int id = getKey(d,m);
        //Prevent self edges
        if( id == key){
            return false;
        }

        // check if the vertex has already been added
        for(Vertex x : cg[key].adj){
            if(x.id == id){
                return false;
            }
        }

        return true;
    }

    /**
     * Create the acyclic component graph
     *
     * @param g the original graph
     * @param m map of the strongly connected components
     *
     * @return component graph
     */

    public static Vertex[] componentGraph(Vertex[] g,  Map<Integer, List<Integer>> m){
        Vertex [] cg = new Vertex[g.length];

        /**
         * Let c represents the number of distinct Components
         */
        // initializing the component graph cg  O(c)
        for(Map.Entry<Integer, List<Integer>> e : m.entrySet()){
            int id = e.getKey();
            cg[id] = new Vertex(g[id]);
        }



        // O((V + E) * {  (c * (V + E)) + ) + ((V + E - c) * (V + E + ...))} )
        for(Map.Entry<Integer, List<Integer>> e : m.entrySet()){
            int id = e.getKey();

            for(Vertex x: g[id].adj){
                int xKey = getKey(x.id,m); // O(V + E)

                if(xKey != id){
                    cg[id].adj.add(cg[xKey]);
                }
            }

            for(int i : e.getValue()){

                for(Vertex y: g[i].adj){
                    if(checkEdge(cg,m,id,y.id)){
                        cg[id].adj.add(cg[getKey(y.id,m)]);
                    }

                }
            }
        }


        return cg;

    }

    public static void printGraph(Vertex[] v){

        for(int i = 0; i < v.length; i ++){
            if(v[i] != null)
             System.out.println(v[i] +" -> " + v[i].adj);
        }
    }


    public static void printForest(Map<Integer,List<Integer>> m){

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

        Map<Integer,List<Integer>> m = scc(v);
        //printForest(m);
        Vertex [] cg = componentGraph(v,m);
        printGraph(cg);

        dfs(cg, new HashMap<>(),new Counter());
        System.out.println("After search");
        printGraph(cg);


    }
















    public static class Vertex{

        public int id,f,d, color;
        public Vertex p;
        public List<Vertex> adj;

        public Vertex(){
            adj = new ArrayList<>();
        }

        // used during the creation a graph's transpose
        public Vertex(Vertex x){
            this();
            this.id = x.id;
            this.f = x.f;
            this.d = x.d;
        }

        @Override
        public String toString(){
            return "("+id+ "," + d +")";
        }

    }
}

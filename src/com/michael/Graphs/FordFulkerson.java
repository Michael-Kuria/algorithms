package com.michael.Graphs;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Edmonds-Karp's implementation of the Ford-Fulkerson Algorithm. It rus in O(VE^2) time.
 *
 *
 */
public class FordFulkerson {


    static int n, en, s, t; // number of vertices, edges, source and sink
    static Vertex [] g; // graph
    static Edge [] edges; // an array containing all the original edges of the graph
    static boolean []  vst;


    /**
     * Edmonds-Karp Algorithm uses bfs to find the shortest path from the source to the sink
     *
     *
     * @param u starting vertex
     * @param gf the residual graph on which we will be performing the bfs
     * @return A List containing the edges that makes up the Argumentative path and null if none exists
     */
    public static ArrayList<Integer> bfs(Vertex u, Vertex [] gf){

        Queue<Vertex> q = new ArrayDeque<>();

        vst[u.id] = true;
        q.offer(u);


        while(!q.isEmpty()){

            Vertex v = q.poll();

            for(Edge e: v.adj){

                if(!vst[e.u.id]){
                    q.offer(e.u);
                    e.u.parent = e;
                }
                vst[e.u.id] = true;

            }

        }

        ArrayList<Integer> ans = null;
        if(vst[t]){
            ans = new ArrayList<>();
            Vertex r = gf[t];

            while(r.parent != null){
                ans.add(r.parent.id);
                r = r.parent.v;
            }
        }


        return ans;

    }

    /**
     * Create the Residual graph and perform the bfs on it
     *
     * @return A List containing the edges that makes up the Argumentative path and null if none exists
     */
    public static ArrayList<Integer> findArgumentativePath(){
        // form the residual Graph
        Vertex [] gf = new Vertex[n];
        vst = new boolean[n];

        for(int i = 0; i < n; i ++){
            gf[i] = new Vertex(i);
        }

        for(int i = 0; i < n; i ++){

            for(Edge e : g[i].adj){

                /**
                 * if the flow (f) is 0 we can't have flow in the opposite direction of e
                 * If the capacity is maxed out we can't push more flow in the direction of e
                 */
                if(e.f != 0 )
                    gf[e.u.id].adj.add(new Edge(gf[i],gf[e.u.id],e.c, e.f,e.id + en));
                if(e.f != e.c){
                    gf[i].adj.add(new Edge(gf[e.u.id],gf[i],e.c, e.c - e.f, e.id));
                }


            }
        }



        return bfs(gf[s], gf);

    }

    /**
     * Find the max flow by always finding an argumentative and updating flow along that path.
     *
     */
    public static void findFlow(){

        ArrayList<Integer> aPath = findArgumentativePath();
        while( aPath != null){ // the loop can take at most O(VE) time

            int cf = Integer.MAX_VALUE >> 1; // update the flow with the min cf
            for(int k : aPath){

                if(k >= en) k -= en;

                cf =  Math.min(cf, (edges[k].c - edges[k].f));
            }

            for(int k :  aPath){

                if(k < en){ // does the edge exist in the original graph
                    edges[k].f +=  cf;
                }else{
                    edges[k - en].f -= cf;
                }
            }

            aPath = findArgumentativePath();

        }

    }


    public static void printGraph(Vertex [] vx){

        int m = vx.length;

        for(int i = 0; i < m; i ++){

            System.out.print(i +" ");
            for(Edge e: vx[i].adj){
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }


    public static void main(String []  args){

        n = 6;
        en = 9;
        s = 0;
        t = n - 1;

        edges = new Edge[en];
        g = new Vertex[n];

        for(int i = 0; i < n; i ++){
            g[i] = new Vertex(i);
        }

        edges[0] = new Edge(g[1],g[0],16,0,0);
        edges[1] = new Edge(g[2],g[0],13,0,1);
        edges[2] = new Edge(g[3],g[1],12,0,2);
        edges[3] = new Edge(g[1],g[2],4,0,3);
        edges[4] = new Edge(g[4],g[2],14,0,4);
        edges[5] = new Edge(g[2],g[3],9, 0,5);
        edges[6] = new Edge(g[3],g[4],7,0,6);
        edges[7] = new Edge(g[5],g[3],20,0,7);
        edges[8] = new Edge(g[5],g[4],4,0,8);


        g[0].adj.add(edges[0]); g[0].adj.add(edges[1]);
        g[1].adj.add(edges[2]);
        g[2].adj.add(edges[3]); g[2].adj.add(edges[4]);
        g[3].adj.add(edges[5]); g[3].adj.add(edges[7]);
        g[4].adj.add(edges[6]); g[4].adj.add(edges[8]);

        findFlow();

        int f = 0;

        for(Edge e: g[0].adj){
            f += e.f;
        }

        System.out.println("f = " + f);
        printGraph(g);

    }



    public static class Vertex{
        ArrayList<Edge> adj =  new ArrayList<>();
        int  id;
        Edge parent;

        public Vertex(int id){
            this.id =  id;
        }
    }

    public static class Edge{
        Vertex u, v;
        int c, f, id; // capacity, flow and the id of the edge

        public Edge(Vertex u,Vertex v, int c, int f, int id){
            this.v = v;
            this.u = u;
            this.f = f;
            this.c = c;
            this.id = id;
        }


        @Override
        public String toString(){
            return "(" + u.id +" , c = " + c +", f = " + f +")";
        }

    }
}

/*
import java.util.*;

*/
/**
 * An implementation of ford-fulkerson Algorithim for finding the Maximum flow of
 * flow network
 *
 * improve so that we update using c instead of f
 *//*

public class FordFulkerson {

    public static Vertex[] residualGraph(Vertex [] g){
        int n = g.length;
        Vertex[] gf = new Vertex[n];

        for(Vertex v : g){
            gf[v.id] = new Vertex(g[v.id]);
        }


        for(int i = 0; i < n; i ++){
            Vertex v = g[i];

            for(Edge e: v.adj){
                // if c - f > 0  then we can have f'(u,v)
                int c = e.c - e.f;

                if(c > 0 || e.f == 0){ // if f(e) = 0 or the e is not yet maxed out
                    gf[v.id].adj.add(new Edge(c, e.f, true,gf[e.v.id]));
                }

                if(c >= 0 && e.f > 0){ // if e is either maxed out or not and the f(e) is greater than 0
                    gf[e.v.id].adj.add(new Edge(e.f,e.f, false,gf[v.id]));
                }

            }
        }

        return gf;
    }


    public static Vertex[]  bfs(Vertex [] g, int s, int t){

        for(Vertex u : g){
            u.p = null;
            u.color = 0;
        }

        Queue<Vertex> q = new ArrayDeque<>();

        q.offer(g[s]);

        while(!q.isEmpty()){

            Vertex u = q.poll();

            for(Edge e: u.adj){

                if(e.v.color == 0){
                    q.offer(e.v);
                    e.v.p = u;
                }
            }
            u.color = 1;

        }

        return g;

    }


    public static void fordFulkerson(Vertex [] g, int s, int t){
        // set f(e) = 0 for every edge
        for(Vertex u: g){
            for(Edge e: u.adj){
                e.f = 0;
            }
        }

        Vertex [] gf = residualGraph(g);

        while(bfs(gf,s,t)[t].p != null){


            int cp = (int)(1e7);
            Vertex a = gf[t];
            while(a.p != null){

                for(Edge e: a.p.adj){
                    if(e.v == a && e.c > 0){
                        cp = Math.min(cp, e.c); //residual capacity
                    }
                }
                a = a.p;
            }


            Vertex b = gf[t];

            while(b.p != null){ // while the there are still edges in the path

                for(Edge e: b.p.adj){

                    // b.p = u and b = v
                    if(e.v == b){ // if this is an edge on the path
                        // determine whether e is in g
                        if(e.inOrg){

                            for(Edge ed: g[b.p.id].adj){

                                if(ed.v == g[b.id]){
                                    // update f
                                    ed.f += cp;
                                }
                            }


                        }else{
                            // e is not in g

                            for(Edge ed: g[b.id].adj){

                                if(ed.v == g[b.p.id]){
                                    ed.f -= cp;
                                    //System.out.println("Edge : " +"( v = "+b.id + " __ "+ed.f +"/"+ed.c +"____ u = " + ed.v.id + ")");
                                }
                            }


                        }

                    }
                }
                b = b.p;

            }

            gf = residualGraph(g);


        }


    }

    public static void print(Vertex[] g){

        for(Vertex v : g){

            System.out.println(v);
        }
    }



    public static void main(String [] args){

        Vertex[] g = new Vertex[6];

        for(int i = 0; i < g.length; i ++){
            g[i] = new Vertex(i);
        }

        g[0].adj.addAll(Arrays.asList(new Edge(13,8,true,g[3]),new Edge(16,11,true,g[1]) ));
        g[3].adj.addAll(Arrays.asList(new Edge(14,11,true,g[4]), new Edge(4,1,true,g[1])));
        g[2].adj.addAll(Arrays.asList(new Edge(9,4,true,g[3]), new Edge(20,15,true,g[5])));
        g[4].adj.addAll(Arrays.asList(new Edge(7,7,true,g[2]), new Edge(4,4,true,g[5])));
        g[1].adj.add(new Edge(12,12,true,g[2]));


        fordFulkerson(g,0,5);

        print(g);
    }












    private static class Vertex{
        Vertex p;
        int id;
        List<Edge> adj = new ArrayList<>();
        int color;

        public Vertex(int id){
            this.id = id;
        }

        public Vertex(Vertex v){
            this.id = v.id;
            adj = new ArrayList<>();
        }

        @Override
        public String toString(){
            String str = "";

            for(Edge e: adj){
                str +=  "(v = " +id +"__ " + e.f +"/" + e.c +" __ u = " + e.v.id +") ";
            }

            return str;
        }
    }

    private static class Edge{
        boolean inOrg; // will keep track of edges in the graph G other than those in Gf
        int c,f;
        Vertex v;

        public Edge(int c, int f, boolean inOrg,Vertex v){
            this.c = c;
            this.f = f;
            this.inOrg = inOrg;
            this.v = v;
        }



    }
}
*/

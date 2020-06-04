package com.michael.Graphs;

import java.util.*;

/**
 * An implementation of ford-fulkerson Algorithim for finding the Maximum flow of
 * flow network
 *
 * improve so that we update using c instead of f
 */
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
                        cp = Math.min(cp, e.c); // residual capacity
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

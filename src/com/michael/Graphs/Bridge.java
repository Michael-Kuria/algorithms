package com.michael.Graphs;

import java.util.Arrays;

/**
 * A bridge in an undirected graph is an edge (u,v) which when removed will increase the number of connected components
 * in the graph. i.e paths will not exist between some vertices
 */
public class Bridge {

    public int entry = 0, bridges = 0;
    public int [] low;
    Vertex [] g;

    public Bridge(int n){
        g = new Vertex[n];
        for(int i = 0; i < n; i ++){
            g[i] = new Vertex();
            g[i].index = i;
        }
    }

    public void dfs(Vertex v, Vertex p){

        v.color = Color.BLACK;
        v.d = low[v.index] = entry ++;

        for(Vertex to: v.adj){

            if(to == p) continue;
            else if(to.color == Color.BLACK){
                low[v.index] = Math.min(low[v.index],to.d);
            }else{
                dfs(to,v);
                low[v.index] = Math.min(low[v.index],low[to.index]);
                if(low[to.index] > v.d){
                    bridges ++;
                }
            }
        }

    }

    public void findBridges(){

        int n = g.length;
        low = new int[n];
        for(int i = 0; i < n; i ++){
            if(g[i].color == Color.WHITE){
                dfs(g[i], null);
            }
        }


    }

    public static  void main(String [] args){
        Bridge b = new Bridge(9);

        b.g[0].adj.addAll(Arrays.asList(b.g[1],b.g[2]));
        b.g[1].adj.addAll(Arrays.asList(b.g[0],b.g[3]));
        b.g[2].adj.addAll(Arrays.asList(b.g[0],b.g[3]));
        b.g[3].adj.addAll(Arrays.asList(b.g[1],b.g[2],b.g[4]));
        b.g[4].adj.addAll(Arrays.asList(b.g[5],b.g[3],b.g[7]));
        b.g[5].adj.addAll(Arrays.asList(b.g[4],b.g[6]));
        b.g[6].adj.addAll(Arrays.asList(b.g[5],b.g[7]));
        b.g[7].adj.addAll(Arrays.asList(b.g[4],b.g[6],b.g[8]));
        b.g[8].adj.add(b.g[7]);

        b.findBridges();

        System.out.println(b.bridges);


    }
}

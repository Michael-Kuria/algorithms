package com.michael.Graphs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    public int d; // depth of the vertex int bfs/ first time visited in dfs
    public int f;
    public int index;
    public Color color;
    public List<Vertex> adj;
    public Vertex parent;

    public Vertex(){
        d = 0;
        f = 0;
        color = Color.WHITE;
        adj = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "(" + color + ","+ d + "," + f +")";
    }

}


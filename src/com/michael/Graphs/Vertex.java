package com.michael.Graphs;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    public int d; // depth of the vertex
    public Color color;
    public List<Vertex> adj;
    public Vertex parent;

    public Vertex(){
        d = 0;
        color = Color.WHITE;
        adj = new ArrayList<>();
    }

    @Override
    public String toString(){
        return "(" + color + ","+ d +"," + (parent == null ? "null": parent.d) +")";
    }

}

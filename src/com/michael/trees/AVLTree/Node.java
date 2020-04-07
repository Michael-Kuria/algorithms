package com.michael.trees.AVLTree;

public class Node<T extends Comparable> {
    T key;
    int h;
    Node<T> p;
    Node<T> right;
    Node<T> left;

    public Node(){
        h = -1;
    }

    public Node(T key){
        this();
        this.key = key;
    }
}

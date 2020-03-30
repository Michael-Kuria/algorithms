package com.michael.trees;

public class Node<T> {

    T key;
    Node parent;
    Node left;
    Node right;

    public Node(T key) {
        this.key = key;
    }
}


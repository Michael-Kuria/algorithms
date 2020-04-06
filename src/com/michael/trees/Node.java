package com.michael.trees;

/**
 * This class represents a single node in a tree
 * @param <T> the object it contains
 */
public class Node<T> {

    T key;
    Node parent;
    Node left;
    Node right;

    public Node(T key) {
        this.key = key;
    }
}


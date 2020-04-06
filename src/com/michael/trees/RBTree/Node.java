package com.michael.trees.RBTree;

/**
 *
 * @param <T> The object stored in the node, for usage in creation of trees the Node needs to extend the Comparable Class
 */
public class Node<T extends Comparable> {

    T key;
    Node<T> right;
    Node<T> left;
    Node<T> p;
    Color color;

    public Node(T key){
        this.key = key;
        color = Color.RED;
    }

}

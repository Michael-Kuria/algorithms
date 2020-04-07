package com.michael.trees.AVLTree;

/**
 * Java implementation of AVL Tree. AVL are height balanced with the property |x.left.h - x.right.h| < 2
 *
 * @param <T>
 * @author Kush
 */
public class AVLTree<T extends Comparable> {

    Node<T> root;
    Node<T> nil = new Node<>(); // if either left or right node(s) of a Node is null then they will point to this.

    int size = 0;

    public AVLTree(){
        this.root = nil;
    }

    public AVLTree(T key){

        this.root = new Node<>(key);
        root.left = nil;
        root.right = nil;
        size ++;
    }

    /**
     * Find the minimum object {@code T}, on the subtree rooted at x
     * @param x
     * @return
     */
    public Node<T> min(Node<T> x){
        while(!x.left.equals(nil)){
            x = x.left;
        }
        return x;
    }

    /**
     *Find the maximum object {@code T}, on the subtree rooted at x
     * @param x
     * @return
     */
    public Node<T> max(Node<T> x){
        while(!x.right.equals(nil)){
            x = x.right;
        }
        return x;
    }

    /**
     * Find the element that come before x, if the objects are arranged in ascending order
     * @param x
     * @return
     */
    public Node<T> predecessor(Node<T> x){
        if(!x.left.equals(nil)){
            return max(x.left);
        }

        Node<T> y = x.p;
        while(!y.equals(nil) && y.left == x ){
            x = y;
            y = x.p;
        }
        return y;
    }

    /**
     * Find the element that comes after x, if the objects are arranged in non decreasing order
     * @param x
     * @return
     */
    public Node<T> successor(Node<T> x){
        if(!x.right.equals(nil)){
            return min(x.right);
        }

        Node<T> y = x.p;

        while(!y.equals(nil) && y.right == x){
            x = y;
            y = x.p;
        }
        return y;
    }

    /**
     * Search for the first Node with the key == x
     * @param x
     * @return
     */
    public Node<T> search(T x){

        Node<T> n = root;

        while(!n.equals(nil)){
            if(n.key.equals(x)){
                return n;
            }else if(x.compareTo(n.key) < 0){
                n = n.left;
            }else
                n = n.right;
        }

        return null;
    }

    /**
     * Update the height of the tree starting at the node x
     * @param x
     */
    public void updateHeight(Node<T> x){

        while(x != null){

            x.h = Math.max(x.right.h, x.left.h) + 1;

            x = x.p;
        }
    }

    /**
     * Rotate the tree to the left
     * @param x
     */
    public void leftRotate(Node<T> x){
        Node<T> y = x.right;

        x.right = y.left;
        if(!x.left.equals(nil)){
            y.left.p = x;
        }

        if(x.p == null){
            root = y;
        }else if(x == x.p.right){
            x.p.right = y;
        }else{
            x.p.left = y;
        }
        y.p = x.p;
        y.left = x;
        y.left.p = y;

        updateHeight(x);
    }

    /**
     * Rotate the tree to the right
     * @param x
     */
    public void rightRotate(Node<T> x){
        Node<T> y = x.left;

        x.left = y.right;
        if(!x.left.equals(nil)){
            x.left.p = x;
        }

        if(x.p == null){
            root = y;
        }else if(x == x.p.left){
            x.p.left = y;
        }else{
            x.p.right = y;
        }
        y.p = x.p;
        y.right = x;
        x.p = y;
        updateHeight(x);

    }

    /**
     * Insert the object {@code T} z into the tree.
     * @param z
     */
    public void insert(T z){
        Node<T> y = new Node<>(z);
        y.right = nil;
        y.left = nil;


        Node<T> x = root;
        Node<T> w = null; // points to the parent of the node being inserted.
        while(!x.equals(nil)){
            w = x;
            if(z.compareTo(x.key) < 0){
                x = x.left;
            }else{
                x = x.right;
            }
        }

        if(w == null){
            root = y;
        }else if(z.compareTo(w.key) < 0){
            w.left = y;
        }else{
            w.right = y;
        }
        size ++;
        y.p = w;
        updateHeight(y);


        if(y != root && y.p != root)
            balance(y);


    }

    /**
     * After insertion of Node z the AVL tree property (|x.left.h - x.right.h| < 2) might be violated
     * @param z
     */
    public void balance(Node<T> z){

        while(z.p != root ){

            if(Math.abs(z.p.p.right.h - z.p.p.left.h) > 1){
                if(z.p.p.right == z.p){
                    if(z == z.p.left){
                        z = z.p;
                        rightRotate(z);
                    }
                    leftRotate(z.p.p);
                }else{
                    if(z == z.p.right){
                        z = z.p;
                        leftRotate(z);
                    }
                    rightRotate(z.p.p);

                }
                z = root.right;

            }else{
                z = z.p;
            }
        }

    }

    /**
     * Delete the Node Z from the tree.
     * @param z
     */
    public void delete(Node<T> z){}

    /**
     * Print the elements inorder fashion.
     * @param x
     */
    public void print(Node<T> x){
        if(!x.equals(nil)){
            print(x.left);
            System.out.print("[ " + x.key +", "+x.h +" ]");
            print(x.right);
        }

    }

    public static void main(String [] args){
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);

        tree.insert(7);
        tree.insert(8);
        tree.insert(8);
        tree.insert(9);
        /*tree.insert(8);
        tree.insert(8);
        tree.insert(8);*/

        tree.print(tree.root);

        System.out.println();

        System.out.println(tree.successor(tree.search(5)).key);

    }

}

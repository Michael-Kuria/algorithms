package com.michael.trees;

/**
 * An implementation of a binary tree.
 *
 * This implementation does not take care of unbalanced trees
 *
 * @author Kush
 */

public class BinaryTree<T extends Comparable> {

    Node<T> root;
    private int size = 0;

    public BinaryTree(){}

    public BinaryTree(Node root){
        this.root = root;
    }

    /**
     * Find the minimum element in the tree whose root is X. To do this, just follow the left nodes according
     * to the binary tree property definition.
     *
     * @param x the root of the tree we will be finding the minimum
     * @return the smallest element in the (sub-) tree rooted at X
     */
    public Node<T> min(Node<T> x){
        // if x is null find the global minimum
        if(x == null){
            x = this.root;
        }
        while(x.left != null){
            x = x.left;
        }
        return x;

    }

    /**
     * Find the maximum(biggest) element in the tree rooted at x. According to the binary tree property,
     * follow the right nodes.
     *
     * @param x the root of the given tree/sub-tree
     * @return the largest element in the (sub-)tree rooted at X
     */
    public Node<T> max(Node<T> x){
        // if x is null just find the global maximum
        if(x == null){
            x = this.root;
        }

        while(x.right != null){
            x = x.right;
        }
        return x;
    }

    /**
     * This function finds the element that is next in line after x, i.e if the elements are to be arranged in an
     * ascending order then find the element that comes after x.
     *
     * @param x the Node whose successor we want to find
     * @return the successor of x
     */
    public Node<T> successor(Node<T> x){
        if(x.right != null){
            return min(x.right);
        }

        Node<T> y = x.parent;
        while(y != null && x == y.right ){
            x = y;
            y = x.parent;
        }
        return y;

    }

    /**
     * Find the Node that comes before x, when the Nodes are arranged in ascending order.
     *
     * @param x The Node whose predecessor we are finding
     * @return the predecessor of x
     */
    public Node<T> predecessor(Node<T> x){
        if(x.left != null){
            return max(x.left);
        }

        Node<T> y = x.parent;

        while(y != null && x == y.left){
            x = y;
            y = x.parent;
        }
        return y;
    }

    /**
     * Find the object x, in the tree.
     * @param x the object to be searched
     * @return the node with the key x if found, return null if not found
     */
    public Node<T> search(T x){

        Node<T> y = this.root;

        while(y != null){
            if(y.key.compareTo(x) == 0){
                return y;
            } else if(x.compareTo(y.key) < 1){
                y = y.left;
            }else{
                y = y.right;
            }
        }
        return null;
    }

    /**
     * Insert x into the tree. If x >= y.key then insert it on the right of y, otherwise insert it on the left;
     * @param x the object to be inserted
     */
    public void insert(T x){
        Node<T> y = this.root;
        Node<T> t = null; // trailing pointer

        while(y != null){
            t = y;

            if(x.compareTo(y.key) >= 0){
                y = y.right;
            }else
                y = y.left;
        }

        Node<T> node = new Node<>(x);
        node.parent = t;

        if(t == null)
            this.root = node;

        else if(x.compareTo(t.key) >= 0)
            t.right = node;
        else
            t.left = node;

        size ++; // increase the size of the tree

    }

    /**
     * Replace the node x with node y in the tree. This method will be used as a step in the delete method
     *
     * @param x the Node to be replaced
     * @param y  the Node to be replaced with
     */
    public void transplant(Node<T> x, Node<T> y){
        if(x.parent == null){
            this.root = y;
        }else if(x == x.parent.left){
            x.parent.left = y;
        }else{
            x.parent.right = y;
        }

        if(y != null)
            y.parent = x.parent;
    }

    /**
     * Remove the first node with the key x
     * @param x the object to be removed from the tree
     */
    public void delete(T x){
        Node<T> z = search(x);

        if(z == null)
            return;

        if(z.right == null){
            transplant(z,z.left);
        }else if(z.left == null){
            transplant(z,z.right);
        }else{
            Node<T> y = min(z.right); // This node has no left child
            if(z.right != y){
                transplant(y,y.right);
                y.right = z.right;
                z.right.parent = y;
            }
            transplant(z,y);
            y.left = z.left;
            y.left.parent = y;
        }
        size --;
    }

    /**
     * get the number of objects in the tree
     * @return size of the tree
     */
    public int size(){
        return size;
    }

    /**
     * Print the tree in inorder traversal
     */
    public void inorderPrint(Node<T> x){

        if(x != null){
            inorderPrint(x.left);
            // for debugging print the key and it's parent key
            System.out.print("[" + x.key +", " + (x.parent == null? "null": x.parent.key) +"] > ");
            inorderPrint(x.right);
        }
    }


    public static void main(String [] args){
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.insert(15);
        tree.insert(18);
        tree.insert(6);
        tree.insert(3);
        //tree.insert(7);
        tree.insert(17);
        tree.insert(19);
        tree.insert(2);
        tree.insert(4);
        tree.insert(14);
        tree.insert(9);
        tree.insert(13);

        System.out.println("Elements In the tree >>>>>>");
        tree.inorderPrint(tree.root);
        System.out.println("size " + tree.size());
        tree.delete(6);
        System.out.println("After deleting 6 >>>>>>");
        System.out.println("Root " + tree.root.key);
        tree.inorderPrint(tree.root);
        System.out.println("size " + tree.size());

        /*System.out.println("The predecessor of 6 is " + tree.predecessor(tree.search(6)).key );
        System.out.println();
        System.out.println("The successor of 4 is " + tree.successor(tree.search(4)).key );
        System.out.println();
        System.out.println("The minimum element rooted at 6 "+ tree.min(tree.search(6)).key);
        System.out.println();
        System.out.println("The maximum element rooted at 6 "+ tree.max(tree.search(6)).key);*/



    }
}


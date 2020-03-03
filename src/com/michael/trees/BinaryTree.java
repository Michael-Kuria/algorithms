package com.michael.trees;

/**
 * An implementation of a binary tree.
 *
 * This implementation does not take care of unbalanced trees
 *
 * @author Kush
 */

public class BinaryTree {

    public Node root;

    public BinaryTree(){}

    /**
     * Creates a binary tree with the param as the starting node i.e root
     * @param value
     */
    public BinaryTree(int value){
        Node node = new Node(value);
        this.root = node;
    }

    /**
     * This function works with the assumption that the root element is not null
     * @param value
     * @param node (start node)
     */
    public void insertRecursive(int value, Node node){

        if(value <= node.getValue()){

            if(node.getLeftNode() == null){
                node.setLeftNode(new Node(value));
                return;
            }

            insertRecursive(value,node.getLeftNode());
        }else {

            if(node.getRightNode() == null){
                node.setRightNode(new Node(value));
                return;
            }

            insertRecursive(value,node.getRightNode());

        }

    }

    /**
     * Inserts the value at any node including the root
     * @param value
     */
    public void insert(int value){
        Node node = new Node(value);
        Node parent;

        if(root == null){
            this.root = node;
            return;
        }else{
            parent = root;
        }

        while(true){
            if(value <= parent.getValue()){
                if(parent.getLeftNode() == null){
                    parent.setLeftNode(node);
                    return;
                }
                parent = parent.getLeftNode();
            }else{
                if(parent.getRightNode() == null){
                    parent.setRightNode(node);
                    return;
                }
                parent = parent.getRightNode();
            }
        }


    }

    /**
     * A recursive function that finds if  @param value is present in the tree
     * @param node (start node)
     *
     * @return True if found False otherwise
     */
    public boolean contains(int value, Node node){

        if(node == null){
            return false;
        }

        if(node.getValue() == value){
            return true;
        }

        if(value <= node.getValue()){
            return contains(value, node.getLeftNode());
        }else{

            return contains(value, node.getRightNode());
        }


    }



    public void delete(int value){

    }


    /**
     * print the left child  -> parent -> right child
     * @param node (start node)
     */
    public void printInorder(Node node){

        if(node != null){
            printInorder(node.getLeftNode());
            System.out.print(node.getValue() + " ");
            printInorder(node.getRightNode());
        }

    }

    public static void main(String [] args){
        BinaryTree tree = new BinaryTree(40);
        tree.insert(20);
        tree.insert(100);
        tree.insertRecursive(23, tree.root);

        tree.printInorder(tree.root);

    }
}

package com.michael.trees;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The following Java program attempts to balance an unbalance BST
 */
public class BalancingBinarySearchTrees {

    /**
     * This class will attempt to balance a binary tree by traversing the original tree
     * in inorder traversal and storing the elements in an array, the create a new tree from the array
     */
    public static class SortedArray{

        public BinaryTree tree;
        public ArrayList<Integer> nodes = new ArrayList<>();
        public int[] array;

        public SortedArray(BinaryTree tree){
            this.tree = tree;
        }

        public void getNodes(Node node){

            if(node == null){
                return ;
            }

            getNodes(node.getLeftNode());
            nodes.add(node.getValue());
            getNodes(node.getRightNode());

        }

        public int[] getNodesArray(){
            array = new int[nodes.size()];

            int j = 0;
            for(Integer i : nodes){
                array[j] = i;
                j ++;
            }

            return array;
        }


        public Node buildTree(Node node, int start, int end){

            if(start > end){
                return null;
            }

            int mid = (start + end)/2;
            Node newNode = new Node(array[mid]);

            node.setLeftNode(buildTree(node.getLeftNode(),start,mid - 1));
            node.setRightNode(buildTree(node.getRightNode(),mid + 1,end));

            return newNode;
        }


    }






    /*public BinaryTree usingDummyTree(BinaryTree tree){
        if(tree.root == null ){
            return null;
        }

        BinaryTree dummy = new BinaryTree();
        return dummy;
        
    }
*/

}

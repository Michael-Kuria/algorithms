package com.michael.LinkedList;

/**
 * This class implements a doubly linked list
 *
 * @param <T>
 */
public class ListNode<T> {

    Node<T> head;
    Node<T> tail;
    private int size = 0;

    public ListNode(){}

    public ListNode(Node node){
        this.head = node;
        this.tail = node;
    }


    /**
     * Inserting the Node x in the list, insertion is taking place at the head i.e new elements are inserted at the head.
     *
     * @param x
     */
    public void insert(Node x){
        x.next = head;

        if(head != null){
            head.prev = x;
        }else{
            this.tail = x;
        }
        this.head = x;
        size ++;
    }

    /**
     * Given the key <T>, make a new Node and insert it in the List
     *
     * @param t value to be inserted
     */
    public void insert(T t){
        insert(new Node(t));
    }

    /**
     * insert the given object in the last position
     *
     * @param t object to be inserted
     */
    public void insertLast(T t){

        Node node = new Node(t);

        if(this.tail != null){
            this.tail.next = node;
            this.tail.prev = this.tail;
        }

        this.tail = node;
        size ++;
    }


    /**
     * Given a <T> key. If it's available return the Node otherwise return null
     *
     * @param x value to be searched.
     * @return The node, contains all the information i.e the previous and the next value in the list
     */
    public Node<T> search(T x){
        if(!isEmpty()){
            Node next = this.head;
            while(next != null){
                if(next.key.equals(x)){
                    return  next;
                }
                next = next.next;
            }

        }
        return null;
    }

    /**
     * Given a {@code node} return if from the List, with all it's details
     * @param node
     * @return
     */
    public Node<T> search(Node node){

        if(!isEmpty()){
            Node next = this.head;
            for(int i = 0; i < size; i ++){
                if(next == node){
                    return next;
                }
                next = next.next;

            }

        }
        return  this.head;


    }


    /**
     * Given the key {@code x} delete it from the list if available.
     *
     * @param x key to be deleted
     */
    public void delete(T x){

        Node node = search(x);

        if(node != null){
            if (node.equals(this.head)){
                this.head = node.next;
                node.prev = null;

            }else{
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size --;


        }

    }

    /**
     * Delete the given Node from the list if available
     * @param x
     */
    public void delete(Node x){

        Node node = search(x);

        if(node != null){
            if(!node.equals(head)){
                node.prev.next = node.next;
                node.next.prev = node.prev;

            }
            else{
                node.prev = null;
                this.head = node.next;
            }
            size --;
        }

    }

    /**
     * This function will delete the last element with {@code t} of the list
     * @param t value to be deleted
     */
    public void deleteLast(T t){}

    /**
     * Check if the list is empty
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }


    /**
     * print the given list
     * @param list list to be printed
     * @param <T> the data type of the list
     */
    public static <T> void print(ListNode<T> list){
        if(!list.isEmpty()){
            Node next = list.head;
            while(next != null){
                System.out.print(next.key +" ");
                next = next.next;
            }
            System.out.println();
        }else
            System.out.println("List is Empty");

    }


    public static void main(String [] args){
        ListNode<Integer> list = new ListNode<>();

        list.insert(new Node(12));
        list.insert(new Node(14));
        list.insert(new Node(13));
        list.insertLast(16);
        list.insert(new Node(18));

        list.delete(18);

        print(list);
        System.out.println(list.size);

    }












    public static class Node<T>{
       T key;
       Node<T> prev;
       Node<T> next;

       public Node(T key){
           this.key = key;
       }

    }

}

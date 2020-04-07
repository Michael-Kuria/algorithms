package com.michael.trees.RBTree;

public class RBTree<T extends Comparable> {

    Node<T> root; // the root of the tree, it will be the entry point of the tree
    Node<T> nil; // this is a sentinel value, all leaves will point to it as their child, the root will point to it as it's parent

    RBTree(){
      nil = new Node<>(null);
      nil.color = Color.BLACK;
      root = nil;
    }

    RBTree(T key){
        this();
        root = new Node<>(key);
        root.p = nil;
        root.right = nil;
        root.left = nil;
    }

    /**
     * Insert an object T into the tree. The Node will be initialized with the color Red, with this insertion it is possible to violate the R-B Tree
     * property i.e If a sub-tree root color is red both it's children should be black, thus there is need to correct this. This correction is done
     * using insertFixUp()
     *
     * @param x The key of the node to be inserted.
     */
    public void insert(T x){
        Node<T> k = new Node<>(x); // the node to be inserted
        Node<T> y = nil; // trailing pointer for the parent
        Node<T> z = this.root;

        while(!z.equals(nil)){
            y = z;
            if(x.compareTo(z.key) < 0){
                z = z.left;
            }else
                z = z.right;
        }

        if(y.equals(nil)){
            // we are dealing with the root
            this.root = k;

        }else{
            if(x.compareTo(y.key) < 0){
               y.left = k;

            }else {
                y.right = k;
            }

        }
        k.p = y;
        k.right = nil;
        k.left = nil;

        insertFixUp(k);

    }

    /**
     * This function is called each time there is an insertion in the tree. It checks if R-B Tree properties have been
     * violated and fixes it.
     *
     * @param x the Node that has been recently inserted
     */
    public void insertFixUp(Node<T> x){
        // we will only go to the loop if we have 2 red nodes following one another
        while(x.p.color.equals(Color.RED)){
            if(x.p == x.p.p.left){
                Node<T> y = x.p.p.right;

                if(y.color.equals(Color.RED)){
                    y.color = Color.BLACK;
                    x.p.color = Color.BLACK;
                    x.p.p.color = Color.RED;
                    x = x.p.p;
                }else{
                    // y is black
                    if(x == x.p.right){
                        x = x.p;
                        leftRotate(x);
                    }
                    x.p.p.color = Color.RED;
                    x.p.color = Color.BLACK;
                    rightRotate(x.p.p);
                }
            }else{
                Node<T> y = x.p.p.left;

                if(y.color.equals(Color.RED)){
                    y.color = Color.BLACK;
                    x.p.color = Color.BLACK;
                    x.p.p.color = Color.RED;
                    x = x.p.p; // because we have changed its color to red
                }else{
                    if(x == x.p.left){
                        x = x.p;
                        rightRotate(x);
                    }
                    x.p.p.color = Color.RED;
                    x.p.color = Color.BLACK;
                    leftRotate(x.p.p);
                }

            }

        }
        root.color = Color.BLACK;

    }

    /**
     * Preform a left rotation on the node x
     * @param x
     */
    public void leftRotate(Node<T> x){
        Node<T> y = x.right;

        x.right = y.left;
        if(!y.left.equals(nil)){
            y.left.p = x;
        }

        y.p = x.p;
        if(x.p.equals(nil)){
            // we are dealing with the root
            root = y;
        }else if(x == x.p.left){
            x.p.left = y;
        }else{
            x.p.right = y;
        }
        y.left = x;
        x.p = y;

    }

    /**
     * Perform a right rotation about x
     * @param x
     */
    public void rightRotate(Node<T> x){
        Node<T> y = x.left;
        x.left = y.right;

        if(!y.right.equals(nil)){
            y.right.p = x;
        }

        y.p = x.p;
        if(x.p.equals(nil)){
            root = y;
        }else if(x == x.p.left){
            x.p.left = y;
        }else{
            x.p.right = y;
        }

        y.right = x;
        x.p = y;

    }

    /**
     * Find the first node with a key z
     * @param z The key to search for
     * @return the Node with key z, and null if none
     */
    public Node<T> search(T z){
        Node<T> x = this.root;

        while(!x.equals(nil)){

            if(x.key.compareTo(z) == 0){
                return x;
            }else if(z.compareTo(x.key) < 0){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        return x;

    }

    /**
     * Find the node with the least key, in the tree or on the sub-tree rooted at x
     *
     * @param x The root of the subtree or tree, where we are looking for the min.
     * @return The node with the least key.
     */
    public Node<T> min(Node<T> x){

        while(!x.left.equals(nil)){
            x = x.left;
        }

        return x;
    }

    /**
     * Find the node with the greatest key, in the tree or on the sub-tree rooted at x.
     *
     * @param x the root of the tree or of a sub-tree
     * @return the Node<T> whose key is the greatest.
     */
    public Node<T> max(Node<T> x){

        while(!x.right.equals(nil)){
            x = x.right;
        }
        return x;
    }


    /**
     * If the elements are ordered in a ascending order according to their keys, return the Node that comes after x
     * Very useful during deletion of nodes. Can also be used to print elements inorder.
     *
     * @param x The Node whose successor we are finding
     * @return the successor of x
     */
    public Node<T> successor(Node<T> x){

        if(!x.right.equals(nil)){
            return min(x.right);
        }
        // we are a left child of the successor
        Node<T> y = x.p;

        while(x == y.right && !y.equals(nil)){
            x = y;
            y = x.p;
        }
        return y;
    }

    /**
     * The opposite of successor
     *
     * @param x the Node whose predecessor we are finding
     * @return the predecessor of x
     */
    public Node<T> predecessor(Node<T> x){
        if(!x.left.equals(nil)){
            return max(x.left);
        }

        Node<T> y = x.p;
        // if the left node is null, then if y which is it's predecessor (can be nil) is ancestor of x
        while(!y.equals(nil) && x == y.left ){
            x = y;
            y = x.p;
        }
        return y;
    }

    /**
     * Remove {@code Node} y from it's position and replace it with x.
     * Note that we are not setting the left and right child of x, this will be left for the calling function to take care of this.
     * This takes care of cases where y has only one child, making it reusable in the different cases.
     *
     * @param y The {@code Node} that we are removing from the tree
     * @param x The {@code Node} we are replacing y with
     */
    public void transplant(Node<T> y, Node<T> x){

        if(y.p.equals(nil)){
            root = x;
        }else if(y.p.right.equals(y)){
            y.p.right = x;
        }else{
            y.p.left = x;
        }
        x.p = y.p;

    }

    /**
     * Delete the given Node from the tree.
     * @param z
     */
    public void delete(Node<T> z){
        // avoid deleting nil or null node
        if(z.equals(nil) || z == null)
            return;

        Node<T> x,y = z;
        Color yColor = y.color;

        if(z.left.equals(nil)){
            x = z.right;
            transplant(z,z.right);
        }else if(z.right.equals(nil)){
            x = z.left;
            transplant(z, z.left);
        }else{
            y = min(z.right); // the successor of z
            yColor = y.color;
            x = y.right;

            // Note that y has no left child since it is the successor
            if(y.equals(z.right)){
                x.p = y;
            }else{
                transplant(y,y.right);
                // assign z right child to y, this is because y is not an immediate child of z, the left children of z will be
                // assigned to y on the next step.
                y.right = z.right;
                y.right.p = y;

            }
            transplant(z,y);
            // assigning z left child to
            y.left = z.left;
            y.left.p = y;
            y.color = z.color; // y color has changed, if y was black then the black height property would be violated
        }

        if(yColor.equals(Color.BLACK)){
            deleteFixUp(x);
        }
    }

    /**
     * x is the element that replaces y in its position, if x is black then there is trouble that will need to be
     * resolved, this is due to the black height property
     *
     * @param x recently inserted Node.
     */

    public void deleteFixUp(Node<T> x){

        while(!x.equals(root) && x.color.equals(Color.BLACK)){
            Node<T> w;
            if(x.equals(x.p.left)){
                w = x.p.right;
                if(w.color.equals(Color.RED)){
                    w.color = Color.BLACK;
                    x.p.color = Color.RED;
                    leftRotate(x.p);
                    w = x.p.right;
                }

                if(w.left.color.equals(Color.BLACK) && w.right.color.equals(Color.BLACK)){
                    w.color = Color.RED;
                    x = x.p;
                }else if(w.right.color.equals(Color.BLACK)){
                    w.left.color = Color.BLACK;
                    w.color = Color.RED;
                    rightRotate(w);
                    w = x.p.right;
                }else{
                    w.color = x.p.color;
                    x.p.color = Color.BLACK;
                    w.right.color = Color.BLACK;
                    leftRotate(x.p);
                    x = root;
                }


            }else{
                w = x.p.left;

                if(w.color.equals(Color.RED)){
                    w.color = Color.BLACK;
                    x.p.color = Color.RED;
                    rightRotate(x.p);
                    w = x.p.left;
                }

                if(w.right.color.equals(Color.BLACK) && w.left.color.equals(Color.BLACK)){
                    w.color = Color.RED;
                    x = x.p;
                }else if(w.left.color.equals(Color.BLACK)){
                    w.right.color = Color.BLACK;
                    w.color = Color.RED;
                    leftRotate(w);
                    w = x.p.left;

                }else{
                    w.color = x.p.color;
                    x.p.color = Color.BLACK;
                    w.left.color = Color.BLACK;
                    rightRotate(x.p);

                    x = root;
                }

            }

        }
        x.color = Color.BLACK;

    }

    public boolean isEmpty(){
        return this.root.equals(nil);
    }

    public void print(Node<T> x){
        if(!x.equals(nil)){
            System.out.print("["+x.key+", " + x.color +" ," + x.p.color +"]   ");
            print(x.left);
            //System.out.println("["+x.key+", " + x.color + ","+ x.p.color+"]");
            print(x.right);
            //System.out.println("["+x.key+", " + x.color + ","+ x.p.color+"]");
        }
    }


    public static void main(String [] args){

        RBTree<Integer> tree = new RBTree<>();
        tree.insert(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.insert(19);
        tree.insert(8);
        /* tree.insert(11);
        tree.insert(14);
        tree.insert(15);
*/
        tree.print(tree.root);
       /* System.out.println();
        tree.delete(tree.search(1));*/


        //tree.print(tree.root);
        //System.out.println(tree.root.key);

    }
}

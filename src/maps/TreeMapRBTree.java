package maps;

@SuppressWarnings("SuspiciousNameCombination") // for suppressing unnecessary variable name warnings
public class TreeMapRBTree<K extends Comparable<K>, V> {
    private static final boolean RED = false, BLACK = true;
    private boolean CASE_TRACING = false;
    private static class Node<K, V> {
        final private K key;
        private V val;
        private Node<K, V> left, right, parent;
        private boolean color;
        public Node(K k, V v) {
            key = k; val = v;
            left = right = parent = null;
            color = RED;
        }

        public String toString() {
            String colorString = (color == RED)? "RED" : "BLACK";
            if (val != null) return "<" + key.toString() + ", " + val.toString() + ", " + colorString + ">" ;
            else return key.toString();
        }
    }

    private Node<K, V> root; // the root node
    private int numberOfNodes = 0; // stores the number of nodes/records
    public TreeMapRBTree() { }

    public void turnOnCaseTracing() { CASE_TRACING = true; }
    //**********************************************************//
    // checks if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }
    //**********************************************************//
    private boolean isLeftChild( Node<K,V> node ) {
        return node.parent != null && node.parent.left == node;
    }

    private boolean isRightChild( Node<K,V> node ) {
        return node.parent != null && node.parent.right == node;
    }

    private Node<K,V> grandParent( Node<K,V> node ){
        return node.parent.parent;
    }

    private Node<K,V> uncle( Node<K,V> node ){
        if( node == null ) return null;
        return ( isLeftChild(node.parent) ) ? node.parent.parent.right : node.parent.parent.left;
    }

    // right rotates the subtree rooted at node 'y'
    private void rightRotateAt(Node<K,V> y) {
        Node<K,V> x = y.left;
        y.left = x.right;

        if( x.right != null )
            x.right.parent = y;

        x.parent = y.parent;

        if( y.parent == null ) root = x;
        else if( y == y.parent.right ) y.parent.right = x;
        else y.parent.left = x;

        x.right = y;
        y.parent = x;
    }

    // left rotates the subtree rooted at node 'x'
    private void leftRotateAt(Node<K,V> x) {
        Node<K,V> y = x.right;
        x.right = y.left;

        if( y.left != null )
            y.left.parent = x;

        y.parent = x.parent;

        if( x.parent == null )  root = y;
        else if( x == x.parent.left ) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    // inserts a new record <key,value> in the tree and then fixes the tree using re-colorings and rotations
    public boolean put(K key, V value) {
        if( key == null )   throw new IllegalArgumentException("Null keys are not allowed.");
        if( value == null ) throw new IllegalArgumentException("Null values are not allowed.");

        if( CASE_TRACING ) System.out.println("Inserting record <" + key + ", " + value + ">");

        Node<K,V> z = new Node<>(key,value); // node is RED right now

        if( isEmpty() ) {
            root = z;
            root.color = BLACK;
            numberOfNodes++;
            return true;
        }

        Node<K,V> current = root, parentOfCurrent;
        boolean isLeftChild;

        do {
            if (key.compareTo(current.key) == 0) // a record exists with key 'key; insertion failed
                return false;
            else if (key.compareTo(current.key) < 0) { // go to the left subtree and continue
                parentOfCurrent = current;
                current = current.left;
                isLeftChild = true;
            } else { // go the right subtree and continue
                parentOfCurrent = current;
                current = current.right;
                isLeftChild = false;
            }

        } while (current != null);

        // insert the new record as a left/right child
        z.parent = parentOfCurrent;
        if( isLeftChild )  parentOfCurrent.left  = z;
        else               parentOfCurrent.right = z;

        numberOfNodes++;

        // Fixing up the tree by climbing up
        // This part of the code has been inspired from the pseudocode presented in the
        // famous CLRS book 'Introduction to Algorithms' (no need to refer to it in COP 3530).
        while( z.parent != null && z.parent.color == RED ){
            Node<K,V> y = uncle(z);
            if( y != null && y.color == RED ){                      // Case 1
                // Note: Case 1 code is generalized and works both for Case 1a and 1b
                if( CASE_TRACING ) System.out.println("Case 1");    // Case 1
                z.parent.color = BLACK;                             // Case 1
                y.color = BLACK;                                    // Case 1
                grandParent(z).color = RED;                         // Case 1
                z = grandParent(z);                                 // Case 1
            }
            else { // y == null || y.color == BLACK
                boolean isCase2 = false;
                if( isLeftChild(z.parent) && isRightChild(z) ) {
                    isCase2 = true;                                                                 // Case 2
                    if( CASE_TRACING ) System.out.println("Case 2a");                               // Case 2
                    z = z.parent;                                                                   // Case 2
                    if( CASE_TRACING ) System.out.println("Left rotating at " + z.toString() );     // Case 2
                    leftRotateAt(z);                                                                // Case 2
                }                                                                                   // Case 2
                else if ( isRightChild(z.parent) && isLeftChild(z) ) {                              // Case 2
                    isCase2 = true;                                                                 // Case 2
                    if( CASE_TRACING ) System.out.println("Case 2b");                               // Case 2
                    z = z.parent;                                                                   // Case 2
                    if( CASE_TRACING ) System.out.println("Right rotating at " + z.toString() );    // Case 2
                    rightRotateAt(z);                                                               // Case 2
                }

                // Note: if Case 2 is executed, Case 3 must be executed as well
                // However, Case 3 may get executed without Case 2 being executed
                z.parent.color = BLACK;                                                                       // Case 3
                grandParent(z).color = RED;                                                                   // Case 3
                if( isLeftChild(z.parent) ) {                                                                 // Case 3
                    if( CASE_TRACING && !isCase2 )                                                            // Case 3
                        System.out.println("Case 3a " + grandParent(z).toString() );                          // Case 3
                    rightRotateAt(grandParent(z));                                                            // Case 3
                }                                                                                             // Case 3
                else {                                                                                        // Case 3
                    if( CASE_TRACING && !isCase2 )                                                            // Case 3
                        System.out.println("Case 3b " + grandParent(z).toString() );                          // Case 3
                    leftRotateAt( grandParent(z) );                                                           // Case 3
                }
            }
        }

        root.color = BLACK; // color the root BLACK
        return true;
    }
    //**********************************************************//
    // returns the value part of the record whose key is 'key'
    public V get(K key) {
        if( key == null )
            throw new IllegalArgumentException("Null keys are not allowed.");

        Node<K,V> current = root;
        while( current != null) {
            if (key.compareTo(current.key) == 0) // a record exists with key 'key; insertion failed
                return current.val;
            else if (key.compareTo(current.key) < 0)  // go to the left subtree
                current = current.left;
            else  // go the right subtree
                current = current.right;
        }
        return null; // record with key 'key' is not present, return null
    }
    //**********************************************************//
    // updates the value part of the record whose key is 'key' with a new value
    // returns the old value
    public V updateValue(K key, V newValue) {
        if( key == null )
            throw new IllegalArgumentException("Null keys are not allowed.");

        Node<K,V> current = root;
        while( current != null) {
            if (key.compareTo(current.key) == 0) {// a record exists with key 'key; insertion failed
                V oldValue = current.val;
                current.val = newValue;
                return oldValue;
            }
            else if (key.compareTo(current.key) < 0)  // go to the left subtree
                current = current.left;
            else  // go the right subtree
                current = current.right;
        }
        return null; // signal failure; such a record does not exist in the tree
    }
    //**********************************************************//
    // returns the number of records stored in the tree
    public int size() {
        return numberOfNodes;
    }
    //**********************************************************//
    // clears the binary search tree
    public void clear() {
        root = null; numberOfNodes = 0;
    }
    //**********************************************************//
    // find height of the binary search tree
    public int height( ){ return heightRec(root); }
    public boolean isLeaf(Node<K,V> n) { return n.left == null && n.right == null; }
    private int heightRec(Node<K,V> n){
        if( n == null || isLeaf(n) )  return 0;
        else             return 1 + Math.max( heightRec(n.left) , heightRec(n.right) );
    }
    //**********************************************************//
    public void printInOrderTraversal() {
        printInOrderTraversalRecursive(root);
    }

    private void printInOrderTraversalRecursive(Node<K,V> node) {
        if( node == null )
            return;
        printInOrderTraversalRecursive( node.left );
        System.out.println(node);
        printInOrderTraversalRecursive(node.right);
    }
}

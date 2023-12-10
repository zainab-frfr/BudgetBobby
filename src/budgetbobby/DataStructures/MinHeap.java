package budgetbobby.DataStructures;


public class MinHeap<T extends Comparable<T>>  {
    
    private class Node {
        T data; double time;
        Node left, right;

        public double getTime() {
            return time;
        }

        Node(T item, double time) {
            data = item;   this.time = time;
            left = right = null;
        }
    }
    
    
    protected Node root;
    private int currentHeapSize;


    public boolean isEmpty(){
        return root==null;
    }
    
    public MinHeap() {
        this.root = null;
        this.currentHeapSize = 0;
    }

    
    public void insert(T key, double time) {
        root = insertRec(root, key, time);
        currentHeapSize++;
    }
//
//    private Node insertRec(Node root, T key, int time) {
//        if (root == null) {
//            return new Node(key, time);
//        }
//
//        if (key.compareTo(root.data) < 0) {
//            root.left = insertRec(root.left, key, root.left.time);
//        }
//
//        else if (key.compareTo(root.data) > 0) {
//            root.right = insertRec(root.right, key,  root.right.time);
//        }
//
//        return root;
//    }



    public double getMinPriority() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        // Assuming that the priority of the minimum element is stored in the root
        return root.time; // Replace with the actual field or method that represents the priority
    }
    private Node insertRec(Node root, T key, double time) {
        if (root == null) {
            return new Node(key, time);
        }

        // Compare based on time values
        if (time < root.time || (time == root.time && key.compareTo(root.data) < 0)) {
            root.left = insertRec(root.left, key, time);
        } else {
            root.right = insertRec(root.right, key, time);
        }

        return root;
    }


    public T getMin() {
        if (root == null) {
            return null;
        }
        return findMin(root);
    }

    
    private T findMin(Node root) {
        if (root.left == null) {
            return root.data;
        }
        return findMin(root.left);
    }

    
    public T extractMin() {
        if (root == null) {
            return null;
        }

        T minValue = findMin(root);
        root = deleteNode(root, minValue);
        currentHeapSize--;

        return minValue;
    }

    
    protected Node deleteNode(Node root, T key) {
        
        //Root is empty
        if (root == null) {
            return root;
        }
        
        // Node with value in left subtree
        if (key.compareTo(root.data) < 0) {
            root.left = deleteNode(root.left, key);
        } 
        
        // Node with value in right subtree
        else if (key.compareTo(root.data) > 0) {
            root.right = deleteNode(root.right, key);
        } 
        
        // Node with value found
        else {
            // Case 1: Node with one child or no child
            if (root.left == null) {
                return root.right;
            } 
            else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = findMin(root.right);

            // Delete the inorder successor
            root.right = deleteNode(root.right, root.data);
        }

        return root;
    }

    
    public void print() {
        inOrderTraversal(root);
        System.out.println();
    }

    
    private void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.println(root.data + " ");
            inOrderTraversal(root.right);
        }
    }
}

package budgetbobby.DataStructures;


public class MinHeap<T extends Comparable<T>>  {
    
    private class Node {
        T data;
        Node left, right;

        Node(T item) {
            data = item;
            left = right = null;
        }
    }
    
    
    protected Node root;
    private int currentHeapSize;

    
    public MinHeap() {
        this.root = null;
        this.currentHeapSize = 0;
    }

    
    public void insert(T key) {
        root = insertRec(root, key);
        currentHeapSize++;
    }

    private Node insertRec(Node root, T key) {
        if (root == null) {
            return new Node(key);
        }

        if (key.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, key);
        } 
        
        else if (key.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, key);
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

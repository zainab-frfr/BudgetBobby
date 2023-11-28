
package budgetbobby.DataStructures;

public class TestingMain {
    
    public static void main(String[] args){
        
        MinHeap<Integer> heap = new MinHeap<>();
        Integer n1 = 3;
        Integer n2 = 45;
        Integer n3 = 1;
        Integer n4 = 15;
        Integer n5 = 5;
        Integer n6 = 101;
        
        heap.insert(n1);
        heap.insert(n2);
        heap.insert(n3);
        heap.insert(n4);
        heap.insert(n5);
        
        
        heap.print();
        System.out.println("Min element: " + heap.getMin());

        System.out.println("\nExtracted Min: " + heap.extractMin());
        System.out.println("\nMin element: " + heap.getMin());
        
        System.out.println("\nHeap after extraction:");
        heap.print();
        
        heap.deleteNode(heap.root, n4);
        heap.insert(n6);
        System.out.println("\nHeap after deletion of '15':");
        heap.print();
        
        
        
    }
    
}

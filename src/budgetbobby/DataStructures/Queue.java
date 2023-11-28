
package budgetbobby.DataStructures;

public class Queue<T> {

    Node<T> F;
    Node<T> R;
    int size;

    // Constructor
    public Queue() {
        this.F = null;
        this.R = null;
        this.size = 0;
    }

    public void add(T obj) {
        Node<T> newNode = new Node<>(obj);

        if (isEmpty()) {
            F = newNode;
            R = newNode;
        } else {
            R.next = newNode;
            R = newNode;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null; 
        }

        T temp = F.data;
        F = F.next;

        if (F == null) {
            R = null;
        }
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return (F == null);
    }

    public void print() {
        String str = "";
        Node<T> temp = F;

        while (temp != null) {
            str = str + temp.data + " ";
            temp = temp.next;
        }
        System.out.println(str);
    }
    
    public int size(){
        return size;
    }
    
}

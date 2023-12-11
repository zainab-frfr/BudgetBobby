package budgetbobby.DataStructures;

public class Node<T> {
    protected T data;
    protected Node<T> next;
    protected Node<T> prev;

    Node(T d){
        data = d;
    }

    public T getData() {
        return data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
    
}

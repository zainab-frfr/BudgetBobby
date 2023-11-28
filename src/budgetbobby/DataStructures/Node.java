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
}

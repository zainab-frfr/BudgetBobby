package budgetbobby.DataStructures;

import budgetbobby.User;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public Node<T> getHead() {
        return head;
    }

    public LinkedList() {
        head = null;
    }

    public void insert(T d) {
        Node<T> NewNode = new Node<T>(d);
        Node<T> x = null;
        if (head == null) {
            head = NewNode;
            tail = head;
        } else {
            x = head;
            while (x.next != null) {
                x = x.next;
            }
            x.next = NewNode;
            tail = x.next;
        }

    }

    public Boolean find(T d) {
        Node<T> x = head;
        if (head == null) return false;
        while (x.data != d && x.next != null) {
            x = x.next;
        }

        return x.data == d;
    }


//    public Object findObject(T d) {
//        Node<T> x = head;
//        if (head == null) return false;
//        while (x.getData() != d && x.next != null) {
//            x = x.next;
//        }
//        if(x.data == d){
//            return x;
//        }
//        else
//            return null;
//    }

    public void clear() {
        head = null;
    }


    public void delete(T d) {
        Node<T> x = head;

        if(head!=null) {
            if (x.data == d) {
                head = x.next;
            } else {
                while (x.next != null) {
                    if (x.next.data == d) {
                        x.next = x.next.next;
                        return;
                    } else x = x.next;
                }
            }
        }
    }

    public String toString() {
        String s = "";
        if(head==null) return "list is empty";

        Node<T> Temp= head;
        while(Temp!=null){
            s += (Temp.data) + " ";
            Temp=Temp.next;
        }
        return s;
    }
}
package budgetbobby.DataStructures;

import budgetbobby.User;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    private int length = 0;

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

        length++;

    }

    public Boolean find(T d) {
        Node<T> x = head;
        if (head == null) return false;
        while (x.data != d && x.next != null) {
            x = x.next;
        }

        return x.data == d;
    }


    public Object findUser(int d) {
        Node<User> x = (Node<User>) head;
        if (head == null) return false;
        while (x.getData().getID()!= d && x.next != null) {
            x = x.next;
        }
        if(x.data.getID()== d){
            return x;
        }
        else
            return null;
    }

    public void clear() {
        head = null;
    }


    public int getLength() {
        return length;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void delete(T d) {
        Node<T> x = head;

        if(head!=null) {
            if (x.data == d) {
                head = x.next;
                length--;
            } else {
                while (x.next != null) {
                    if (x.next.data == d) {
                        x.next = x.next.next;
                        length--;
                        return;
                    } else x = x.next;
                }
            }
        }
    }


    public Node<T> getNode(int idx){
        int counter = 0;
        Node<T> temp = head;

        while(temp != null){
            if(counter==idx){
                return temp;
            }
            counter++;
            temp = temp.next;
        }

        return null;
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

    public boolean findUserEmail(String email){
        Node<User> x = (Node<User>) head;
        if (head == null) return false;
        while (x.getData().getEmail().equals(email)&& x.next != null) {
            x = x.next;
        }
        return x.getData().getEmail().equals(email);
    }
}
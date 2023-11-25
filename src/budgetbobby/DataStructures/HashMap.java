package budgetbobby.DataStructures;

import budgetbobby.User;

public class HashMap<T> {
    LinkedList<T>[] Table;


    public HashMap(int s){
        Table=new LinkedList[s+(s/3)];
    }

    public int Hash(T obj){
//        return  ((T)obj). % Table.length;
        if(obj instanceof User){
            return ((User) obj).getID() % Table.length;
        }
        return -1;
    }

    public void insert(T obj){
        int index = Hash(obj);
        if(Table[index]==null)
            Table[index] = new LinkedList<>();
        Table[index].insert(obj);
    }

    public Boolean find(T obj) {
        int idx = Hash(obj);
        if(Table[idx]!=null)
            return Table[idx].find(obj);
        return false;
    }

//    public Object findObject(T obj) {
//        int idx = Hash(obj);
//        if(Table[idx]!=null) {
//            Object var = (Object) Table[idx].findObject(obj);
//            if (var != null) {
//                return var;
//            }
//        }
//        return null;
//    }

    public void delete(T obj){
        int idx = Hash(obj);
        if(Table[idx]==null) System.out.println("not present");
        else Table[idx].delete(obj);
    }

    public void displayTable() {
        for (LinkedList<T> tLinkedList : Table) {
            if (tLinkedList != null)
                System.out.println(tLinkedList.toString());
        }
    }
}

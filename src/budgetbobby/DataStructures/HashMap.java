package budgetbobby.DataStructures;

import budgetbobby.FoodItem;
import budgetbobby.User;

import java.util.Arrays;

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
        // code according to price
        if(obj instanceof FoodItem){
            return (int)(((FoodItem) obj).getPrice()) % Table.length;
        }
        return -1;
    }

    public int HashID(int id){
        return id % Table.length;
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

    public User findUser(int id) {
        int idx = HashID(id);
        if(Table[idx]!=null) {
            User var = (User) Table[idx].findUser(id);
            if (var != null) {
                return var;
            }
        }
        return null;
    }

    public void delete(T obj){
        int idx = Hash(obj);
        if(Table[idx]==null) System.out.println("not present");
        else Table[idx].delete(obj);
    }

//    public void displayTable() {
//        for (LinkedList<T> tLinkedList : Table) {
//            if (tLinkedList != null)
//                System.out.println(tLinkedList.toString());
//        }
//    }

    public boolean findUserEmail(String email){
        //LinkedList<T>[] Table;
        boolean found = false;

        for (int i = 0; i < Table.length; i++) {
            if(Table[i]!=null){
                found = Table[i].findUserEmail(email);
                if(found) break;
            }
        }
        return found;
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "Table=" + Arrays.toString(Table) +
                '}';
    }


}

package budgetbobby;


import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;

public class Bill {
    private User user;
    private String restaurant;
    private LinkedList<FoodItem> orderedItems;

    private double billAmount;

    public Bill(User user, String restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.billAmount = 0 ;
        orderedItems =  new LinkedList<FoodItem>();
    }

    public LinkedList<FoodItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(LinkedList<FoodItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public User getUserName() {
        return user;
    }

    public void setUserName(User userName) {
        this.user = userName;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public double getBillAmount(){
        Node<FoodItem> temp = orderedItems.getHead();

        while(temp != null){
            billAmount += temp.getData().getPrice();
            temp = temp.getNext();
        }

        return billAmount;

    }

    @Override
    public String toString() {
        String s = "";
              s =  "User=" + user +
                ", restaurant='" + restaurant + '\'';
        if(orderedItems!=null){
            s += orderedItems.toString();
        }
        s += "bill: " + getBillAmount();
        return  s;
    }
}
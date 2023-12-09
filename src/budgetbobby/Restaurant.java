/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import budgetbobby.DataStructures.HashMap;
import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;

/**
 *
 * @author zaina
 */
public class Restaurant {

    LinkedList<User> customers = new LinkedList<>();
    private String name;
    private String area;
    private double avgRating;
    HashMap<FoodItem> itemsOFRestaurant = new HashMap<>(15);//we're hashing based on price
    LinkedList<FoodItem> itemsLinkedList = new LinkedList<>();

    public Restaurant(String name, String area, double avgRating) {
        this.name = name;
        this.area = area;
        this.avgRating = avgRating;
    }

    // method of searching 
    public void searchCombinations(int budget) {
        LinkedList<LinkedList<FoodItem>>[] combinations = new LinkedList[budget + 1];

        for (int i = 0; i <= budget; i++) {
            combinations[i] = new LinkedList<LinkedList<FoodItem>>();
        }

        for (int i = 0; i < itemsLinkedList.getLength(); i++) { //iterating through fooditems
            FoodItem curr = itemsLinkedList.getNode(i).getData();
            LinkedList<FoodItem> currList = new LinkedList<>();
            currList.insert(curr);

            combinations[0].insert(currList);

            for (int j = 0; j < combinations.length; j++) {// iterating through combinations array 
                if (curr.getPrice() <= j) {
                    int idx = (j - this.itemsLinkedList.getNode(i).getData().getPrice());
                    if (combinations[idx].getTail() != null) {
                        LinkedList<FoodItem> tailListAtPrev = new LinkedList<FoodItem>();
                        tailListAtPrev.insert(combinations[idx].getTail().getData().getTail().getData());
                        //System.out.println(combinations[idx].getTail().getData());
                        if (tailListAtPrev != null) {
                            combinations[j].insert(tailListAtPrev);
                            if ((curr.getPrice() != j) && (j % curr.getPrice() == 0)) {
//                                System.out.println(i + " " + j);
                                for (int k = 0; k < (j / curr.getPrice()) - 1; k++) {
                                    combinations[j].getTail().getData().insert(curr);
                                }
                            } else if ((curr.getPrice() != j)) {
                                combinations[j].getTail().getData().insert(curr);
                            }

                        }
                    }

                }

                //removing entry if it is less than 12
                if (combinations[j].getTail() != null && j != 0) {
                    LinkedList<FoodItem> lastCombo = combinations[j].getTail().getData();
                    Node<FoodItem> temp = lastCombo.getHead();
                    int sum = 0;
                    while (temp != null) {
                        sum += temp.getData().getPrice();
                        temp = temp.getNext();
                    }
                    if (sum != j) {
                        combinations[j].removeTail();
                    }
                }
            }
        }

//      System.out.println(combinations[budget]);
        for (int i = 1; i < combinations.length; i++) {
            System.out.println("Combinations for " + i);
            System.out.println(combinations[i].outerLinkedList() + "\n");
        }
    }

    public void addFoodItem(FoodItem item) {
        itemsOFRestaurant.insert(item);
    }

    public void addFoodItemToList(FoodItem item) {
        itemsLinkedList.insert(item);
    }

    public void addCustomer(User customer) {
        customers.insert(customer);
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public double getAvgRating() {
        return avgRating;
    }

    @Override
    public String toString() {
        return "Restaurant{"
                + "name='" + name + '\''
                + ", area='" + area + '\''
                + ", itemsOFRestaurant=" + itemsOFRestaurant
                + ", Average Rating: " + avgRating + '}' + "\n";
    }
}

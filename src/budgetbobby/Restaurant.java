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
    public void searchCombinations(int budget, int calories, String mealtime, String category) {
        LinkedList<LinkedList<FoodItem>>[] combinations = new LinkedList[budget + 1];

        for (int i = 0; i <= budget; i++) {
            combinations[i] = new LinkedList<LinkedList<FoodItem>>();
        }

        for (int i = 0; i < itemsLinkedList.getLength(); i++) { //iterating through fooditems
            FoodItem curr = itemsLinkedList.getNode(i).getData();
            LinkedList<FoodItem> currList = new LinkedList<>();
            currList.insert(curr);

            boolean currFoodItemInserted = false;
            //user has not selected meal time or category
            if (mealtime.equalsIgnoreCase("Select Meal Time") && category.equalsIgnoreCase("Select cuisine")) {
                combinations[0].insert(currList);
                currFoodItemInserted = true;
            }
            //user has selected both
            else if(category.equalsIgnoreCase(curr.getCategory()) && mealtime.equalsIgnoreCase(curr.getMealTime())){
                combinations[0].insert(currList);
                currFoodItemInserted = true;
            }
            //user has selected either meal time or category 
            else if (mealtime.equalsIgnoreCase(curr.getMealTime()) && category.equalsIgnoreCase("Select cuisine")) {
                combinations[0].insert(currList);
                currFoodItemInserted = true;
            }
            else if(category.equalsIgnoreCase(curr.getCategory()) && mealtime.equalsIgnoreCase("Select Meal Time")){
                combinations[0].insert(currList);
                currFoodItemInserted =true;
            }

            if (currFoodItemInserted) {
                // iterating through combinations array; each index of the array represents a value of current max budget
                // adding the current foor item at indexes where a combination is possible 
                for (int j = 0; j < combinations.length; j++) {
                    if (curr.getPrice() <= j) {
                        int idx = (j - this.itemsLinkedList.getNode(i).getData().getPrice());
                        if (combinations[idx].getTail() != null) {
                            LinkedList<FoodItem> tailListAtPrev = new LinkedList<FoodItem>();
                            tailListAtPrev.insert(combinations[idx].getTail().getData().getTail().getData());
                            if (tailListAtPrev != null) {
                                combinations[j].insert(tailListAtPrev);
                                if ((curr.getPrice() != j) && (j % curr.getPrice() == 0)) {
                                    for (int k = 0; k < (j / curr.getPrice()) - 1; k++) {
                                        combinations[j].getTail().getData().insert(curr);
                                    }
                                } else if ((curr.getPrice() != j)) {
                                    combinations[j].getTail().getData().insert(curr);
                                }

                            }
                        }

                    }

                    //removing combination if it is less than j (current max budget)
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

                    //removing combination if calorie limit is exceeded
                    if (combinations[j].getTail() != null && j != 0) {
                        LinkedList<FoodItem> lastCombo = combinations[j].getTail().getData();
                        Node<FoodItem> temp = lastCombo.getHead();
                        int totalCalories = 0;
                        while (temp != null) {
                            totalCalories += temp.getData().getCalorie();
                            temp = temp.getNext();
                        }
                        if (totalCalories > calories) {
                            combinations[j].removeTail();
                        }
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


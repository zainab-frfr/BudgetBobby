/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import budgetbobby.DataStructures.Graphs;
import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author zaina
 */
public class Manager {

    // Hashmap of restaurant
    UserAccounts accounts;
    Delivery delivery;
    manager_login_signin manager_login_signin;
    Graphs graphs = new Graphs(5);

    LinkedList<Restaurant> allRestaurants;
    LinkedList<FoodItem> foodItemLinkedList;

    Scanner input = new Scanner(System.in);

    public Manager() {
        allRestaurants = new LinkedList<Restaurant>();
        foodItemLinkedList = new LinkedList<>();
        accounts = new UserAccounts();
        manager_login_signin = new manager_login_signin(accounts);
        delivery = new Delivery();

        String restaurantPath = "src/restaurants.txt";
        String foodPath = "src/FoodItems.txt";
        String usersPath = "src/Users.txt";

        fileReading(restaurantPath, "restaurant");
        fileReading(foodPath, "fooditem");
        fileReading(usersPath, "users"); //columns are 5 but there is conflict with line 37

        addingFoodItemsIntoRestaurants(10);

    }

    public void fileReading(String filePath, String str) {
        try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            bufferedReader.readLine();
            bufferedReader.readLine();
            // Read the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] separated = line.split("\\|");
                if (str.equalsIgnoreCase("restaurant")) {
                    readingRestaurants(separated);
                } else if (str.equalsIgnoreCase("fooditem")) {
                    readingItems(separated);
                } else if (str.equalsIgnoreCase("users")) {
                    readingUsers(separated);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readingRestaurants(String[] separated) {
        Restaurant r = new Restaurant(separated[0], separated[1], Double.parseDouble(separated[2]));
        allRestaurants.insert(r);
    }

    public void readingItems(String[] separated) {
        //System.out.println(Arrays.toString(separated));
        int price = Integer.parseInt(separated[1]);
        int calorie = Integer.parseInt(separated[3]);
        FoodItem foodItem = new FoodItem(separated[0], price, separated[2], calorie, separated[4]);
        foodItemLinkedList.insert(foodItem);
    }

    public void readingUsers(String[] separated) {
        int ID = Integer.parseInt(separated[3]);
        User user = new User(separated[0], separated[1], separated[2], ID, separated[4]);
        accounts.addUser(user);
    }

    public void addingFoodItemsIntoRestaurants(int foodItemsPerRestaurant) {
        int count = 0;
        for (int i = 0; i < allRestaurants.getLength(); i++) {
            for (int j = count; j < foodItemsPerRestaurant + count && j < foodItemLinkedList.getLength(); j++) {
                allRestaurants.getNode(i).getData().addFoodItem(foodItemLinkedList.getNode(j).getData());
                allRestaurants.getNode(i).getData().addFoodItemToList(foodItemLinkedList.getNode(j).getData());
            }
            count += foodItemsPerRestaurant;
        }
    }

    //method to calculate top rated 
    public Restaurant topRated() {
        Node<Restaurant> temp = allRestaurants.getHead();
        Restaurant topRated = null;
        double currMaxRating = 0;
        double prevMaxRating = 0;
        while (temp != null) {
            prevMaxRating = currMaxRating;
            currMaxRating = Math.max(prevMaxRating, temp.getData().getAvgRating());
            if (prevMaxRating < currMaxRating) {
                topRated = temp.getData();
            }
            temp = temp.getNext();
        }

        return topRated;
    }

//    public void allCombinations(int budget, int calories, String mealtime, String category) {
//
////        Node<Restaurant> curr = this.allRestaurants.getHead();
////
////        while (curr != null) {
////            System.out.println("Restaurant: " + curr.getData().getName());
////            curr.getData().searchCombinations(budget, calories, mealtime, category);
////            curr = curr.getNext();
////        }
//
//        Node<Restaurant> curr = this.allRestaurants.getHead();
//        System.out.println("Restaurant: " + curr.getData().getName());
//        curr.getData().searchCombinations(budget, calories, mealtime, category);
//
//    }



    //method to input user selection


    public void RestaurantsDistances(){

        graphs.addVertex("Bahadurabad");
        graphs.addVertex("Clifton");
        graphs.addVertex("Sindhi Muslim");
        graphs.addVertex("PECHS");
        graphs.addVertex("Gulshan");

        graphs.addEdge("Bahadurabad", "Sindhi Muslim", 5);
        graphs.addEdge("Bahadurabad", "Clifton", 10);
        graphs.addEdge("Bahadurabad", "Gulshan", 15);
        graphs.addEdge("Bahadurabad", "PECHS", 20);

        graphs.addEdge("Sindhi Muslim", "Clifton", 8);
        graphs.addEdge("Sindhi Muslim", "Gulshan", 12);
        graphs.addEdge("Sindhi Muslim", "PECHS", 18);

        graphs.addEdge("Clifton", "Gulshan", 6);
        graphs.addEdge("Clifton", "PECHS", 14);

        graphs.addEdge("Gulshan", "PECHS", 10);
    }




    public void combinationAllRestaurants(int areaNo,int budget, int calories, String mealtime, String category){
        String[] areasNearestToFarthest = graphs.shortestPath(areaNo);
        for (String area : areasNearestToFarthest) {
            System.out.println("*****************" + area + "*****************");

            // Iterate through restaurants and print combinations only for those in the current area
            for (int j = 0; j < allRestaurants.getLength(); j++) {
                Restaurant restaurant = allRestaurants.getNode(j).getData();
                if (restaurant.getArea().equals(area)) {
                    System.out.println("*****************" + restaurant.getName() + "*****************");
                    restaurant.searchCombinations(budget, calories, mealtime, category);
                }
            }
        }

    }



    // filter display
}

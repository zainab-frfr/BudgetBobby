/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

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

    LinkedList<Restaurant> allRestaurants;
    LinkedList<FoodItem> foodItemLinkedList;

    Scanner input = new Scanner(System.in);
    public Manager() {
        allRestaurants = new LinkedList<Restaurant>();
        foodItemLinkedList = new LinkedList<>();
        accounts = new UserAccounts();
        manager_login_signin = new manager_login_signin(accounts);

        String restaurantPath = "src/restaurants.txt";
        String foodPath = "src/FoodItems.txt";
        String usersPath = "src/Users.txt";

        fileReading(restaurantPath, "restaurant");
        fileReading(foodPath, "fooditem");
        fileReading(usersPath, "users"); //columns are 5 but there is conflict with line 37

        addingFoodItemsIntoRestaurants(10);

    }

    
    public void fileReading(String filePath, String str){
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            bufferedReader.readLine();
            bufferedReader.readLine();
            // Read the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] separated = line.split("\\|");
                if(str.equalsIgnoreCase("restaurant")){
                    readingRestaurants(separated);
                }
                else if(str.equalsIgnoreCase("fooditem")){
                    readingItems(separated);
                }
                else if(str.equalsIgnoreCase("users")){
                    readingUsers(separated);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void readingRestaurants(String[] separated){
        Restaurant r = new Restaurant(separated[0], separated[1],Double.parseDouble(separated[2]));
        allRestaurants.insert(r);
    }
    public void readingItems(String[] separated){
        //System.out.println(Arrays.toString(separated));
        double price = Double.parseDouble(separated[1]);
        int calorie = Integer.parseInt(separated[3]);
        FoodItem foodItem = new FoodItem(separated[0],price,separated[2],calorie, separated[4]);
        foodItemLinkedList.insert(foodItem);
    }

    public void readingUsers(String[] separated){
        int calorie = Integer.parseInt(separated[3]);
        int ID = Integer.parseInt(separated[4]);
        User user = new User(separated[0],separated[1], separated[2],calorie, ID, separated[5]);
        accounts.addUser(user);
    }
    public void addingFoodItemsIntoRestaurants(int foodItemsPerRestaurant){
        int count = 0;
        for(int i = 0; i < allRestaurants.getLength(); i++){
            for(int j = count; j < foodItemsPerRestaurant+count && j<foodItemLinkedList.getLength(); j++){
                allRestaurants.getNode(i).getData().addFoodItem(foodItemLinkedList.getNode(j).getData());
            }
            count += foodItemsPerRestaurant;
        }
    }

    
    //method to calculate top rated 
    public Restaurant topRated(){
        Node<Restaurant> temp = allRestaurants.getHead();
        Restaurant topRated = null;
        double currMaxRating = 0;
        double prevMaxRating = 0;
        while(temp!=null){
            prevMaxRating = currMaxRating;
            currMaxRating = Math.max(prevMaxRating, temp.getData().getAvgRating());
            if(prevMaxRating<currMaxRating){
                topRated = temp.getData();
            }
            temp = temp.getNext();
        }
        
        return topRated;
    }

    //method to input user selection
    
    
    // filter display
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import budgetbobby.DataStructures.HashMap;
import budgetbobby.DataStructures.LinkedList;

/**
 *
 * @author zaina
 */
public class Restaurant {
   LinkedList<User> customers = new LinkedList<>();
   private String name;
   private String area;
   //menu hashTable left
   HashMap<FoodItem> itemsOFRestaurant = new HashMap<>(15);//we're hashing based on price



   private int ordersPerDay = 0; // our cycle of a day is one time program run
   private double totalStars; // total stars by customers restaurant
   
   
   // method of searching

   
   // method of calculating the avg rating of
   public double avgRating(){
      totalStars+= customers.getTail().getData().getRating();
      return totalStars/this.ordersPerDay;
   }

   public void addFoodItem(FoodItem item){
      itemsOFRestaurant.insert(item);
   }

   public void addCustomer(User customer){
      customers.insert(customer);
      ordersPerDay++;
   }
   public String getName() {
      return name;
   }

   public String getArea() {
      return area;
   }

   public int getOrdersPerDay() {
      return ordersPerDay;
   }

   public double getStars() {
      return totalStars;
   }
}

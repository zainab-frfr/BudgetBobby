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
   private double avgRating;
   HashMap<FoodItem> itemsOFRestaurant = new HashMap<>(15);//we're hashing based on price

   public Restaurant(String name, String area, double avgRating) {
      this.name = name;
      this.area = area;
      this.avgRating = avgRating;
   }
   // method of searching 

   
   

   public void addFoodItem(FoodItem item){
      itemsOFRestaurant.insert(item);
   }

   public void addCustomer(User customer){
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
      return "Restaurant{" +
              "name='" + name + '\'' +
              ", area='" + area + '\'' +
              ", itemsOFRestaurant=" + itemsOFRestaurant +
              ", Average Rating: "+avgRating+'}'+"\n";
   }
}

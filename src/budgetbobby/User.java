/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

/**
 *
 * @author zaina
 */
public class User implements Comparable<User> {
    private String name;
    private String area;
    private String email;
    private int calories;

    private int ID;
    private double rating;

    private Restaurant restaurant;

    private Bill bill;

    // user should also have a list of bills to track diff bills of diff times of orders

    public User(String name, String area, String email, int calories, int id) {
        this.name = name;
        this.area = area;
        this.email = email;
        this.calories = calories;
        this.ID = id;
    }

    //create bill shall be called when the user confirms the order
    public void createBill(Restaurant restaurant) {
        this.restaurant = restaurant;
        restaurant.addCustomer(this);
        this.bill = new Bill(this, restaurant.getName());
    }

    public Bill getBill() {
        return bill;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", area=" + area + ", email=" + email + ", calories=" + calories + ", orderedList="  + bill.getOrderedItems() + '}';
    }
    
    
    
    
}

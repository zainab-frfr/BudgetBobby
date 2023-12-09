/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

/**
 *
 * @author zaina
 */
public class FoodItem {
    private String name;
    private int calorie;
    private int price;
    private String Category;
    private String mealTime; //breakfast and meal
    
    FoodItem(String name, int price, String Category, int calorie,   String mealTime){
        this.name = name;
        this.calorie = calorie;
        this.price = price;
        this.Category = Category;
        this.mealTime = mealTime;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    @Override
    public String toString() {
        //return "FoodItem{" + "name=" + name + ", calorie=" + calorie + ", price=" + price + ", Category=" + Category + ", mealTime=" + mealTime + '}';
        return "" + name +" " + price+"     ";
    }


    
    
    
}
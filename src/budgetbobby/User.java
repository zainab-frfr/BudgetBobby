/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

/**
 *
 * @author zaina
 */
public class User {
    private String name;
    private String area;
    private String email;
    private int calories;
    private double billAmount;

    public User(String name, String area, String email, int calories, double billAmount) {
        this.name = name;
        this.area = area;
        this.email = email;
        this.calories = calories;
        this.billAmount = billAmount;
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

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", area=" + area + ", email=" + email + ", calories=" + calories + ", billAmount=" + billAmount + '}';
    }
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package budgetbobby;


import budgetbobby.DataStructures.Graphs;

/**
 *
 * @author zaina
 */
public class BudgetBobby {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        //0:Bahadurabad
        //1: clifton
        //2: Sindhi Muslim
        //3: PECHS
        //4: Gulshan

        


        Manager manager = new Manager();
        manager.RestaurantsDistances();
        manager.combinationAllRestaurants(3, 8, Integer.MAX_VALUE, "Meal", "Vegetarian");
    }
}


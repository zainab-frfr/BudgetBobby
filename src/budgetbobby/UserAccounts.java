/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import budgetbobby.DataStructures.*;

/**
 *
 * @author zaina
 */
public class UserAccounts {

    HashMap<User> allUsers; //hashmap of users/user id

    public UserAccounts() {
        this.allUsers = new HashMap<>(20);
    }

    //create user
    public void addUser(User newUser){
        allUsers.insert(newUser);
    }
    
    public boolean findUser(String email){
        return allUsers.findUserEmail(email);
    }
    //find user
    public User findUser(int ID){
        return  allUsers.findUser(ID);
    }

    @Override
    public String toString() {
        return "" + allUsers;
    }
}

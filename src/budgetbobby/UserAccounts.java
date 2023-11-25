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
    //hashmap of users/user id
    HashMap<User> allUsers;

    public UserAccounts() {
        this.allUsers = new HashMap<>(20);
    }

    //create user
    public void addUser(User newUser){
        allUsers.insert(newUser);
    }
    //find user
//    public User findUser(int ID){
//        allUsers.findObject();
//    }
}

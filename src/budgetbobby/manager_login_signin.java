package budgetbobby;

import java.io.FileWriter;
import java.io.IOException;

public class manager_login_signin {

    UserAccounts accounts;
    boolean isPresentLogin;
    boolean isPresentSignup;
    
    User currUser;
    
    int lastID;

    public manager_login_signin(UserAccounts accounts, int lastID) {
        this.accounts = accounts;
        lastID = this.lastID;
    }

    public boolean checkIfUserExitsID(int ID) {
        return accounts.findUser(ID) != null;
    }

    public boolean checkIfUserExistEmail(String email) {
        return accounts.allUsers.findUserEmail(email);
    }

    //login methods
    public void login(int ID, String password) {
        isPresentLogin = checkIfUserExitsID(ID);
        if (!isPresentLogin) {
            System.out.println("ID doesn't exist");
            //go to sign up page
            //signUp(ID);
        } else {
            if (accounts.findUser(ID).getPassword().equals(password)) {
                System.out.println("successfully logged in");
                currUser = accounts.findUser(ID);
            } else {
                System.out.println("Incorrect Password");
            }
        }

    }

    public boolean getIsPresentLogin() {
        return isPresentLogin;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }
    
    
    //signup methods

    //id will be generated in manager and that will be sent here from the file by one increment in the counter
    public void signUp(String userName, String email, String area, int ID, String password) throws IOException {

        isPresentSignup = checkIfUserExistEmail(email);
        if (isPresentSignup) {
            System.out.println("You already have an account");
            //go to login page
            //login(ID);
        } else {
            User toAdd = new User(userName, email, area, ID, password);
            currUser = toAdd;
            System.out.println(currUser.getEmail());
            accounts.addUser(toAdd);
            writingUser(toAdd);

        }
    }

    public User getCurrUser() {
        return currUser;
    }
    
    
    
    public boolean getIsPresentSignup() {
        return isPresentSignup;
    }

    public void writingUser(User user) throws IOException {

        String usersPath = "src/Users.txt";
        FileWriter fileWriter = new FileWriter(usersPath, true);
        String userString = user.getName() + "|" + user.getEmail() + "|" + user.getArea() + "|" +  user.getID()+"|"+user.getPassword();
        //John Doe|john.doe@email.com|Gulshan|500|100
        fileWriter.write(userString+"\n");
        fileWriter.close();

    }

}

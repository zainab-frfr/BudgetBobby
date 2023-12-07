package budgetbobby;

import java.io.FileWriter;
import java.io.IOException;

public class manager_login_signin {

    UserAccounts accounts;

    public manager_login_signin(UserAccounts accounts) {
        this.accounts = accounts;
    }

    public boolean checkIfUserExitsID(int ID) {
        return accounts.findUser(ID) != null;
    }

    public boolean checkIfUserExistEmail(String email) {
        return accounts.allUsers.findUserEmail(email);
    }

    //login methods
    public void login(int ID, String password) {
        boolean isPresent = checkIfUserExitsID(ID);
        if (!isPresent) {
            System.out.println("ID doesn't exist");
            //go to sign up page
            //signUp(ID);
        } else {
            if (accounts.findUser(ID).getPassword().equals(password)) {
                System.out.println("successfully logged in");
            } else {
                System.out.println("Incorrect Password");
            }
        }

    }
    //signup methods

    //id will be generated in manager and that will be sent here from the file by one increment in the counter
    public void signUp(String userName, String email, String area, int calories, int ID, String password) throws IOException {

        boolean isPresent = checkIfUserExistEmail(email);
        if (isPresent) {
            System.out.println("You already have an account");
            //go to login page
            //login(ID);
        } else {
            User toAdd = new User(userName, email, area, calories, ID, password);
            accounts.addUser(toAdd);
            writingUser(toAdd);

        }
    }

    public void writingUser(User user) throws IOException {

        String usersPath = "src/Users.txt";
        FileWriter fileWriter = new FileWriter(usersPath, true);
        String userString = user.getName() + "|" + user.getEmail() + "|" + user.getArea() + "|" + user.getCalories() + "|" + user.getID()+"|"+user.getPassword();
        //John Doe|john.doe@email.com|Gulshan|500|100
        fileWriter.write("\n" + userString);
        fileWriter.close();

    }

}

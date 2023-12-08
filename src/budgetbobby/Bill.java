package budgetbobby;

import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bill {

    private User user;
    private String restaurant;
    private LinkedList<FoodItem> orderedItems;

    private double billAmount;

    public Bill(User user, String restaurant) {
        this.user = user;
        this.restaurant = restaurant;
        this.billAmount = 0;
        orderedItems = new LinkedList<FoodItem>();
    }

    public LinkedList<FoodItem> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(LinkedList<FoodItem> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public User getUserName() {
        return user;
    }

    public void setUserName(User userName) {
        this.user = userName;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public double getBillAmount() {
        Node<FoodItem> temp = orderedItems.getHead();

        while (temp != null) {
            billAmount += temp.getData().getPrice();
            temp = temp.getNext();
        }

        return billAmount;

    }

    public void sendEmail() {
        try {
            // Command to execute Python script
            // Command to execute Python script
            String pythonScriptPath = "src/send_email.py";

            List<String> billArray = new ArrayList<>();
            billArray.add("zainab.rehman.frfr@gmail.com");
            billArray.add("Areeba");
            billArray.add("ROLL EXPRESS");
            billArray.add(String.valueOf(250.75)); // Convert double to String

            // Command and arguments
            List<String> cmd = new ArrayList<>();
            cmd.add("C:/Python311/python.exe");
            cmd.add(pythonScriptPath);
            cmd.addAll(billArray); // Add all elements from billArray to the command list

            // Create ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder(cmd);

            // Start the process
            Process process = pb.start();

            // Read output from the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Read errors from the Python script
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.out.println("Error: " + line);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Python script executed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        String s = "";
        s = "User=" + user
                + ", restaurant='" + restaurant + '\'';
        if (orderedItems != null) {
            s += orderedItems.toString();
        }
        s += "bill: " + getBillAmount();
        return s;
    }
}

package budgetbobby;

import budgetbobby.DataStructures.LinkedList;
import budgetbobby.DataStructures.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Comparable<Bill> {

    private User user;
    private String restaurant;
    private LinkedList<FoodItem> orderedItems= new LinkedList<>();;

    private double billAmount;

    private final int billTime;

    public int getBillTime() {
        return billTime;
    }

    public Bill(User user, String restaurant, int time) {
        this.user = user;
        this.restaurant = restaurant;
        this.billAmount = 0;
        this.billTime = time;
        orderedItems = new LinkedList<>();
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

    public void sendConfirmationEmail() {
        try {
            // Command to execute Python script
            // Command to execute Python script
            String pythonScriptPath = "src/send_confirmation_email.py";

            List<String> billArray = new ArrayList<>();
            billArray.add(user.getEmail());
            billArray.add(user.getName());
            billArray.add(restaurant);
            billArray.add(String.valueOf(this.billAmount)); // Convert double to String

            // Add FoodItems from orderedItems to billArray
            Node<FoodItem> temp = orderedItems.getHead();
            StringBuilder foodItemsStringBuilder = new StringBuilder(); // To store all food items as a single string
            while (temp != null) {
                String str = temp.getData().getName()+"&nbsp;&nbsp;&nbsp;&nbsp;"+temp.getData().getPrice()+"<br>";
                foodItemsStringBuilder.append(str); 
                temp = temp.getNext();
            }

            billArray.add(foodItemsStringBuilder.toString()); // Add all food items as a single string

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

    @Override
    public int compareTo(Bill o) {
        return 0;
    }
}

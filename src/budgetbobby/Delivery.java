/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zaina
 */
public class Delivery {
    //graph storing areas and distances
    String restaurantArea;
    String restaurantName;
    String userArea;
    //priority queue of orders placed
    User user;
    
    
    //shortest distance calculation method
    
    // email sending and confirmation process
    public void sendDeliveredEmail(){
        try {
            // Command to execute Python script
            String pythonScriptPath = "src/send_delivered_email.py";

            List<String> deliveredArray = new ArrayList<>();
            deliveredArray.add(user.getEmail());
            deliveredArray.add(user.getName());
            deliveredArray.add(restaurantName); 
            
            // Command and arguments
            List<String> cmd = new ArrayList<>();
            cmd.add("C:/Python311/python.exe");
            cmd.add(pythonScriptPath);
            cmd.addAll(deliveredArray); // Add all elements from deliveredArray to the command list

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
    
}

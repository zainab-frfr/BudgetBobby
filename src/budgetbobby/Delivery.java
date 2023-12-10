/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package budgetbobby;

import budgetbobby.DataStructures.MinHeap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalTime;
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
    MinHeap<Bill> ofOrders =  new MinHeap<>();
    User user;
    
    
    //shortest distance calculation method

    private double convertToMinutes(String time) {
        double hours = 0; double mins = 0;
        if(time.equalsIgnoreCase("As soon as possible")){
            mins = 1;
        }
        else {
            String[] t = time.split(" ");
            System.out.println(Arrays.toString(t));
            System.out.println(t.length);
            double num = Double.parseDouble(t[0]);
            String min_hr = t[1];
            if (min_hr.equalsIgnoreCase("hour")) {
                hours = num;
                if (hours == 1.5) {
                    hours = 1;
                    mins = 30;
                } else if (hours == 2.5) {
                    hours = 2;
                    mins = 30;
                }
            } else if (min_hr.equalsIgnoreCase("minutes")) {
                mins = num;
            }
        }
        return hours * 60 + mins;
    }

    //adding orders into heap based on time
//    public void insertIntoHeap(Bill bill){
////      int timeDiff = Math.abs(currentTime-bill.getBillTime());
//        String[] t = bill.getBillTime().split(" ");
//        double time = Double.parseDouble(t[0]);
//        ofOrders.insert(bill,time);
//        System.out.println("being inserted");
//        bill.sendConfirmationEmail();
//    }

    public void insertIntoHeap(Bill bill) {
        double time = convertToMinutes(bill.getBillTime());
        ofOrders.insert(bill, time);
        System.out.println("Being inserted");
        bill.sendConfirmationEmail();
    }

    //removal from heap.
    // this method will also call sendDeliveredEmail method to send the email to the extracted orderBill





    public void removeFromHeap() {
        LocalTime currentTime = LocalTime.now();


        if (!ofOrders.isEmpty()) {
            // Get the time of the order at the top of the heap
            double orderTime = ofOrders.getMinPriority();

            // Compare with the current time
            if (orderTime <= currentTime.toSecondOfDay() / 60.0) {
                Bill removedBill = ofOrders.extractMin();
                System.out.println(removedBill);
                sendDeliveredEmail(removedBill.emailAddr(), removedBill.getUserName().getName());
            }
        }
    }



//    public void removeFromHeap(){
//        ofOrders.extractMin();
//        sendDeliveredEmail();
//     }
//

    // email sending and confirmation process
    public void sendDeliveredEmail(String emailId, String userNName){
        try {
            // Command to execute Python script
            String pythonScriptPath = "src/send_delivered_email.py";

            List<String> deliveredArray = new ArrayList<>();
            deliveredArray.add(emailId);
            deliveredArray.add(userNName);
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

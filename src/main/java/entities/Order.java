package entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.HashMap;
/**
 * An order.
 */

public class Order implements Serializable {
    private final String summary;
    private final String customerName; // name of the customer who ordered the food
    private final String customerNumber; // contact number of the customer who ordered the food
    private final String sellerName; // name of the seller who owns the food truck
    private final String sellerNumber; // contact number of the seller who owns the food truck
    private double rating;// customer can rate their order from 0 ~ 10. (if the customer didn't rate, rating
    // for the order will be a default -0.1)
    private String status; // the status can only be "in progress" or "order completed"
    private final LocalDateTime time;

    /**
     * Construct a new order object
     *
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     */
    public Order(String summary, String customerName,
                 String customerNumber, String sellerName, String sellerNumber) {
        this.summary = summary;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.sellerName = sellerName;
        this.sellerNumber = sellerNumber;
        this.rating = 0;
        this.status = "in progress";
        this.time = LocalDateTime.now();
    }

    /**
     * change the current status of this order to the next status.
     * E.g. when current status is "order completed", call changeOrderStatus again
     * will not change the status and "Change Failed" will be returned.
     *
     */
    public void changeOrderStatus(){
        if (this.status.equals("in progress")) {
            this.status = "order completed";
        }
    }


    /**
     * Update the customers rating for his/her order. Method returns true when given a reasonable rating, return false
     * otherwise
     *
     * @param rating should be a double < 10 & > 0
     * @return return true if rating updated successfully, return false otherwise
     */
    public boolean rateOrder(double rating) {
        if (0 <= rating & rating <= 10) {
            this.rating = rating;
            return true;
        }
        return false;
    }


    /**
     * A string representaton of order object (kinda like a receipt)
     *
     * @return A string
     */
    public String toString() {
        return "Order Time: " + this.getFormattedTime() + "\n" +
                "Customer Name: " + this.getCustomerName() + "\n" +
                "Customer Number: " + this.getCustomerNumber() + "\n" +
                "Seller Name: " + this.getSellerName() + "\n" +
                "Seller Number: " + this.getSellerNumber() + "\n" +
                this.summary + "\n" +
                "Status: " + this.getStatus() + "\n" +
                "Rating: " + this.getRating();
    }

    /**
     * A string represents the order time.
     *
     * @return A string
     */
    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return this.getTime().format(format);
    }


    public LocalDateTime getTime() {
        return this.time;
    }


    public String getCustomerName() {
        return this.customerName;
    }


    public String getCustomerNumber() {
        return this.customerNumber;
    }


    public String getSellerName() {
        return this.sellerName;
    }


    public String getStatus() {
        return this.status;
    }


    public String getSellerNumber() {
        return this.sellerNumber;
    }

    public double getRating(){
        return this.rating;
    }
}

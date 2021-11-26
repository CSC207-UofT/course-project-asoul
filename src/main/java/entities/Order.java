package entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

/**
 * An order
 */

public class Order implements Serializable {
    private final FoodTruck foodTruck; // private FoodTruck foodTruck;
    private final ArrayList<Food> foodList; // a list of foods ordered by the customers
    private final double totalPrice; // total price
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
     * @param foodTruck      the foodtruck that is responsible for this order
     * @param foodList       a list of foods ordered by the customers
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     */
    public Order(FoodTruck foodTruck, ArrayList<Food> foodList, String customerName,
                 String customerNumber, String sellerName, String sellerNumber) {
        this.foodTruck = foodTruck;
        this.foodList = foodList; // Aliasing problem??
        this.totalPrice = calculateTotalPrice();
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.sellerName = sellerName;
        this.sellerNumber = sellerNumber;
        this.rating = -0.1;
        this.status = "in progress";
        this.time = LocalDateTime.now();
    }


    /**
     * Calculate the total price of the food list.
     *
     * @return The total price.
     */
    public double calculateTotalPrice() {
        double price = 0.0;
        for (Food f : this.foodList) {
            price = price + f.getPrice();
        }
        return price;
    }

    /**
     * change the current status of this order to the next status.
     * E.g. when current status is "order completed", call changeOrderStatus again
     * will not change the status and "Change Failed" will be returned.
     *
     * @return Whether the status being changed successfully.
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
                "Food Truck: " + this.getFoodTruck().getTruckName() + "\n" +
                "Seller Name: " + this.getSellerName() + "\n" +
                "Seller Number: " + this.getSellerNumber() + "\n" +
                "Food List: " + this.getFoodList() + "\n" +
                "Total Price: $" + this.getTotalPrice() + "\n" +
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

    public FoodTruck getFoodTruck() {
        return this.foodTruck;
    }


    public Double getTotalPrice() {
        return this.totalPrice;
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

    public double getRatingRaw(){return this.rating; }

    public double getRating(){
        return this.rating;
    }


    public String getFoodList() {
        StringBuilder result = new StringBuilder();
        for (Food food : this.foodList) {
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n");
        }
        return result.toString().trim();
    }
}

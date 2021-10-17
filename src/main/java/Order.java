import java.util.ArrayList;

/**
 * An order
 */

public class Order {
    private final int ID; // a unique id for the order
    private final FoodTruck foodTruck; // private FoodTruck foodTruck;
    private final ArrayList<Food> foodList; // a list of foods ordered by the customers
    private final double totalPrice; // total price
    private final String customerName; // name of the customer who ordered the food
    private final String customerNumber; // contact number of the customer who ordered the food
    private final String sellerName; // name of the seller who owns the food truck
    private final String sellerNumber; // contact number of the seller who owns the food truck
    private double rating;// customer can rate their order from 0 ~ 10. (if the customer didn't rate, rating
    // for the order will be a default -0.1)
    private String status; // the status can only be "order received", "in progress" or "order completed"

    /**
     * Construct a new order object
     *
     * @param ID             a unique id for the order
     * @param foodTruck      the foodtruck that is responsible for this order
     * @param foodList       a list of foods ordered by the customers
     * @param totalPrice     total price
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     */
    public Order(int ID, FoodTruck foodTruck, ArrayList<Food> foodList, double totalPrice, String customerName,
                 String customerNumber, String sellerName, String sellerNumber) {
        this.ID = ID;
        this.foodTruck = foodTruck;
        this.foodList = foodList; // Aliasing problem??
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.customerNumber = customerNumber;
        this.sellerName = sellerName;
        this.sellerNumber = sellerNumber;
        this.rating = -0.1;
        this.status = "order received";
    }

    /**
     * change the current status of this order to the next status. Method will return true when the status successfully
     * modified, and return false otherwise. E.g. when current status is "order completed", call changeOrderStatus again
     * will not change the status and false will be returned.
     *
     * @return whether the current status has been modified
     */
    public boolean changeOrderStatus() {
        if (this.status.equals("order received")) {
            this.status = "in progress";
            return true;
        } else if (this.status.equals("in progress")) {
            this.status = "order completed";
            return true;
        } else {
            return false;
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
        if (0 < rating & rating < 10) {
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
        return this.ID + "\n" + this.customerName + " : " + this.customerNumber + "\n" + this.foodTruck.getTruckName() +
                " : " + this.sellerNumber + "\n" + this.getFoodList() + "\n" + "Total : $" + this.totalPrice + "\n" +
                this.status;
    }


    public int getID() {
        return this.ID;
    }


    public FoodTruck getFoodTruck() {
        return this.foodTruck;
    }


    public double getTotalPrice() {
        return this.totalPrice;
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


    public Double getRating() {
        return this.rating;
    }


    public String getFoodList() {
        StringBuilder result = new StringBuilder("");
        for (Food food : this.foodList) {
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n");
        }
        return result.toString().trim();
    }
}

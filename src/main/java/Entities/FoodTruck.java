package Entities;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Java class representation for Entities.FoodTruck instance
 */
public class FoodTruck implements Serializable {
    private final String truckName; // The name of the Entities.Food Truck
    private final String location; //The location of the Entities.Food Truck, can't be changed once set
    private final String serviceTimeStart; //Starting service time
    private final String serviceTimeEnd; //Ending service time
    private boolean status = false; //Whether the Entities.Food Truck is currently operating
    private final Seller seller; // The Entities.Seller who owns the Entities.Food Truck
    private final ArrayList<Order> orderHistory; // List of Entities.Order Histories of the Entities.Food Truck
    private double rating = 0.0; // Rating of the Entities.Food Truck
    // we are going to change it in the rating system. So it can't be final.
    private final ArrayList<Order> orderQueue; // List of Active Orders
    private final FoodMenu menu; //Menu of the Entities.Food Truck

    /**
     * Construct an instance of a Entities.FoodTruck
     *
     * @param truckName        The name of the Entities.Food Truck
     * @param location         The location of the Entities.Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Entities.Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd   Entities.Food Truck service end Time (eg. "17:30", "22:00")
     * @param seller           The corresponding Entities.Seller of this Entities.Food Truck
     * @param menu             The corresponding Menu of this Entities.Food Truck, which contains a list of foods.
     */
    public FoodTruck(String truckName, String location, String serviceTimeStart,
                     String serviceTimeEnd, Seller seller, FoodMenu menu) {
        this.truckName = truckName;
        this.location = location;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
        this.seller = seller;
        this.menu = menu;
        this.orderHistory = new ArrayList<>();
        this.orderQueue = new ArrayList<>();
    }

    /**
     * Change the status of the Entities.Food Truck
     *
     * @param status The status want to change to.
     */
    public void changeStatus(boolean status) {
        this.status = status;
    }

    /**
     * add food to menu if food object is not in menu. If the food is in menu, update the food with the new one.
     *
     * @param food The food want to add or update.
     * @return true if we add the food. false if we update the food.
     */
    public boolean addFoodToMenu(Food food) {
        return this.menu.addFood(food);
    }

    /**
     * remove food from menu if food object is in menu.
     *
     * @param food The food want to remove.
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */
    public boolean removeFoodFromMenu(Food food) {
        return this.menu.removeFood(food);
    }

    public void updateOrderHistory(Order order) {
        this.orderHistory.add(order);
        // Then if OrderHistory >= 10, calculate rating (To be implemented later TODO)
    }

    public void addOrderToQueue(Order order) {
        this.orderQueue.add(order);
    }

    // Remove the Entities.Order from orderQueue with the given id, return the removed Entities.Order
    public Order removeOrderWithID(int id) { // we are going to use the return value later.
        for (Order orders : this.orderQueue) {
            if (orders.getID() == id) {
                this.orderQueue.remove(orders);
                return orders;
            }
        }
        return null; // Add Error here, we should not have reached here if the given id is correct.
    }


    // public boolean renameTruck(String name){ } (TODO but wait for database)

    //A string description of the Entities.Food Truck (name, location, rating...)
    @Override
    public String toString() {
        if (this.status) {
            return this.truckName + "is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        } else {
            return this.truckName + "is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently not operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        }
    }

    // Display the food truck's corresponding menu items

    // public String displayMenu() {
    //     return "The menu of this food truck: " + " \n" + this.menu.toString();
    // }

    // TODO: Implement a rating system

    /**
     * Below are Getter methods for all instance variables
     */

    public String getTruckName() {
        return this.truckName;
    }

    public String getLocation() {
        return this.location;
    }

    // Return a String showing the service window of this Entities.Food Truck
    public String displayServiceTime() {
        return "The service time for this food truck is :"
                + this.serviceTimeStart + "-" + this.serviceTimeEnd + ".";
    }

    public boolean getStatus() {
        return this.status;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public double getRating() {
        return this.rating;
    }

    public FoodMenu getMenu() {
        return this.menu;
    }

    public ArrayList<Order> getOrderHistory() {
        return this.orderHistory;
    }

    public ArrayList<Order> getOrderQueue() {
        return this.orderQueue;
    }
}

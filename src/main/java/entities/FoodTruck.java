package entities;


import java.io.Serializable;
import java.util.HashSet;
/**
 * Java class representation for Entities.FoodTruck instance
 */
public class FoodTruck implements Serializable {
    private String truckName; // The name of the Entities.Food Truck
    private String location; //The location of the Entities.Food Truck, can't be changed once set
    private String serviceTimeStart; //Starting service time
    private String serviceTimeEnd; //Ending service time
    private boolean active = false; //Whether the Entities.Food Truck is currently operating
    private final String seller; // The Entities.Seller who owns the Entities.Food Truck
    private double rating; // Rating of the Entities.Food Truck
    // we are going to change it in the rating system. So it can't be final.
    private final HashSet<String> orderQueue; // List of Active Orders
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
                     String serviceTimeEnd, String seller, FoodMenu menu) {
        this.rating = 0.0;
        this.truckName = truckName;
        this.location = location;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
        this.seller = seller;
        this.menu = menu;
        this.orderQueue = new HashSet<>();
        this.active = false;
    }

    /**
     * Change the status of the Entities.Food Truck
     */
    public void changeStatus() {
        this.active = !this.active;
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

    public void addOrderToQueue(String orderID) {
        this.orderQueue.add(orderID);
    }

    // Remove the Entities.Order from orderQueue with the given id, return the removed Entities.Order
    public void removeOrderWithID(String id) { // we are going to use the return value later.
        this.orderQueue.remove(id);
    }


    //A string description of the Entities.Food Truck (name, location, rating...)
    @Override
    public String toString() {
        if (this.active) {
            return this.truckName + "is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        } else {
            return this.truckName + "is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently not operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        }
    }

    /**
     * Set food truck to active state.
     */
    public void activateTruck() {
        this.active = true;
    }

    /**
     *
     * @param newName the new name user wants to assign to this truck.
     */
    public void setTruckName(String newName) {
        this.truckName = newName;
    }

    public void setServiceTimeStart(String time){
        this.serviceTimeStart = time;
    }

    public void setServiceTimeEnd(String time){
        this.serviceTimeEnd = time;
    }

    public void setAddress(String address){
        this.location = address;
    }

    /**
     * Deactivate this food truck.
     */
    public void deactivateTruck() {
        this.active = false;
    }

    public String displayMenu() {
        return this.menu.toString();
    }

    /**
     * Update the rating of the food truck given a rating.
     * @param rating new rating of the food truck.
     */
    public void updateRating(double rating){this.rating = rating;}

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

    public boolean isActive() {
        return this.active;
    }

    public String getSeller() {
        return this.seller;
    }

    public double getRating() {
        return this.rating;
    }

    public FoodMenu getMenu() {
        return this.menu;
    }

    public HashSet<String> getOrderQueue() {
        return this.orderQueue;
    }
}

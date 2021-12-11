package entities;


import java.io.Serializable;
import java.util.HashMap;

import exceptions.*;

/**
 * Java class representation for Entities.FoodTruck instance
 */
public class FoodTruck implements Serializable {
    private String truckName; // The name of the Entities.Food Truck
    private String location; //The location of the Entities.Food Truck, can't be changed once set
    private String serviceTimeStart; //Starting service time
    private String serviceTimeEnd; //Ending service time
    private boolean active; //Whether the Entities.Food Truck is currently operating
    private final String seller; // The Entities.Seller who owns the Entities.Food Truck
    // we are going to change it in the rating system. So it can't be final.
    private final FoodMenu menu; //Menu of the Entities.Food Truck
    private final HashMap<String, Double> ratings;
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
        this.truckName = truckName;
        this.location = location;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
        this.seller = seller;
        this.menu = menu;
        this.active = false;
        this.ratings = new HashMap<>();
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
     *
     */
    public void addFoodToMenu(Food food, String id) throws CollidedFoodException, FoodIdCollisionException {
        this.menu.addFood(food, id);
    }

    /**
     * remove food from menu if food with specified id is in menu.
     *
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */

    public boolean removeFoodFromMenu(String id) {
        return this.menu.removeFood(id);
    }
    //A string description of the Entities.Food Truck (name, location, rating...)
    @Override
    public String toString() {
        if (this.active) {
            return this.truckName + " is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently operating." +
                    "\n" + "The rating of the food truck is " + getRating() + " out of " + getNumberOfRatings() +
                    " orders.";
        } else {
            return this.truckName + " is located at " + this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently not operating." +
                    "\n" + "The rating of the food truck is " + getRating() + " out of " + getNumberOfRatings() +
                    " orders.";
        }
    }

    public String getDetailedDescription() {
        StringBuilder sb = new StringBuilder();
        String f = String.format("Truck Name: %s\n" +
                "Service Time Start: %s\n" +
                "Service Time End: %s\n" +
                "Address: %s\n" +
                "Owner: %s\n" +
                "rating: %f\n", truckName,  serviceTimeStart, serviceTimeEnd, location, seller, getRating()
        );
        sb.append(f).append("\n\n");
        sb.append("-----------Menu-----------\n");
        sb.append(menu.toString()).append("\n");
        return sb.toString();
    }

    public double calculatePrice(HashMap<String, Integer> cart) throws UnknownFoodException {
        double total = 0;
        for(String id: cart.keySet()){
            int quantity = cart.get(id);
            double price = menu.getFoodPrice(id);
            total += (quantity * price);
        }
        return Math.round(total * 100.0) / 100.0;
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
     * Update the rating of the food truck given a rating.
     * @param rating new rating of the food truck.
     */
    public void updateRating(String id, double rating) throws IncorrectArgumentException {
        if(rating < 0 || rating > 10){
            throw new IncorrectArgumentException();
        }
        this.ratings.put(id, rating);
    }

    /**
     * Below are Getter methods for all instance variables
     */

    public String getTruckName() {
        return this.truckName;
    }


    // Return a String showing the service window of this Entities.Food Truck
    public String displayServiceTime() {
        return "The service time for this food truck is: "
                + this.serviceTimeStart + "-" + this.serviceTimeEnd + ".";
    }

    public boolean isActive() {
        return this.active;
    }

    public String getSeller() {
        return this.seller;
    }

    public int getNumberOfRatings(){
        return ratings.size();
    }

    public double getRating() {
        double s = 0;
        for(String id: ratings.keySet()){
            s += ratings.get(id);
        }
        s = s / ratings.size();
        return Math.round(s * 100.0) / 100.0;
    }


    public FoodMenu getMenu() {
        return this.menu;
    }
}

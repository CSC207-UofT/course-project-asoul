import java.util.ArrayList;
import java.util.Queue;

/**
 * Java class representation for FoodTruck instance
 */
public class FoodTruck {
    private String truckName; // The name of the Food Truck
    private final String location; //The location of the Food Truck, can't be changed once set
    private String serviceTimeStart; //Starting service time
    private String serviceTimeEnd; //Ending service time
    private boolean status = false; //Whether the Food Truck is currently operating
    private Seller seller; // The Seller who owns the Food Truck
    private ArrayList<Order> orderHistory = new ArrayList<>(); // List of Order Histories of the Food Truck
    private double rating = 0.0; // Rating of the Food Truck
    private ArrayList<Order> orderQueue = new ArrayList<>(); // List of Active Orders
    private FoodMenu menu; //Menu of the Food Truck

    /**
     * Construct an instance of a FoodTruck
     * @param truckName The name of the Food Truck
     * @param location The location of the Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd Food Truck service end Time (eg. "17:30", "22:00")
     * @param seller The corresponding Seller of this Food Truck
     * @param menu The corresponding Menu of this Food Truck, which contains a list of foods.
     */
    public FoodTruck(String truckName, String location, String serviceTimeStart,
                     String serviceTimeEnd, Seller seller, FoodMenu menu){
        this.truckName = truckName;
        this.location = location;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
        this.seller = seller;
        this.menu = menu;
    }

    /**
     * Construct an instance of a FoodTruck with no seller (the default value is null).
     * @param truckName The name of the Food Truck
     * @param location The location of the Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd Food Truck service end Time (eg. "17:30", "22:00")
     * @param menu The corresponding Menu of this Food Truck, which contains a list of foods.
     */
    public FoodTruck(String truckName, String location, String serviceTimeStart,
                     String serviceTimeEnd, FoodMenu menu){
        this.truckName = truckName;
        this.location = location;
        this.serviceTimeStart = serviceTimeStart;
        this.serviceTimeEnd = serviceTimeEnd;
        this.seller = null;
        this.menu = menu;
    }

    // Change the status of the Food Truck
    public void changeStatus(boolean status){
        this.status = status;
    }

    // Add the given Food to the Food Truck's menu
    // TODO Add remove Food from Menu
    public void updateMenu(Food food){
        this.menu.addFood(food);
    }

    public void updateOrderHistory(Order order){
        this.orderHistory.add(order);
        // Then if OrderHistory >= 10, calculate rating (To be implemented later TODO)
    }

    public void addOrderToQueue(Order order){
        this.orderQueue.add(order);
    }

    // Remove the Order from orderQueue with the given id, return the removed Order
    public Order removeOrderWithID(int id){
        for (Order orders : this.orderQueue) {
            if (orders.getID() == id) {
                Order temp_order = orders;
                int orderIDInList = this.orderQueue.indexOf(orders);
                this.orderQueue.remove(orderIDInList);
                return temp_order;
            }
        }
        return null; // Add Error here, we should not have reached here if the given id is correct.
    }



    // public boolean renameTruck(String name){ } (TODO but wait for database)

    //A string description of the Food Truck (name, location, rating...)
    @Override
    public String toString(){
        if (this.status) {
            return this.truckName + "is located at " +  this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        }
        else{
            return this.truckName + "is located at " +  this.location + "." + "\n" +
                    displayServiceTime() + "\n" + "The food truck is currently not operating." +
                    "\n" + "The rating of the food truck is " + this.rating + ".";
        }
    }

    // Display the food truck's corresponding menu items
    public String displayMenu(){
        return "The menu of this food truck: " + " \n" + this.menu.toString();
    }

    /**
     * TODO Will implement a rating system
     * public double updateRating(){}
     */

    /**
     * Below are Getter methods for all instance variables
     */

    public String getTruckName(){
        return this.truckName;
    }

    public String getLocation(){
        return this.location;
    }

    // Return a String showing the service window of this Food Truck
    public String displayServiceTime(){
        return "The service time for this food truck is :"
                + this.serviceTimeStart  + "-" + this.serviceTimeEnd + ".";
    }

    public boolean getStatus(){
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

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager {
    private final HashMap<String, FoodTruck> food_trucks;

    /**
     *
     * @param foodTrucks a map that maps a food truck's id to the FoodTruck object.
     *
     * Create a FoodTruckManager with the given FoodTrucks.
     */
    public FoodTruckManager(HashMap<String, FoodTruck> foodTrucks){
        this.food_trucks = foodTrucks;
    }

    /**
     *
     * Create a FoodTruckManager with no given FoodTrucks.
     */
    public FoodTruckManager() {
        this.food_trucks = new HashMap<String, FoodTruck>();
    }

    /**
     *
     * @param status the status that the food truck will turn to.
     * @param id the id of the food truck whose status is going to be changed.
     *
     * Change the status of the specific food truck. Return true if successfully changed.
     */
    public boolean changeStatus(String id, boolean status) {
        if (this.food_trucks.containsKey(id)) {
            this.food_trucks.get(id).changeStatus(status);
            return true;
        } else {
            return false;
        }
    }

    // TODO: updateMenu()
    // It's better to change the code in the FoodTruck.updateMenu().
    // Since when we update menu we don't only add food.

    /**
     *
     * @param id the id of the specific food truck.
     *
     * Get order history of the specific food truck. Return false if the food truck is not in the list.
     */
    public Object getOrderHistory(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getOrderHistory();
        } else {
            return false;
        }
    }

    // TODO: updateOrderHistory()
    // It's better to change the code in the FoodTruck.updateOrderHistory().
    // Since when we update OrderHistory we don't only add order.

    // TODO: renameTruck()

    /**
     *
     * @param id the id of the specific food truck.
     *
     * Get the menu of the specific food truck. Return false if the food truck is not in the list.
     */
    public Object getMenu(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getMenu();
        } else {
            return false;
        }
    }

    /**
     * Create a unique id (0~999) for the truck and add it to the list.
     *
     * @param truckName The name of the Food Truck
     * @param location The location of the Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd Food Truck service end Time (eg. "17:30", "22:00")
     * @param seller The corresponding Seller of this Food Truck
     * @param menu The corresponding Menu of this Food Truck, which contains a list of foods.
     *
     * @return true if the food truck being created successfully.
     */

    public boolean creatFoodTruck(String truckName, String location, String serviceTimeStart,
                                  String serviceTimeEnd, Seller seller, FoodMenu menu) {
        int id = ThreadLocalRandom.current().nextInt(0, 999 + 1);
        while (this.food_trucks.containsKey(Integer.toString(id))) {
            id = ThreadLocalRandom.current().nextInt(0, 999 + 1);
        }
        FoodTruck new_truck = new FoodTruck(truckName, location, serviceTimeStart, serviceTimeEnd, seller, menu);
        this.food_trucks.put(Integer.toString(id), new_truck);
        return true;
    }

    /**
     *
     * @param id the id of the specific food truck.
     *
     * Get the order queue of the specific food truck. Return false if the food truck is not in the list.
     */
    public Object getOrderQueue(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getOrderQueue();
        } else {
            return false;
        }
    }

    /**
     *
     * @param id the id of the specific food truck.
     *
     * Get the rating of the specific food truck. Return false if the food truck is not in the list.
     */
    public Object getRating(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getRating();
        } else {
            return false;
        }
    }

    /**
     *
     * Get all food trucks on the list.
     */
    public HashMap<String, FoodTruck> getFoodTrucks() {
        return this.food_trucks;
    }




}

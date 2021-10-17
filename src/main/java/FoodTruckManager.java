import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager {
    private final HashMap<String, FoodTruck> food_trucks; // Mapping FoodTrucks' id to the FoodTrucks.

    /**
     *
     * @param foodTrucks a current map that maps a food truck's id to the FoodTruck object.
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
     * Use truckName as the id for the truck and add it to the Hashmap.
     *
     * @param truckName The name of the Food Truck
     * @param location The location of the Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd Food Truck service end Time (eg. "17:30", "22:00")
     * @param seller The corresponding Seller of this Food Truck
     * @param menu The corresponding Menu of this Food Truck, which contains a list of foods.
     *
     * @return true if the food truck being created successfully. false if the food truck name exists.
     */

    public boolean creatFoodTruck(String truckName, String location, String serviceTimeStart,
                                  String serviceTimeEnd, Seller seller, FoodMenu menu) {
        if (this.food_trucks.containsKey(truckName)) {
            return false;
        }else {
            FoodTruck new_truck = new FoodTruck(truckName, location, serviceTimeStart, serviceTimeEnd, seller, menu);
            this.food_trucks.put(truckName, new_truck);
            return true;
        }
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

    /**
     * @param id the FoodTruck's id.
     * @return A map that from the FoodTruck's id to the FoodTruck's detailed information. If the truck doesn't
     *         exist, return an empty map.
     */
    public HashMap<String, String> getFoodTruckDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        if (this.food_trucks.containsKey(id)) {
            FoodTruck truck = this.food_trucks.get(id);
            information.put("id/truckName", truck.getTruckName());
            information.put("location", truck.getLocation());
            information.put("serviceTime", truck.displayServiceTime());
            information.put("status", String.valueOf(truck.getStatus()));
            information.put("seller", truck.getSeller().toString());
            information.put("rating", String.valueOf(truck.getRating()));
            information.put("menu", truck.getMenu().toString());
        } return information;
    }

    /**
     * @param id the FoodTruck's id.
     * @return A map that from the FoodTruck's id to the FoodTruck's briefly information. If the truck doesn't
     *         exist, return an empty map.
     */
    public HashMap<String, String> getFoodTruckDescription(String id) {
        HashMap<String, String> information = new HashMap<>();
        if (this.food_trucks.containsKey(id)) {
            FoodTruck truck = this.food_trucks.get(id);
            information.put(id, truck.toString());
        }
        return information;
    }

    /**
     * @return A set contains all current FoodTrucks' names.
     */
    public Set<String> getExistFoodTruckName() {
        return this.food_trucks.keySet();
    }
}

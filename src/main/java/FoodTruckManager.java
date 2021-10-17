import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * A FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager {

    private final HashMap<String, FoodTruck> food_trucks; // a Hashmap mapping FoodTrucks' id to the FoodTrucks.

    /**
     * @param foodTrucks a current map that maps a food truck's id to the FoodTruck object.
     *                   <p>
     *                   Create a FoodTruckManager with the given FoodTrucks.
     */
    public FoodTruckManager(HashMap<String, FoodTruck> foodTrucks) {
        this.food_trucks = foodTrucks;
    }

    /**
     * Create a FoodTruckManager with no given FoodTrucks.
     */
    public FoodTruckManager() {
        this.food_trucks = new HashMap<>();
    }

    /**
     * @param status the status that the food truck will turn to.
     * @param id     the id of the food truck whose status is going to be changed.
     *               <p>
     *               Change the status of the specific food truck.
     * @return Return true if successfully changed.
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
     * @param id the id of the specific food truck.
     * @return order history of the specific food truck.
     * false if the food truck is not in the list.
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
     * @param id the id of the specific food truck.
     * @return the menu of the specific food truck.
     * false if the food truck is not in the list.
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
     * @param truckName        The name of the Food Truck
     * @param location         The location of the Food Truck (eg. "207 St. George St")
     * @param serviceTimeStart Food Truck service start Time (eg. "9:30", "10:00")
     * @param serviceTimeEnd   Food Truck service end Time (eg. "17:30", "22:00")
     * @param selName          The corresponding Seller's account name of this Food Truck
     * @param sellers          The SellerManager of all current sellers.
     * @param menu             The corresponding Menu of this Food Truck, which contains a list of foods.
     * @return true if the food truck being created successfully.
     * false if the food truck name has been exists.
     */

    public boolean creatFoodTruck(String truckName, String location, String serviceTimeStart,
                                  String serviceTimeEnd, String selName, SellerManager sellers, FoodMenu menu) {
        if (this.food_trucks.containsKey(truckName)) {
            return false;
        } else {
            Seller sel = sellers.getSellerByAccName(selName);
            FoodTruck new_truck = new FoodTruck(truckName, location, serviceTimeStart, serviceTimeEnd, sel, menu);
            this.food_trucks.put(truckName, new_truck);
            sel.addFoodTruck(new_truck);
            return true;
        }
    }

    /**
     * Create a default food truck corresponding to the given seller.
     *
     * @param selName The corresponding Seller's account name of this Food Truck
     * @param sellers The SellerManager of all current sellers.
     * @return true if the food truck being created successfully.
     * false if the food truck name has been exists.
     */

    public boolean createDefaultFoodTruck(SellerManager sellers, String selName) {
        String truckName = "Blue_Truck";
        String location = "Bahen Center for Information Technology";
        String serviceTimeStart = "9:00";
        String serviceTimeEnd = "20:00";

        ArrayList<String> label1 = new ArrayList<>();
        label1.add("Fast food");
        Food food1 = new Food("Hamburger", 5.50, 1, label1, "Pretty delicious legend Hamburger!");
        ArrayList<String> label2 = new ArrayList<>();
        label2.add("Italian");
        Food food2 = new Food("Pizza", 10.50, 2, label2, "Pretty delicious and traditional Italian pizza!");
        ArrayList<String> label3 = new ArrayList<>();
        label3.add("Drinks");
        Food food3 = new Food("Coca Cola", 1.80, 3, label3, "Cool and relaxing!");
        ArrayList<String> label4 = new ArrayList<>();
        label4.add("Fast food");
        label4.add("Crisp");
        Food food4 = new Food("Poutine", 6.50, 4, label4, "Pretty delicious crisp Poutine!");

        FoodMenu menu = new FoodMenu();
        menu.addFood(food1);
        menu.addFood(food2);
        menu.addFood(food3);
        menu.addFood(food4);

        return creatFoodTruck(truckName, location, serviceTimeStart, serviceTimeEnd, selName, sellers, menu);
    }

    /**
     * @param id the id of the specific food truck.
     * @return Get the order queue of the specific food truck. Return false if the food truck doesn't exist.
     */
    public Object getOrderQueue(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getOrderQueue();
        } else {
            return false;
        }
    }

    /**
     * @param id the id of the specific food truck.
     *           <p>
     *           Get the rating of the specific food truck. Return false if the food truck is not in the list.
     */
    public Object getRating(String id) {
        if (this.food_trucks.containsKey(id)) {
            return this.food_trucks.get(id).getRating();
        } else {
            return false;
        }
    }

    /**
     * @return The seller of the FoodTruck.
     */
    public Seller getSeller(String id) {
        return getFoodTruckById(id).getSeller();
    }

    /**
     * @return The seller AccountName and PhoneNumber of the FoodTruck.
     */
    public HashMap<String, String> getSellerDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        information.put("accountName", getSeller(id).getAccountName());
        information.put("phoneNumber", getSeller(id).getPhoneNumber());
        return information;
    }

    /**
     * @return All food existing FoodTrucks.
     */
    public HashMap<String, FoodTruck> getFoodTrucks() {
        return this.food_trucks;
    }

    /**
     * @param id the FoodTruck's id.
     * @return A map that from the FoodTruck's id to the FoodTruck's detailed information. If the truck doesn't
     * exist, return an empty map.
     */
    public HashMap<String, String> getFoodTruckDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        if (this.food_trucks.containsKey(id)) {
            FoodTruck truck = getFoodTruckById(id);
            information.put("id/truckName", truck.getTruckName());
            information.put("location", truck.getLocation());
            information.put("serviceTime", truck.displayServiceTime());
            information.put("status", String.valueOf(truck.getStatus()));
            information.put("seller", truck.getSeller().toString());
            information.put("rating", String.valueOf(truck.getRating()));
            information.put("menu", truck.getMenu().toString());
        }
        return information;
    }

    /**
     * @return A map that from the FoodTruck's id to the FoodTruck's briefly information for all trucks.
     */
    public HashMap<String, String> getAllFoodTruckDescription() {
        HashMap<String, String> information = new HashMap<>();
        for (String id : getExistFoodTruckName())
            information.put(id, getFoodTruckById(id).toString());
        return information;
    }

    /**
     * @return A set contains all current FoodTrucks' names.
     */
    public Set<String> getExistFoodTruckName() {
        return this.food_trucks.keySet();
    }

    /**
     * @param id the FoodTruck's id
     * @return The food truck with the id.
     */
    public FoodTruck getFoodTruckById(String id) {
        return this.food_trucks.get(id);
    }
}

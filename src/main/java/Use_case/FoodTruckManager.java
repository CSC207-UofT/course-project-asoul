package Use_case;

import Entities.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A Use_case.FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager{

    protected static HashMap<String, FoodTruck> foodTrucks = new HashMap<>(); // a Hashmap mapping FoodTrucks' id to the FoodTrucks.

    /**
     * @param id     the id of the food truck whose status is going to be changed.
     *               <p>
     *               Change the status of the specific food truck.
     * @return Return true if successfully changed.
     */
    public static boolean changeStatus(String id) {
        if (foodTrucks.containsKey(id)) {
            foodTrucks.get(id).changeStatus();
            return true;
        } else {
            return false;
        }
    }

    /**
     * With the given foodtruck's name, add food to menu if food object is not in menu.
     * If the food is in menu, update the food with the new one.
     *
     * @param foodName  The name of the food want to add or update
     * @param price     The price of the food item, in double
     * @param label     The category that the food item belongs to
     *                  {"Appetizer", "Beverage", "Meal", "Dessert",
     *                  "Italian Entities.Food", "Fast Entities.Food", etc.}
     * @param truckName The name of the given truck
     * @return true if we add the food. false if we update the food.
     */
    public static boolean addFoodToMenu(String foodName, double price,
                                 ArrayList<String> label, String descriptions, String truckName) {
        ArrayList<Integer> ids = getFoodTruckById(truckName).getMenu().getFoodIds();
        int i = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        while (ids.contains(i)) {
            i = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        }
        Food food = new Food(foodName, price, i, label, descriptions);
        return getFoodTruckById(truckName).addFoodToMenu(food);
    }

    /**
     * With the given foodtruck's name, add food to menu if food object is not in menu.
     * If the food is in menu, update the food with the new one.
     *
     * @param food      The food want to add or update.
     * @param truckName The name of the given truck
     * @return true if we add the food. false if we update the food.
     */
    public static boolean addFoodToMenu(Food food, String truckName) { // we are going to use the return value later.
        return getFoodTruckById(truckName).addFoodToMenu(food);
    }

    /**
     * With the given foodtruck's name, remove food to menu if food object is in menu.
     *
     * @param foodName  The name of the food want to add or update
     * @param truckName The name of the given truck
     * @return true if we add the food. false if we update the food.
     */
    public static boolean removeFoodFromMenu(String foodName, String truckName) {
        ArrayList<String> label = new ArrayList<>();
        Food food = new Food(foodName, 0, 0, label, "");
        return getFoodTruckById(truckName).removeFoodFromMenu(food);
    }

    /**
     * With the given foodtruck's name, remove food to menu if food object is in menu.
     *
     * @param food      The food want to remove.
     * @param truckName The name of the given truck
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */
    public static boolean removeFoodFromMenu(Food food, String truckName) {
        return getFoodTruckById(truckName).removeFoodFromMenu(food);
    }
    /**
     * With the given foodtruck's name, remove food to menu if food object is in menu.
     *
     * @param ID      The food ID want to check.
     * @param truckName The name of the given truck
     * @return true if the food ID is in the given foodtruck's menu, false otherwise.
     */
    public boolean checkFoodFromFTMenu(int ID, String truckName){
        return getFoodTruckById(truckName).getMenu().isThereSameNameFoodId(ID);
    }

    // TODO: updateOrderHistory()
    // It's better to change the code in the Entities.FoodTruck.updateOrderHistory().
    // Since when we update OrderHistory we don't only add order.

    // TODO: renameTruck()

    /**
     * @param id the id of the specific food truck.
     * @return the menu of the specific food truck.
     * false if the food truck is not in the list.
     */
    public static Object getMenu(String id) {
        if (foodTrucks.containsKey(id)) {
            return foodTrucks.get(id).getMenu().toString();
        } else {
            return false;
        }
    }

    /**
     * @param sellerName the owner of the food truck
     * @return the created food truck
     */

    protected static FoodTruck createEmptyFoodTruck(String sellerName) { // Called when creating a new user
        if (foodTrucks.containsKey(sellerName)) {
            return null;
        } else {
            FoodMenu menu = new FoodMenu();

            FoodTruck new_truck = new FoodTruck(sellerName + "'s foodtruck", "", "", "", sellerName, menu);
            foodTrucks.put(sellerName, new_truck);
            return new_truck;
        }
    }


    /**
     * @param id the id of the specific food truck.
     * @return Get the order queue of the specific food truck. Return false if the food truck doesn't exist.
     */
    public static Object getOrderQueue(String id) {
        if (foodTrucks.containsKey(id)) {
            return foodTrucks.get(id).getOrderQueue();
        } else {
            return false;
        }
    }

    /**
     * @param id the id of the specific food truck.
     *           <p>
     *           Get the rating of the specific food truck. Return false if the food truck is not in the list.
     */
    public static Object getRating(String id) {
        if (foodTrucks.containsKey(id)) {
            return foodTrucks.get(id).getRating();
        } else {
            return false;
        }
    }

    /**
     * @return The seller of the Entities.FoodTruck.
     */
    public static User getSeller(String id) {
        return UserManager.userMap.get(getFoodTruckById(id).getSeller());
    }

    /**
     * @return The seller AccountName and PhoneNumber of the Entities.FoodTruck.
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
        return foodTrucks;
    }

    /**
     * @param id the Entities.FoodTruck's id.
     * @return A map that from the Entities.FoodTruck's id to the Entities.FoodTruck's detailed information. If the truck doesn't
     * exist, return an empty map.
     */
    public static HashMap<String, String> getFoodTruckDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        if (foodTrucks.containsKey(id)) {
            FoodTruck truck = getFoodTruckById(id);
            information.put("id/truckName", truck.getTruckName());
            information.put("location", truck.getLocation());
            information.put("serviceTime", truck.displayServiceTime());
            information.put("active", String.valueOf(truck.isActive()));
            information.put("seller", truck.getSeller().toString());
            information.put("rating", String.valueOf(truck.getRating()));
            information.put("menu", truck.getMenu().toString());
        }
        return information;
    }

    /**
     * @return A map that from the Entities.FoodTruck's id to the Entities.FoodTruck's briefly information for all trucks.
     */
    public static HashMap<String, String> getAllFoodTruckDescription() {
        HashMap<String, String> information = new HashMap<>();
        for (String id : getExistFoodTruckName())
            information.put(id, getFoodTruckById(id).toString());
        return information;
    }

    /**
     * @return A set contains all current FoodTrucks' names.
     */
    public static Set<String> getExistFoodTruckName() {
        return foodTrucks.keySet();
    }

    /**
     * @param id the Entities.FoodTruck's id
     * @return The food truck with the id.
     */
    public static FoodTruck getFoodTruckById(String id) {
        return foodTrucks.get(id);
    }

    @SuppressWarnings("unchecked")
    public static void constructFoodTruckDataBase() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/foodtruck info"));
            foodTrucks = (HashMap<String, FoodTruck>) ois.readObject();
            ois.close();
        }catch (EOFException e){
            // Do Nothing, no foodtruck has been registered yet
        }
    }

    public static void saveFoodTruckDataBase() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/foodtruck info"));
        oos.writeObject(foodTrucks);
        oos.flush();
        oos.close();
    }
}

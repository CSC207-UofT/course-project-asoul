package use_case;

import entities.*;
import exceptions.UnauthorizedAccessException;
import exceptions.UnknownFoodTruckException;
import exceptions.UnknownUserException;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A Use_case.FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager{
    protected static HashMap<String, FoodTruck> foodTrucks = new HashMap<>(); // a Hashmap mapping User's account name to the FoodTrucks.
    private static final Serializer ftSerializer = new Serializer();
    private static final Deserializer ftDeserializer = new Deserializer();

    /**
     *
     * @param id name of the food truck
     * @throws UnknownFoodTruckException if the truck doesn't exist.
     */
    public static void activateTruck(String id) throws UnknownFoodTruckException {
        if (!foodTrucks.containsKey(id)) {
            throw new UnknownFoodTruckException();
        }
        FoodTruck truck = foodTrucks.get(id);
        truck.activateTruck();
    }

    /**
     *
     * @param id name of the food truck
     * @throws UnknownFoodTruckException if the truck doesn't exist.
     */
    public static void deactivateTruck(String id) throws UnknownFoodTruckException {
        if (!foodTrucks.containsKey(id)) {
            throw new UnknownFoodTruckException();
        }
        FoodTruck truck = foodTrucks.get(id);
        truck.deactivateTruck();
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
     */
    public static void addFoodToMenu(Food food, String truckName) { // we are going to use the return value later.
        getFoodTruckById(truckName).addFoodToMenu(food);
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
    public static boolean checkFoodFromFTMenu(int ID, String truckName){
        return getFoodTruckById(truckName).getMenu().isThereSameNameFoodId(ID);
    }

    // TODO: renameTruck()
    public static boolean renameTruck(String newTruckName, String userAccountName, String accessKey) throws UnauthorizedAccessException {
        UserManager.accessCheck(userAccountName, accessKey);
        if (foodTrucks.containsKey(userAccountName)) {
            foodTrucks.get(userAccountName).changeTruckName(newTruckName);
            return true;
        } return false;
    }

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

    public static void createEmptyFoodTruck(String sellerName) { // Called when creating a new user
        if(!foodTrucks.containsKey(sellerName)){
            FoodMenu menu = new FoodMenu();

            String location = "";
            String serviceTimeStart = "";
            String serviceTimeEnd = "";

            FoodTruck new_truck = new FoodTruck(sellerName + "'s foodtruck", location, serviceTimeStart,
                    serviceTimeEnd, sellerName, menu);
            foodTrucks.put(sellerName, new_truck);
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
     * Food Truck Rating System
     * TODO, Implement the controller part
     */
    public static void calculateRating(String sellerName) throws UnknownUserException {
        if (foodTrucks.containsKey(sellerName)){
            HashSet<String> orders = UserManager.getSellOrderHistory(sellerName);
            double totalRating = 0.0;
            int count = 0;
            int i = 0;
            Iterator<String> iterator = orders.iterator();
            while(i < 100 && iterator.hasNext()){
                if (Objects.requireNonNull(OrderManager.getOrder(iterator.next())).getRating() >= 0 &&
                        Objects.requireNonNull(OrderManager.getOrder(iterator.next())).getRating() <= 10){
                    totalRating += Objects.requireNonNull(OrderManager.getOrder(iterator.next())).getRating();
                    count ++;
                }
                i++;
            }
            double rating = totalRating / count;
            foodTrucks.get(sellerName).updateRating(rating);
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

    public static void constructFoodTruckDataBase() throws IOException, ClassNotFoundException {
        ftDeserializer.deserialize();
    }

    public static void saveFoodTruckDataBase() throws IOException {
        ftSerializer.serialize("./data/foodtruck info", foodTrucks);
    }
}

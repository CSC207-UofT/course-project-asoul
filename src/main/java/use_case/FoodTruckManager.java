package use_case;

import entities.*;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A Use_case.FoodTruckManager that manages all the FoodTrucks.
 */

public class FoodTruckManager{
    protected static HashMap<String, FoodTruck> foodTrucks = new HashMap<>(); // a Hashmap mapping User's account name to the FoodTruck.
    private static final Serializer ftSerializer = new Serializer();
    private static final Deserializer ftDeserializer = new Deserializer();


    public static boolean existsTruck(String username){
        return foodTrucks.containsKey(username);
    }

    /**
     *
     * @param username username of a user
     * @throws UnknownFoodTruckException if the truck doesn't exist.
     */
    public static void activateTruck(String username) throws UnknownFoodTruckException {
        if (!foodTrucks.containsKey(username)) {
            throw new UnknownFoodTruckException();
        }
        FoodTruck truck = foodTrucks.get(username);
        truck.activateTruck();
    }

    /**
     *
     * @param username username of a user
     * @throws UnknownFoodTruckException if the truck doesn't exist.
     */
    public static void deactivateTruck(String username) throws UnknownFoodTruckException {
        if (!foodTrucks.containsKey(username)) {
            throw new UnknownFoodTruckException();
        }
        FoodTruck truck = foodTrucks.get(username);
        truck.deactivateTruck();
    }

    /**
     * With the given foodtruck's name, add food to menu if food object is not in menu.
     * If the food is in menu, update the food with the new one.
     *
     * @param foodName  The name of the food want to add or update
     * @param price     The price of the food item, in double
     * @param username The name of the given truck
     */
    public static void addFoodToMenu(String foodName, String price, String descriptions, String id, String username)
            throws CollidedFoodException, FoodIdCollisionException{
        double numPrice = Double.parseDouble(price);
        Food food = new Food(foodName, numPrice, descriptions);
        foodTrucks.get(username).addFoodToMenu(food, id);
    } //TODO

    /**
     * With the given foodtruck's name, add food to menu if food object is not in menu.
     * If the food is in menu, update the food with the new one.
     *
     * @param food      The food want to add or update.
     * @param username The name of the given truck
     */
    public static void addFoodToMenu(Food food, String id, String username) throws CollidedFoodException, FoodIdCollisionException{
        foodTrucks.get(username).addFoodToMenu(food, id);
    }

    /**
     * With the given foodtruck's name, remove food to menu if food object is in menu.
     *
     * @param foodName  The name of the food want to add or update
     * @param username The name of the given truck
     * @return true if we add the food. false if we update the food.
     */
    public static boolean removeFoodFromMenu(String foodName, String username) {
        return foodTrucks.get(username).removeFoodFromMenu(foodName);
    }

    /**
     * With the given foodtruck's name, remove food to menu if food object is in menu.
     *
     * @param food      The food want to remove.
     * @param username The name of the given truck
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */
    public static boolean removeFoodFromMenu(Food food, String username) {
        return foodTrucks.get(username).removeFoodFromMenu(food);
    }

    /**
     * With the given foodtruck's name, remove food from menu if food object is in menu.
     *
     * @param name      The food name ID want to check.
     * @param username The name of the given truck
     * @return true if the food ID is in the given foodtruck's menu, false otherwise.
     */
    public static boolean hasFood(String name, String username){
        return foodTrucks.get(username).getMenu().hasFood(name);
    }

    public static boolean hasFoodId(String id, String truckName){ // food id
        return foodTrucks.get(truckName).getMenu().hasFood(id);
    } //TODO

    public static void setTruckName(String newTruckName, String userAccountName, String accessKey) throws UnauthorizedAccessException {
        UserManager.accessCheck(userAccountName, accessKey);
        foodTrucks.get(userAccountName).setTruckName(newTruckName);
    }

    public static void setStartTime(String time, String username, String accessKey) throws UnauthorizedAccessException{
        UserManager.accessCheck(username, accessKey);
        foodTrucks.get(username).setServiceTimeStart(time);
    }

    public static void setEndTime(String time, String username, String accessKey) throws UnauthorizedAccessException{
        UserManager.accessCheck(username, accessKey);
        foodTrucks.get(username).setServiceTimeEnd(time);
    }

    public static void setAddress(String address, String username, String accessKey) throws UnauthorizedAccessException{
        UserManager.accessCheck(username, accessKey);
        foodTrucks.get(username).setAddress(address);
    }

    /**
     * @param id the id of the specific food truck.
     * @return the menu of the specific food truck.
     * false if the food truck is not in the list.
     */
    public static String getMenu(String id) throws UnknownFoodTruckException {
        if (foodTrucks.containsKey(id)) {
            return foodTrucks.get(id).getMenu().toString();
        } throw new UnknownFoodTruckException();
    }

    /**
     * @param sellerName the owner of the food truck
     * @return the created food truck
     */

    public static void createEmptyFoodTruck(String sellerName) throws CollidedFoodException, FoodIdCollisionException{ // Called when creating a new user
        if(!foodTrucks.containsKey(sellerName)){
            FoodMenu menu = new FoodMenu();

            String location = "Bahen Center for Information Technology";
            String serviceTimeStart = "9:00";
            String serviceTimeEnd = "20:00";

            FoodTruck new_truck = new FoodTruck(sellerName + "'s foodtruck", location, serviceTimeStart,
                    serviceTimeEnd, sellerName, menu);
            foodTrucks.put(sellerName, new_truck);

            Food food1 = new Food("Hamburger", 5.50, "Pretty delicious legend Hamburger!");
            Food food2 = new Food("Pizza", 10.50, "Pretty delicious and traditional Italian pizza!");
            Food food3 = new Food("Coca Cola", 1.80, "Cool and relaxing!");
            Food food4 = new Food("Poutine", 6.50, "Pretty delicious crisp Poutine!");

            addFoodToMenu(food1, "1", sellerName);
            addFoodToMenu(food2, "2", sellerName);
            addFoodToMenu(food3, "3", sellerName);
            addFoodToMenu(food4, "4", sellerName);
        }
    }


    /**
     * @param id the id of the specific food truck.
     * @return Get the order queue of the specific food truck. Return false if the food truck doesn't exist.
     */
    public static Object getOrderQueue(String id) throws UnknownFoodTruckException {
        if (foodTrucks.containsKey(id)) {
            return foodTrucks.get(id).getOrderQueue();
        } throw new UnknownFoodTruckException();

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
    public static double getRating(String id) {
        return foodTrucks.get(id).getRating();
    }

    /**
     * @return The seller of the Entities.FoodTruck.
     */
    public static User getSeller(String id) {
        return UserManager.userMap.get(foodTrucks.get(id).getSeller());
    }

    public static String getTruckName(String id){
        return foodTrucks.get(id).getTruckName();
    }

    /**
     * @return The seller AccountName and PhoneNumber of the Entities.FoodTruck.
     */
    public static HashMap<String, String> getSellerDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        information.put("accountName", getSeller(id).getAccountName());
        information.put("phoneNumber", getSeller(id).getPhoneNumber());
        return information;
    }

    public static String getFoodName(String username, String id) throws UnknownFoodTruckException, UnknownFoodException {
        if(!foodTrucks.containsKey(username)){
            throw new UnknownFoodTruckException();
        }
        return foodTrucks.get(username).getMenu().getFood(id).getFoodName();
    }

    public static double getFoodPrice(String username, String id) throws UnknownFoodException{
        if(!foodTrucks.containsKey(username)){
            throw new UnknownFoodException();
        }
        return foodTrucks.get(username).getMenu().getFoodPrice(id);
    }

    /**
     * @param id the Entities.FoodTruck's id.
     * @return A map that from the Entities.FoodTruck's id to the Entities.FoodTruck's detailed information. If the truck doesn't
     * exist, return an empty map.
     */
    public static String getFoodTruckDetail(String id) throws UnknownFoodTruckException{

        return information;
    }

    /**
     * @return A map that from the Entities.FoodTruck's id to the Entities.FoodTruck's briefly information for all trucks.
     */
    public static HashMap<String, String> getAllFoodTruckDescription() {
        HashMap<String, String> information = new HashMap<>();
        for (String id : foodTrucks.keySet()) {
            information.put(id, foodTrucks.get(id).toString());
        }
        return information;
    }

    /**
     * @return A map that from the FoodTruck's id to FoodTruck's brief description for all active trucks.
     */
    public static HashMap<String, String> getActiveFoodTruckDescription() {
        HashMap<String, String> information = new HashMap<>();
        for (String id : foodTrucks.keySet()) {
            FoodTruck ft = foodTrucks.get(id);
            if (ft.isActive()) {
                information.put(id, foodTrucks.get(id).toString());
            }
        }
        return information;
    }

    public static boolean isActive(String id, String accessKey) throws UnauthorizedAccessException{
        UserManager.accessCheck(id, accessKey);
        return foodTrucks.get(id).isActive();
    }

    @SuppressWarnings("unchecked")
    public static void constructFoodTruckDataBase() throws IOException, ClassNotFoundException {
        ftDeserializer.deserialize("./data/foodtruck info");
        HashMap<String, FoodTruck> m = (HashMap<String, FoodTruck>) ftDeserializer.getObject();
        if(m != null){
            foodTrucks = m;
        }
    }

    public static void saveFoodTruckDataBase() throws IOException {
        ftSerializer.serialize("./data/foodtruck info", foodTrucks);
    }
}

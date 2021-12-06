package use_case;

import entities.*;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.*;
import helper.RandomStringGenerator;

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
        return foodTrucks.get(truckName).getMenu().hasFoodId(id);
    }

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

    static void createEmptyFoodTruck(String sellerName) throws CollidedFoodException, FoodIdCollisionException, UnknownUserException{ // Called when creating a new user
        if(!foodTrucks.containsKey(sellerName)){
            FoodMenu menu = new FoodMenu();

            String location = "Bahen Center for Information Technology";
            String serviceTimeStart = "9:00";
            String serviceTimeEnd = "20:00";
            if(!UserManager.userMap.containsKey(sellerName)){
                throw new UnknownUserException();
            }
            String nickname = UserManager.userMap.get(sellerName).getNickname();
            FoodTruck new_truck = new FoodTruck(nickname + "'s foodtruck", location, serviceTimeStart,
                    serviceTimeEnd, nickname, menu);
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
     * Food Truck Rating System
     */
    static void updateRating(String truck, String orderID, double rate) throws UnknownFoodTruckException,
            UnknownOrderException, UnknownUserException, IncorrectArgumentException {
        FoodTruck ft = getFoodTruck(truck);
        User us = UserManager.getUser(truck);
        if(!us.getSellOrderHistory().contains(orderID)){
            throw new UnknownOrderException();
        }
        ft.updateRating(orderID, rate);
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

    static FoodTruck getFoodTruck(String id) throws UnknownFoodTruckException{
        try{
            return foodTrucks.get(id);
        }catch(NullPointerException e){
            throw new UnknownFoodTruckException();
        }
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

    public static double getFoodPrice(String username, String id) throws UnknownFoodException, UnknownFoodTruckException{
        if(!foodTrucks.containsKey(username)){
            throw new UnknownFoodTruckException();
        }
        return foodTrucks.get(username).getMenu().getFoodPrice(id);
    }

    public static void changeTruckStatus(String username, String accessKey) throws UnknownFoodTruckException, UnauthorizedAccessException{
        UserManager.accessCheck(username, accessKey);
        FoodTruck ft = getFoodTruck(username);
        ft.changeStatus();
    }

    /**
     * @param id the Entities.FoodTruck's id.
     * @return A map that from the Entities.FoodTruck's id to the Entities.FoodTruck's detailed information. If the truck doesn't
     * exist, return an empty map.
     */
    public static String getFoodTruckDetail(String id) throws UnknownFoodTruckException{
        if(!foodTrucks.containsKey(id)){
            throw new UnknownFoodTruckException();
        }
        return foodTrucks.get(id).getDetailedDescription();
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

    public static double calculatePrice(String truck, HashMap<String, Integer> cart) throws UnknownFoodTruckException,
            UnknownFoodException{
        if(!foodTrucks.containsKey(truck)){
            throw new UnknownFoodTruckException();
        }
        return foodTrucks.get(truck).calculatePrice(cart);
    }

    public static void placeOrder(String username, String accessKey, String seller, HashMap<String, Integer> cart)
            throws UnauthorizedAccessException, UnknownFoodTruckException, UnknownFoodException, UnknownUserException,
            InsufficientBalanceException, IncorrectArgumentException{
        UserManager.accessCheck(username, accessKey);
        if(!foodTrucks.containsKey(seller)){
            throw new UnknownFoodTruckException();
        }
        FoodTruck ft = foodTrucks.get(seller);
        double price = ft.calculatePrice(cart);
        UserManager.pay(username, seller, price, accessKey);
        OrderManager.createOrder(cart, username, seller);
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

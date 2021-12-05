package use_case;

import entities.*;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import helper.RandomStringGenerator;
/**
 * A Use_case.OrderManager that manages all the Orders.
 */

public class OrderManager {
    protected static HashMap<String, Order> orders = new HashMap<>(); //
    private static final Serializer oSerializer = new Serializer();
    private static final Deserializer oDeserializer = new Deserializer();

    /**
     * Create an order and add it to the list.
     * @param cart The items included in this order
     * @param customerName   name of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     */

    static void createOrder(HashMap<String, Integer> cart, String customerName, String sellerName)
            throws UnknownFoodException, UnknownFoodTruckException, UnknownUserException {
        RandomStringGenerator rs = new RandomStringGenerator();
        String id = rs.generateRandomString();
        while(orders.containsKey(id)){
            id = rs.generateRandomString();
        }
        StringBuilder sb = new StringBuilder();
        String bPhone = UserManager.getPhoneNumber(customerName);
        String sPhone = UserManager.getPhoneNumber(sellerName);
        for(String key: cart.keySet()){
            String foodName = FoodTruckManager.getFoodName(sellerName, key);
            String quantity = cart.get(key).toString();
            String price = (Math.round(FoodTruckManager.getFoodPrice(sellerName, key) * 100.0) / 100.0) + "";
            sb.append("Item: ").append(foodName).append("--------------").append(quantity).append(" X ").append(price
            ).append("\n");
        }
        sb.append("\n");
        Order new_order = new Order(sb.toString(), customerName, bPhone, sellerName, sPhone);
        orders.put(id, new_order);
        try {
            UserManager.updateOrderHistory(id);
        }catch (UnknownOrderException e){
            e.printStackTrace(); // This situation is impossible
        }
    }

    /**
     * Change the specific order's status.
     *
     * @param id the id of the specific order
     *
     * @return Whether the status being changed successfully.
     */
    public void changeOrderStatus(String username, String accessKey, String id) throws UnknownOrderException,
            UnauthorizedAccessException {
        UserManager.accessCheck(username, accessKey);
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        Order o = orders.get(id);
        if(!username.equals(o.getSellerName())){
            throw new UnauthorizedAccessException();
        }
        orders.get(id).changeOrderStatus();
    }

    /**
     * @param foods the list of foods' name
     * @param truck where these foods from
     * @return An ArrayList of Entities.Food from the given foods' names.
     */
    public static ArrayList<Food> getMenuFood(ArrayList<String> foods, FoodTruck truck) throws UnknownFoodException {
        FoodMenu menu = truck.getMenu();
        ArrayList<Food> wish_food = new ArrayList<>();
        for (String item : foods) {
            wish_food.add(menu.getFood(item));
        }
        return wish_food;
    }

    /**
     * @param foods     the list of foods' name
     * @param trucks    A FoodTruckManager stores all trucks.
     * @param truckName The truck name of the truck
     * @return An ArrayList of Entities.Food from the given foods' names.
     */
    public ArrayList<Food> getMenuFood(ArrayList<String> foods, FoodTruckManager trucks, String truckName) throws UnknownFoodException {
        FoodTruck truck = FoodTruckManager.foodTrucks.get(truckName);
        return getMenuFood(foods, truck);
    }

    /**
     * @param id the order's id.
     * @return A map that from the order's id to order's information.
     * @throws NullPointerException If the order with specified id does not exist
     */
    public HashMap<String, String> getOrderDetail(String id) throws UnknownOrderException{
        HashMap<String, String> information = new HashMap<>();
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        information.put(id, orders.get(id).toString());
        return information;
    }

    static Order getOrder(String id) throws UnknownOrderException{
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        return orders.get(id);
    }

    /**
     * Update the customers rating for an order. Method returns true when given a reasonable rating, return false
     * otherwise
     *
     * @param rating should be a double <= 10 & >= 0
     * @param id     the id of the order we want to rate
     * @return return true if rating updated successfully, return false otherwise
     * @throws NullPointerException If the order with specified id does not exist
     */
    static boolean rateOrder(double rating, String id) throws UnknownOrderException {
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        return orders.get(id).rateOrder(rating);
    }

    @SuppressWarnings("unchecked")
    public static void constructOrderDataBase() throws IOException, ClassNotFoundException {
        oDeserializer.deserialize("./data/order info");
        HashMap<String, Order> m = (HashMap<String, Order>) oDeserializer.getObject();
        if(m != null){
            orders = m;
        }
    }

    public static void saveOrderDataBase() throws IOException {
        oSerializer.serialize("./data/order info", orders);
    }
}

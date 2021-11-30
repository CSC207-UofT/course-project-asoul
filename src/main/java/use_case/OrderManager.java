package use_case;

import entities.Food;
import entities.FoodMenu;
import entities.FoodTruck;
import entities.Order;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * A Use_case.OrderManager that manages all the Orders.
 */

public class OrderManager {
    protected static HashMap<String, Order> orders = new HashMap<>(); //
    private static final Serializer oSerializer = new Serializer();
    private static final Deserializer oDeserializer = new Deserializer();

    /**
     * Create an order and add it to the list.
     *
     * @param foodTruck      the foodtruck that is responsible for this order
     * @param foodList       a list of foods ordered by the customers
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     * @return the id of new order
     */

    public static int createOrder(FoodTruck foodTruck, ArrayList<Food> foodList, String customerName,
                                  String customerNumber, String sellerName, String sellerNumber) {
        int id = orders.size() + 1;
        Order new_order = new Order(foodTruck, foodList, customerName,
                customerNumber, sellerName, sellerNumber);
        orders.put(Integer.toString(id), new_order);
        return id;
    }

    /**
     * Create an order and add it to the list.
     *
     * @param truckName      The truck name of the truck
     * @param foods          a list of foods' name ordered by the customers
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     * @return the id of new order
     */

    public int createOrder(String truckName, ArrayList<String> foods, String
            customerName,
                           String customerNumber, String sellerName, String sellerNumber) {
        FoodTruck foodTruck = FoodTruckManager.getFoodTruckById(truckName);
        ArrayList<Food> foodList = getMenuFood(foods, foodTruck);
        return createOrder(foodTruck, foodList, customerName, customerNumber, sellerName, sellerNumber);
    }

    /**
     * Change the specific order's status.
     *
     * @param id the id of the specific order
     *
     * @return Whether the status being changed successfully.
     */
    public void changeOrderStatus(String id) {
        Objects.requireNonNull(getOrder(id)).changeOrderStatus();
    }

    /**
     * @param foods the list of foods' name
     * @param truck where these foods from
     * @return An ArrayList of Entities.Food from the given foods' names.
     */
    public static ArrayList<Food> getMenuFood(ArrayList<String> foods, FoodTruck truck) {
        FoodMenu menu = truck.getMenu();
        ArrayList<Food> wish_food = new ArrayList<>();
        for (String item : foods) {
            wish_food.add(menu.createCopy(item));
        }
        return wish_food;
    }

    /**
     * @param foods     the list of foods' name
     * @param trucks    A FoodTruckManager stores all trucks.
     * @param truckName The truck name of the truck
     * @return An ArrayList of Entities.Food from the given foods' names.
     */
    public ArrayList<Food> getMenuFood(ArrayList<String> foods, FoodTruckManager trucks, String truckName) {
        FoodTruck truck = FoodTruckManager.getFoodTruckById(truckName);
        return getMenuFood(foods, truck);
    }

    /**
     * @param foods the list of foods' name
     * @param truck where these foods from
     * @return The total price of the given food in the truck
     */
    public double getTotalPrice(ArrayList<String> foods, FoodTruck truck) {
        FoodMenu menu = truck.getMenu();
        double total_price = 0;
        for (String item : foods) {
            total_price += menu.createCopy(item).getPrice();
        }
        return total_price;
    }

    /**
     * @param foods     the list of foods' name
     * @param truckName The truck name of the truck
     * @return The total price of the given food in the truck
     * @throws NullPointerException If the foodtruck with specified id does not exist
     */
    public double getTotalPrice(ArrayList<String> foods, String truckName) throws NullPointerException{
        FoodTruck truck = FoodTruckManager.getFoodTruckById(truckName);
        return getTotalPrice(foods, truck);
    }

    /**
     * @param id the order's id.
     * @return A map that from the order's id to order's information.
     * @throws NullPointerException If the order with specified id does not exist
     */
    public HashMap<String, String> getOrderDetail(String id) throws NullPointerException{
        HashMap<String, String> information = new HashMap<>();
        information.put(id, getOrder(id).toString());
        return information;
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
    public static boolean rateOrder(double rating, String id) throws NullPointerException{
        return getOrder(id).rateOrder(rating);
    }

    /**
     * @param id the Entities.Order's id.
     * @return The order with the given id. Return null is the id is not exist.
     */
    public static Order getOrder(String id) {
        return orders.get(id);
    }

    public static void constructOrderDataBase() throws IOException, ClassNotFoundException {
        oDeserializer.deserialize();
    }

    public static void saveOrderDataBase() throws IOException {
        oSerializer.serialize("./data/order info", orders);
    }
}

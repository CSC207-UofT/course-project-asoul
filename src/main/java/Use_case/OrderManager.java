package Use_case;

import Entities.Food;
import Entities.FoodMenu;
import Entities.FoodTruck;
import Entities.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Use_case.OrderManager that manages all the Orders.
 */

public class OrderManager {

    protected static HashMap<String, Order> orders = new HashMap<>(); // a Hashmap mapping FoodTrucks' id to the FoodTrucks.

    /**
     * Find the id of the newest order.
     *
     * @return The id of the newest order. If there is no existing order, return 0.
     */
    public static int getNewestOrder() {
        int id = 0;
        for (Order k : orders.values()) {
            if (k.getId() > id) {
                id = k.getId();
            }
        }
        return id;
    }

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
        int id = getNewestOrder() + 1;
        Order new_order = new Order(foodTruck, foodList, customerName,
                customerNumber, sellerName, sellerNumber, id);
        orders.put(Integer.toString(id), new_order);
        return id;
    }

    /**
     * Create an order and add it to the list.
     *
     * @param trucks         A FoodTruckManager stores all trucks.
     * @param truckName      The truck name of the truck
     * @param foods          a list of foods' name ordered by the customers
     * @param customerName   name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName     name of the seller who owns the food truck
     * @param sellerNumber   contact number of the seller who owns the food truck
     * @return the id of new order
     */

    public int createOrder(FoodTruckManager trucks, String truckName, ArrayList<String> foods, String
            customerName,
                           String customerNumber, String sellerName, String sellerNumber) {
        FoodTruck foodTruck = trucks.getFoodTruckById(truckName);
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
    public String changeOrderStatus(String id) throws Exception {
        return getOrder(id).changeOrderStatus();
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
        FoodTruck truck = trucks.getFoodTruckById(truckName);
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
     * @param trucks    A FoodTruckManager stores all trucks.
     * @param truckName The truck name of the truck
     * @return The total price of the given food in the truck
     */
    public double getTotalPrice(ArrayList<String> foods, FoodTruckManager trucks, String truckName) {
        FoodTruck truck = trucks.getFoodTruckById(truckName);
        return getTotalPrice(foods, truck);
    }

    /**
     * @param id the Entities.Order's id.
     * @return A map that from the Entities.Order's id to the Entities.Order's information. If the Entities.Order doesn't
     * exist, return an empty map.
     */
    public HashMap<String, String> getOrderDetail(String id) {
        HashMap<String, String> information = new HashMap<>();
        if (orders.containsKey(id)) {
            information.put(id, getOrder(id).toString());
        }
        return information;
    }

    /**
     * Update the customers rating for an order. Method returns true when given a reasonable rating, return false
     * otherwise
     *
     * @param rating should be a double <= 10 & >= 0
     * @param id     the id of the order we want to rate
     * @return return true if rating updated successfully, return false otherwise
     */
    public boolean rateOrder(double rating, String id) {
        return getOrder(id).rateOrder(rating);
    }

    /**
     * @param id the Entities.Order's id.
     * @return The order with the given id. Return null is the id is not exist.
     */
    public Order getOrder(String id) {
        if (orders.containsKey(id)) {
            return orders.get(id);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static void constructOrderDataBase() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/order info"));
            orders = (HashMap<String, Order>) ois.readObject();
            ois.close();
        } catch (EOFException e) {
            // Do nothing, no order has been created
        }
    }

    public static void saveOrderDataBase() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/order info.txt"));
        oos.writeObject(orders);
        oos.flush();
        oos.close();
    }
}

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A OrderManager that manages all the Orders.
 */

public class OrderManager {

    private final HashMap<String, Order> orders; // a Hashmap mapping FoodTrucks' id to the FoodTrucks.

    /**
     *
     * @param current_orders a map that maps s' id to the Order objects.
     *
     * Create a OrderManager with the given Orders.
     */
    public OrderManager(HashMap<String, Order> current_orders){
        this.orders = current_orders;
    }

    /**
     *
     * Create a OrderManager with no given FoodTrucks.
     */
    public OrderManager() {
        this.orders = new HashMap<String, Order>();
    }

    /**
     * Create a unique id (0~999999) for the order and add it to the list.
     *
     * @param foodTruck the foodtruck that is responsible for this order
     * @param foodList a list of foods ordered by the customers
     * @param customerName name of the customer who ordered the food
     * @param customerNumber contact number of the customer who ordered the food
     * @param sellerName name of the seller who owns the food truck
     * @param sellerNumber contact number of the seller who owns the food truck
     *
     * @return true if the order being created successfully.
     */

    public boolean creatOrder(FoodTruck foodTruck, ArrayList<Food> foodList, String customerName,
                              String customerNumber, String sellerName, String sellerNumber) {
        int id = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
        while (this.orders.containsKey(Integer.toString(id))) {
            id = ThreadLocalRandom.current().nextInt(0, 999999 + 1);
        }
        Order new_order = new Order(id, foodTruck, foodList, customerName,
                customerNumber, sellerName, sellerNumber);
        this.orders.put(Integer.toString(id), new_order);
        return true;
    }

    // TODO: deleteOrder()
    // Need a new attribute of Order which represent order time.

    // TODO: getOrders()
    // Complete this method with some restriction.

    // TODO:
    // **
    // * Cancel the order only if the status of the order is received
    // *
    // * @param id the id of the specific order
    // *
    // * @return true if the order being created successfully.
    // */
    // public boolean cancelOrder(String id) {}

    /**
     * Change the specific order's status.
     *
     * @param id the id of the specific order
     *
     * @return true if the order status being changed successfully.
     */
    public boolean changeOrderStatus(String id) {
        return this.orders.get(id).changeOrderStatus();
    }

    /**
     *
     * @param foods the list of foods' name
     * @param truck where these foods from
     *
     * @return An ArrayList of Food from the given foods' names.
     */
    public ArrayList<Food> getMenuFood(ArrayList<String> foods, FoodTruck truck) {
        FoodMenu menu = truck.getMenu();
        ArrayList<Food> wish_food = new ArrayList<>();
        for (String item : foods) {
            wish_food.add(menu.createCopy(item));
        }
        return wish_food;
    }
}

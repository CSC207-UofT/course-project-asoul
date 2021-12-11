package use_case;

import entities.*;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.*;
import java.util.HashMap;
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
            ).append("$\n");
        }
        String totalCost = FoodTruckManager.calculatePrice(sellerName, cart) + "";
        sb.append("Total: ").append(totalCost).append("\n");
        String bNick = UserManager.getNickname(customerName);
        String sNick = UserManager.getNickname(sellerName);
        String truckName = FoodTruckManager.getTruckName(sellerName);
        Order new_order = new Order(sb.toString(), customerName, bNick, bPhone, sellerName, sNick, sPhone, truckName);
        orders.put(id, new_order);
        try {
            UserManager.updateOrderHistory(id);
        }catch (UnknownOrderException e){
            e.printStackTrace(); // This situation is impossible
        }
    }



    /**
     * @param id the order's id.
     * @return A String that represents the order's information
     * @throws UnknownOrderException If the order with specified id does not exist
     */
    public static String getOrderDetail(String id) throws UnknownOrderException{
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        return orders.get(id).toString();
    }

    public static String getOrderDescription(String id) throws UnknownOrderException{
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        return orders.get(id).getDescription();
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
     *
     * @throws NullPointerException If the order with specified id does not exist
     */
    public static void rateOrder(String username, String accessKey, double rating, String id) throws UnknownOrderException,
            UnauthorizedAccessException, IncorrectArgumentException{
        UserManager.accessCheck(username, accessKey);
        if(!orders.containsKey(id)){
            throw new UnknownOrderException();
        }
        Order o = orders.get(id);
        if(!o.getBuyer().equals(username)){
            throw new UnauthorizedAccessException();
        }
        o.rateOrder(rating);
        try {
            FoodTruckManager.updateRating(o.getSeller(), id, rating);
        }catch (UnknownUserException | UnknownFoodTruckException e){
            // Do nothing, this situation is impossible
        }
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

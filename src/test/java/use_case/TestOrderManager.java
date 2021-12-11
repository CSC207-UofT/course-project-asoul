package use_case;

import entities.Order;
import entities.User;
import exceptions.*;

import java.util.HashMap;
import java.util.Map;

public class TestOrderManager {
    User user;
    String key;

    @org.junit.Before
    public void Setup() throws IncorrectCredentialsException {
        UserManager.createUser("a", "123", "mike", "12345");
        key = UserManager.login("a", "123");
    }

    @org.junit.Test
    public void testCreateOrder()throws UnknownFoodException, UnknownFoodTruckException, UnknownUserException {
        int size = OrderManager.orders.size();
        OrderManager.createOrder(new HashMap<>(), "a", "a");
        assert OrderManager.orders.size() == size + 1;
    }

    @org.junit.Test
    public void testGetOrder() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "a", "a");
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.getTime() == OrderManager.getOrder(key).getTime();
    }

    @org.junit.Test
    public void testGetOrderDetail() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "a", "a");
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.toString().equals(OrderManager.getOrderDetail(key));
    }

    @org.junit.Test
    public void testGetDescription() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "a", "a");
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.getDescription().equals(OrderManager.getOrderDescription(key));
    }
}

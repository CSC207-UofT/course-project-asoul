package use_case;

import entities.Order;
import exceptions.UnknownFoodException;
import exceptions.UnknownFoodTruckException;
import exceptions.UnknownOrderException;
import exceptions.UnknownUserException;

import java.util.HashMap;
import java.util.Map;

public class TestOrderManager {

    @org.junit.Test
    public void testCreateOrder()throws UnknownFoodException, UnknownFoodTruckException, UnknownUserException {
        OrderManager.createOrder(new HashMap<>(), "customer", "seller");
        assert OrderManager.orders.size() == 1;
    }

    @org.junit.Test
    public void testGetOrder() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "customer", "seller");
        assert OrderManager.orders.size() == 1;
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.getTime() == OrderManager.getOrder(key).getTime();
    }

    @org.junit.Test
    public void testGetOrderDetail() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "customer", "seller");
        assert OrderManager.orders.size() == 1;
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.toString().equals(OrderManager.getOrderDetail(key));
    }

    @org.junit.Test
    public void testGetDescription() throws UnknownOrderException, UnknownFoodTruckException, UnknownUserException, UnknownFoodException {
        OrderManager.createOrder(new HashMap<>(), "customer", "seller");
        assert OrderManager.orders.size() == 1;
        Map.Entry<String,Order> entry = OrderManager.orders.entrySet().iterator().next();
        String key = entry.getKey();
        Order value = entry.getValue();
        assert value.getDescription().equals(OrderManager.getOrderDescription(key));
    }
}
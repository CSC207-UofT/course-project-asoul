package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class TestFoodTruck {
    FoodTruck foodTruck;

    @org.junit.Before
    public void Setup() throws Exception {
        foodTruck = new FoodTruck("Truck1", "207 St. George St",
                "9:30", "17:00",
                new Seller("acc1", "1234", "ASOUL", "11223344"),
                new FoodMenu(new ArrayList<>()));
    }

    @org.junit.Test
    public void changeStatusTest() {
        foodTruck.changeStatus(true);
        assert foodTruck.getStatus();
    }

    @org.junit.Test
    public void addFoodToMenuTest() {
        Food burger = new Food("Burger", 9.99, 1, new ArrayList<>(
                Arrays.asList("Fast Entities.Food", "Western")), "A standard Beef Burger.");
        assert foodTruck.addFoodToMenu(burger);
        ArrayList<Food> foodList1 = new ArrayList<>();
        foodList1.add(burger);
        assert foodTruck.getMenu().getFoodList().equals(foodList1);
    }

    @org.junit.Test
    public void removeFoodFromMenuSuccessTest() {
        Food burger = new Food("Burger", 9.99, 1, new ArrayList<>(
                Arrays.asList("Fast Entities.Food", "Western")), "A standard Beef Burger.");
        foodTruck.addFoodToMenu(burger);
        assert foodTruck.removeFoodFromMenu(burger);
        ArrayList<Food> foodList1 = new ArrayList<>();
        assert foodTruck.getMenu().getFoodList().isEmpty();
    }

    @org.junit.Test
    public void removeFoodFromMenuDefeatTest() {
        Food burger = new Food("Burger", 9.99, 1, new ArrayList<>(
                Arrays.asList("Fast Entities.Food", "Western")), "A standard Beef Burger.");
        assert !foodTruck.removeFoodFromMenu(burger);
        ArrayList<Food> foodList1 = new ArrayList<>();
        assert foodTruck.getMenu().getFoodList().isEmpty();
    }

    @org.junit.Test
    public void updateOrderHistoryTest() {

        Order order = new Order(1, foodTruck, new ArrayList<>(),
                "Bob", "1111", "Ava", "2222");
        foodTruck.updateOrderHistory(order);
        assert foodTruck.getOrderHistory().contains(order);
    }

    @org.junit.Test
    public void addOrderToQueueTest() {

        Order order = new Order(1, foodTruck, new ArrayList<>(),
                "Bob", "1111", "Ava", "2222");
        foodTruck.addOrderToQueue(order);
        assert foodTruck.getOrderQueue().contains(order);
    }

    @org.junit.Test
    public void removeOrderWithIDTest() {

        Order order = new Order(1, foodTruck, new ArrayList<>(),
                "Bob", "1111", "Ava", "2222");
        foodTruck.removeOrderWithID(1);
        assert !foodTruck.getOrderQueue().contains(order) && foodTruck.getOrderQueue().size() == 0;
    }
}
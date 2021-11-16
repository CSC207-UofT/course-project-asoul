package Entities;

import java.util.ArrayList;
import java.util.Arrays;

public class TestFoodTruck {
    FoodTruck foodTruck;

    @org.junit.Before
    public void Setup() {
        foodTruck = new FoodTruck("Truck1", "207 St. George St",
                "9:30", "17:00",
                "acc1",
                new FoodMenu(new ArrayList<>()));
    }

    @org.junit.Test
    public void changeStatusTest() {
        foodTruck.changeStatus();
        assert foodTruck.isActive();
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
    public void addOrderToQueueTest() {
        Order order = new Order(foodTruck, new ArrayList<>(),
                "Bob", "1111", "Ava", "2222", 1);
        foodTruck.addOrderToQueue(order);
        assert foodTruck.getOrderQueue().contains(order.getId());
    }

    @org.junit.Test
    public void removeOrderWithIDTest() {
        Order order = new Order(foodTruck, new ArrayList<>(),
                "Bob", "1111", "Ava", "2222", 1);
        foodTruck.removeOrderWithID(1);
        assert !foodTruck.getOrderQueue().contains(order.getId()) && foodTruck.getOrderQueue().size() == 0;
    }
}

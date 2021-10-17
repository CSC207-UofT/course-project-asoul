package Entities;

import java.util.ArrayList;

public class TestCustomer {
    Customer customer;
    Order order;

    @org.junit.Before
    public void Setup() throws Exception {
        customer = new Customer("Yx", "yxyyds", "yuanxiao", "110108");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(pizza);
        FoodMenu menu = new FoodMenu(foodList);

        Seller seller = new Seller("David", "BigBoss", "D.", "6478863531");

        FoodTruck foodTruck = new FoodTruck("Ideal Catering", "Bahen", "8:00",
                "18:00", seller, menu);
        order = new Order(1, foodTruck, foodList, "Paul", "4169990000",
                "David", "6478863531");
    }

    @org.junit.Test
    public void addOrderTest() {
        customer.storeOrder(order);
        ArrayList<Order> anOrder = new ArrayList<>();
        anOrder.add(order);
        assert customer.getOrderHistory().equals(anOrder);
    }

    @org.junit.Test
    public void removeOrderTest() {
        customer.storeOrder(order);
        customer.removeOrder(order);
        ArrayList<Order> anOrder = new ArrayList<>();
        assert customer.getOrderHistory().equals(anOrder);
    }

}

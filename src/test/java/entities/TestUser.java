package entities;

import java.util.ArrayList;

public class TestUser {
    User user;
    Order order;

    @org.junit.Before
    public void Setup() {
        user = new User("Yx", "yxyyds", "yuanxiao", "110108");
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(pizza);
        FoodMenu menu = new FoodMenu(foodList);

        FoodTruck foodTruck = new FoodTruck("Ideal Catering", "Bahen", "8:00",
                "18:00", "David", menu);
        order = new Order(foodTruck, foodList, "Paul", "4169990000",
                "David", "6478863531", 1);
    }

    @org.junit.Test
    public void addOrderTest() {
        user.storeOrder(Integer.toString(order.getId()));
        ArrayList<String> anOrder = new ArrayList<>();
        anOrder.add("1");
        assert user.getOrderHistory().equals(anOrder);
    }


    @org.junit.Test
    public void loginTest() {
        boolean x = user.login("yxyyds");
        assert x;
    }

    @org.junit.Test
    public void logoutTest() {
        user.login("yxyyds");
        boolean y = user.logout();
        assert y;
    }

    @org.junit.Test
    public void addMoneyTest() {
        user.addMoney(1);
        assert user.getAccountBalance() == 1;
    }

    @org.junit.Test
    public void withdrawMoneyTest() {
        user.addMoney(1);
        user.withdrawMoney(1);
        assert user.getAccountBalance() == 0;
    }

    @org.junit.Test
    public void checkBalanceTest() {
        user.addMoney(1);
        assert user.checkBalance() == 1;
    }

}

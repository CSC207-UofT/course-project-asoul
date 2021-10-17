import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestSellerManager {
    SellerManager manager;

    @org.junit.Before
    public void setUp() {

        manager = new SellerManager();
    }


    @org.junit.Test
    public void getSellerTest() {
        manager.createUser("Seller", "ABC", "123", "nick",
                "12345");
        Seller seller = new Seller("ABC", "123", "nick",
                "12345");
        int actual = manager.getSellers().size();
        assert actual == 1;

    }

    @org.junit.Test
    public void addFoodTruckTest() {
        manager.createUser("Seller", "ABC", "123", "nick",
                "12345");
        Seller seller = (Seller) manager.returnUser("ABC");
        FoodMenu menu = new FoodMenu(new ArrayList<>());
        FoodTruck truck = new FoodTruck("Bluc", "TORONTO", "10:00",
                "12:00", seller, menu);
        manager.addFoodTruck("ABC", truck);

        int actual = seller.getFoodTruck().size();
        assert actual == 1;

    }
}

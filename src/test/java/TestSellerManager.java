import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TestSellerManager {



    @org.junit.Test
    public void getSellerTest() {
        SellerManager manager = new SellerManager();
        manager.createUser("Seller", "ABC", "123", "nick",
                "12345");
        Seller seller = new Seller("ABC", "123", "nick",
                "12345");
        int actual = manager.getSellers().size();
        assert actual == 1;
        manager.deleteUser("ABC");

    }

    @org.junit.Test
    public void addFoodTruckTest() {
        SellerManager manager = new SellerManager();
        manager.createUser("Seller", "ABC", "123", "nick",
                "12345");
        Seller seller = (Seller) manager.returnUser("ABC");
        FoodMenu menu = new FoodMenu(new ArrayList<>());
        FoodTruck truck = new FoodTruck("Bluc", "TORONTO", "10:00",
                "12:00", seller, menu);
        manager.addFoodTruck("ABC", truck);

        int actual = seller.getFoodTruck().size();
        assert actual == 1;
        manager.deleteUser("ABC");
    }
}

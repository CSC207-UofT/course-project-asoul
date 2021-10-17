import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestSeller {
    Seller seller;
    FoodTruck foodtruck;

    @org.junit.Before
    public void Setup() throws Exception{
        seller = new Seller("Yx", "yxyyds", "yuanxiao", "110108");
        foodtruck = new FoodTruck("Truck1", "207 St. George St",
                "9:30", "17:00",
                new Seller("acc1", "1234", "ASOUL", "11223344"),
                new FoodMenu(new ArrayList<>()));
    }

    @org.junit.Test
    public void addFoodTruckTest(){
        seller.addFoodTruck(foodtruck);
        ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
        foodTruckList.add(foodtruck);
        assert seller.getFoodTruck().equals(foodTruckList);
    }

    @org.junit.Test
    public void removeFoodTruckTest(){
        seller.addFoodTruck(foodtruck);
        ArrayList<FoodTruck> foodTruckList = new ArrayList<FoodTruck>();
        seller.removeFoodTruck(foodtruck);
        assert seller.getFoodTruck().equals(foodTruckList);
    }

    @org.junit.Test
    public void loginTest(){
        assert seller.login("yxyyds");
    }

    @org.junit.Test
    public void logoutTest(){
        seller.login("yxyyds");
        assert seller.logout();
    }

    @org.junit.Test
    public void addMoneyTest(){
        seller.addMoney(1);
        assert seller.getAccountBalance() == 1;
    }

    @org.junit.Test
    public void withdrawMoneyTest(){
        seller.addMoney(1);
        seller.withdrawMoney(1);
        assert seller.getAccountBalance() == 0;
    }

    @org.junit.Test
    public void checkBalanceTest(){
        seller.addMoney(1);
        assert seller.checkBalance() == 1;
    }





}

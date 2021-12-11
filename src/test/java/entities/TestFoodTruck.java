package entities;

import exceptions.CollidedFoodException;
import exceptions.FoodIdCollisionException;
import exceptions.IncorrectArgumentException;
import exceptions.UnknownFoodException;

import java.util.HashMap;

public class TestFoodTruck {
    FoodTruck foodTruck;

    @org.junit.Before
    public void Setup() {
        foodTruck = new FoodTruck("Truck1", "207 St. George St",
                "9:30", "17:00",
                "acc1",
                new FoodMenu());
    }

    @org.junit.Test
    public void changeStatusTest() {
        foodTruck.changeStatus();
        assert foodTruck.isActive();
    }

    @org.junit.Test
    public void addFoodToMenuTest() throws CollidedFoodException, FoodIdCollisionException, UnknownFoodException {
        Food burger = new Food("Burger", 9.99, "A standard Beef Burger.");
        foodTruck.addFoodToMenu(burger, "1");
        assert foodTruck.getMenu().hasFoodId("1");
        assert foodTruck.getMenu().getFood("1").compareTo(burger) == 0;
    }

    @org.junit.Test
    public void displayServiceTimeTest() {
        String serviceTime = "The service time for this food truck is: "
                + "9:30" + "-" + "17:00" + ".";
        assert foodTruck.displayServiceTime().equals(serviceTime);
    }

    @org.junit.Test
    public void getRatingTest() throws IncorrectArgumentException {
        foodTruck.updateRating("1", 8.0);
        foodTruck.updateRating("1", 6.0);
        foodTruck.updateRating("1", 7.0);
        assert foodTruck.getRating() == 7.0;
    }

    @org.junit.Test
    public void getNumberOfRatingsTest() {
        assert foodTruck.getNumberOfRatings() == 3;
    }

    @org.junit.Test
    public void toStringTest() {
        String result = "Truck1" + " is located at " + "207 St. George St" + "." + "\n" +
                foodTruck.displayServiceTime() + "\n" + "The food truck is currently operating." +
                "\n" + "The rating of the food truck is " + foodTruck.getRating() + " out of " + foodTruck.getNumberOfRatings() +
                " orders.";
        assert foodTruck.toString().equals(result);
    }

    @org.junit.Test
    public void calculatePriceTest() throws UnknownFoodException {
        HashMap<String, Integer> cart = new HashMap<>();
        cart.put("1", 3);
        assert foodTruck.calculatePrice(cart) == 9.99 * 3;
    }

    @org.junit.Test
    public void getMenuTest() throws UnknownFoodException {
        Food burger = new Food("Burger", 9.99, "A standard Beef Burger.");
        FoodMenu menu = foodTruck.getMenu();

        assert menu.hasFoodId("1");
        assert !menu.hasFoodId("2");
        assert menu.getFood("1").compareTo(burger) == 0;
    }

    @org.junit.Test
    public void removeFoodFromMenuDefeatTest() throws UnknownFoodException {
        Food burger = new Food("Burger", 9.99, "A standard Beef Burger.");
        foodTruck.removeFoodFromMenu("2");
        assert foodTruck.getMenu().hasFoodId("1");
        assert foodTruck.getMenu().getFood("1").compareTo(burger) == 0;
    }

    @org.junit.Test
    public void removeFoodFromMenuSuccessTest() {
        foodTruck.removeFoodFromMenu("1");
        assert !foodTruck.getMenu().hasFoodId("1");
    }


}

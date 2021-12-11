package entities;

import exceptions.CollidedFoodException;
import exceptions.FoodIdCollisionException;
import exceptions.UnknownFoodException;

public class TestFoodMenu {
    FoodMenu menu;


    @org.junit.Before
    public void Setup() {
        menu = new FoodMenu();
    }

    @org.junit.Test
    public void hasFoodIdTest() {
        assert !menu.hasFoodId("1");
    }


    @org.junit.Test
    public void addFoodTest() throws CollidedFoodException, FoodIdCollisionException, UnknownFoodException {
        Food pizza = new Food("Pizza", 5.00, "One large slice of Hawaii Piazza");
        menu.addFood(pizza, "1");

        assert menu.hasFoodId("1");
    }

    @org.junit.Test
    public void getFoodTest() throws CollidedFoodException, FoodIdCollisionException, UnknownFoodException {
        Food pizza = new Food("Pizza", 5.00, "One large slice of Hawaii Piazza");
        assert menu.getFood("1").compareTo(pizza) == 0;
    }

    @org.junit.Test
    public void toStringTest() {
        String result = "ID: " + "1" + " " + "Pizza" + " : $" + 5.00 + "\n" + "    " + "One large slice of Hawaii Piazza" + "\n";
        assert menu.toString().equals(result);
    }

    @org.junit.Test
    public void getFoodPriceTest() throws UnknownFoodException {
        assert menu.getFoodPrice("1") == 5.00;
    }


    @org.junit.Test
    public void removeFoodFailedTest() throws UnknownFoodException {
        Food pizza = new Food("Pizza", 5.00, "One large slice of Hawaii Piazza");

        assert !menu.hasFoodId("2");
        assert menu.hasFoodId("1");
        assert menu.getFood("1").compareTo(pizza) == 0;
    }

    @org.junit.Test
    public void removeFoodSuccessTest() {
        assert menu.hasFoodId("1");
        assert !menu.hasFoodId("1");
    }

}

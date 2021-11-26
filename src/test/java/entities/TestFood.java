package entities;

import java.util.ArrayList;
import java.util.Arrays;

public class TestFood {
    Food food;

    @org.junit.Before
    public void Setup() {
        food = new Food("Burger", 9.99, 1, new ArrayList<>(
                Arrays.asList("Fast Entities.Food", "Western")), "A standard Beef Burger."
        );
    }

    @org.junit.Test
    public void changePriceTest() {
        double beforePrice = food.changePrice(10.99);
        assert beforePrice == 9.99 && food.getPrice() == 10.99;
    }

    @org.junit.Test
    public void changeDescriptionsTest() {
        String beforeDescription = food.changeDescription("A standard Chicken Burger.");
        assert beforeDescription.equals("A standard Beef Burger.")
                && food.getDescriptions().equals("A standard Chicken Burger.");
    }

    @org.junit.Test
    public void getIdTest() {
        assert food.getId() == 1;
    }

    @org.junit.Test
    public void getLabelTest() {
        assert food.getLabel().equals(new ArrayList<>(
                Arrays.asList("Fast Entities.Food", "Western")));
    }


}

package entities;

public class TestFood {
    Food food;

    @org.junit.Before
    public void Setup() {
        food = new Food("Burger", 9.99, "Delicious burger");
    }

    @org.junit.Test
    public void getFoodNameTest() {
        assert food.getFoodName().equals("Burger");
    }

    @org.junit.Test
    public void getPriceTest() {
        assert food.getPrice() == 9.99;
    }

    @org.junit.Test
    public void getDescriptionTest() {
        assert food.getDescriptions().equals("Delicious burger");
    }

    @org.junit.Test
    public void compareToTest_1() {
        Food n = new Food("DD", 9.99, "Delicious burger");
        assert food.compareTo(n) != 0;
    }

    @org.junit.Test
    public void compareToTest_2() {
        Food n = new Food("Sandwi", 9.99, "Delicious Sandwich");
        assert food.compareTo(n) > 0;
    }
}

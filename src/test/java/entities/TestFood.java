package entities;

public class TestFood {
    Food food;

    @org.junit.Before
    public void Setup() {
        food = new Food("Burger", 9.99, "Delicious burger");
    }

    @org.junit.Test
    public void getFoodNameTest() {
        Food n = new Food("Sandwich", 9.99, "Delicious Sandwich");
        assert food.getFoodName().equals("Sandwich");
    }

    @org.junit.Test
    public void getPriceTest() {
        Food n = new Food("Sandwich", 9.99, "Delicious Sandwich");
        assert food.getPrice() == 9.99;
    }

    @org.junit.Test
    public void getDescriptionTest() {
        Food n = new Food("Sandwich", 9.99, "Delicious Sandwich");
        assert food.getDescriptions().equals("Delicious Sandwich");
    }

    @org.junit.Test
    public void compareToTest_1() {
        Food n = new Food("Burger", 9.99, "Delicious burger");
        assert food.compareTo(n) == 0;
    }

    @org.junit.Test
    public void compareToTest_2() {
        Food n = new Food("Sandwich", 9.99, "Delicious Sandwich");
        assert food.compareTo(n) > 0;
    }
}

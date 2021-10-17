package Entities;

import java.util.ArrayList;

public class TestFoodMenu {
    FoodMenu menu;
    FoodMenu emptyMenu;


    @org.junit.Before
    public void Setup() throws Exception {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Italian");
        labels.add("Fast food");
        ArrayList<String> labels2 = new ArrayList<String>();
        labels2.add("Drinks");

        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        Food coke = new Food("Coke", 2.00, 2, labels2, "500ml Coke");

        ArrayList<Food> foodList = new ArrayList<>();
        foodList.add(pizza);
        foodList.add(coke);
        ArrayList<Food> foodList2 = new ArrayList<>();

        menu = new FoodMenu(foodList);
        emptyMenu = new FoodMenu(foodList2);
    }


    @org.junit.Test
    public void addFoodTest() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food spaghetti = new Food("Spaghetti", 7.00, 3, labels, "spaghetti bolognese");

        assert menu.addFood(spaghetti);
        assert menu.getFoodList().contains(spaghetti);
    }


    @org.junit.Test
    public void addFoodToEmptyMenuTest() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food spaghetti = new Food("Spaghetti", 7.00, 3, labels, "spaghetti bolognese");

        assert emptyMenu.addFood(spaghetti);
        assert emptyMenu.getFoodList().contains(spaghetti);
    }


    @org.junit.Test
    public void addFoodModifyTest() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food pizza2 = new Food("Pizza", 6.00, 1, labels,
                "One large slice of Salami Piazza");

        assert !menu.addFood(pizza2);
        assert menu.getFoodList().contains(pizza2);
        assert menu.getFoodList().size() == 2;
    }

    @org.junit.Test
    public void removeFoodSuccessTest() {
        ArrayList<String> labels2 = new ArrayList<String>();
        labels2.add("Drinks");
        Food coke = new Food("Coke", 2.00, 2, labels2, "500ml Coke");

        assert menu.removeFood(coke);
        assert !menu.isThereSameNameFood(coke);
    }

    @org.junit.Test
    public void removeFoodDefeatTest() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food spaghetti = new Food("Spaghetti", 7.00, 3, labels, "spaghetti bolognese");

        assert !menu.removeFood(spaghetti);
        assert !menu.isThereSameNameFood(spaghetti);
    }
}

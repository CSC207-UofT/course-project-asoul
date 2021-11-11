package Entities;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * A menu at a food truck (list of all the foods a food truck sells)
 */

public class FoodMenu implements Serializable {
    private final ArrayList<Food> foodList; // a list of Entities.Food objects that are on the menu.


    public FoodMenu(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }

    public FoodMenu() {
        this.foodList = new ArrayList<>();
    }

    /**
     * return whether the menu have the food with same name as the given food.
     *
     * @param food The given food.
     * @return true if there is the food with same name as the given food. false for not
     */
    public boolean isThereSameNameFood(Food food) {
        for (Food f : this.foodList) {
            if (f.getFoodName().equals(food.getFoodName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * add food to menu if food object is not in menu. If the food is in menu, update the food with the new one.
     *
     * @param food The food want to add or update.
     * @return true if we add the food. false if we update the food.
     */
    public boolean addFood(Food food) {
        if (isThereSameNameFood(food)) {
            this.foodList.removeIf(f -> f.getFoodName().equals(food.getFoodName()));
            this.foodList.add(food);
            return false;
        } else {
            this.foodList.add(food);
            return true;
        }
    }

    /**
     * remove food with the same name of the given food from menu if the food is in menu.
     *
     * @param food The food want to remove.
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */
    public boolean removeFood(Food food) {
        if (isThereSameNameFood(food)) {
            this.foodList.removeIf(f -> f.getFoodName().equals(food.getFoodName()));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for foodList
     */
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    /**
     * @return An ArrayList of foods' ids in the menu.
     */
    public ArrayList<Integer> getFoodIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (Food f : foodList) {
            ids.add(f.getId());
        }
        return ids;
    }

    /**
     * A menu
     *
     * @return a string representation of Foodmenu object
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Food food : this.foodList) {
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n").append("    ")
                    .append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }


    public Food createCopy(String foodName) {
        for (Food f : this.foodList) {
            if (f.getFoodName().equals(foodName)) {
                return f;
            }
        }
        return null;
    }
}

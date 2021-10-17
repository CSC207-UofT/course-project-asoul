import java.util.ArrayList;


/**
 * A menu at a food truck (list of all the foods a food truck sells)
 */

public class FoodMenu {
    private final ArrayList<Food> foodList; // a list of Food objects that are on the menu.


    public FoodMenu(ArrayList<Food> foodList){
        this.foodList = foodList;
    }

    public FoodMenu() { this.foodList = new ArrayList<Food>(); }

    /**
     * add food to menu if food object is not in menu. If the food is in menu, update the food with the new one.
     */
    public void addFood(Food food){
        this.foodList.removeIf(f -> f.getFoodName().equals(food.getFoodName()));

        this.foodList.add(food);
    }

    /**
     * Getter for foodList
     */
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    /**
     * A menu
     * @return a string representation of Foodmenu object
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Food food: this.foodList){
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n").append("    ")
                    .append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }


    public Food createCopy(String foodName){
        for (Food f : this.foodList){
            if (f.getFoodName().equals(foodName)){
                return f;
            }
        }
        return null;
    }
}

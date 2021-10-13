import java.util.ArrayList;
import java.util.Map;

/**
 * A menu at a food truck (list of all the foods a food truck sells)
 */

public class FoodMenu {
    private ArrayList<Food> foodList; // a list of Food objects that are on the menu.


    public FoodMenu(ArrayList<Food> foodList){
        this.foodList = foodList;
    }

    /**
     * add food to menu if food object is not in menu. If the food is in menu, update the food with the new one.
     * @param food
     */
    public void addFood(Food food){
        int i = 0;
        while (i < this.foodList.size() & (this.foodList.get(i).compareTo(food) > 0)){
            i++;
        }
        if (this.foodList.get(i).compareTo(food) > 0){
            this.foodList.add(food);
        }
        else{
            this.foodList.remove(i);
            this.foodList.add(food);
        }
    }


    /**
     * A menu
     * @return a string representation of Foodmenu object
     */
    public String toString(){
        StringBuilder result = new StringBuilder(new String(""));
        for (Food food: this.foodList){
            result.append(food.getFoodName()).append(":").append(food.getPrice()).append("\n").append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }
}

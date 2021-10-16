import java.util.ArrayList;


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
        StringBuilder result = new StringBuilder(new String(""));
        for (Food food: this.foodList){
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n").append("    ")
                    .append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }


    public static void main(String[] args) {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Italian");
        labels.add("Fast food");
        ArrayList<String> labels2 = new ArrayList<String>();
        labels2.add("Drinks");

        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        Food coke = new Food("Coke", 2.00, 2, labels2, "500ml Coke");

        ArrayList<Food> foodList = new ArrayList<Food>();
        foodList.add(pizza);
        foodList.add(coke);
        ArrayList<Food> foodList2 = new ArrayList<Food>();

        FoodMenu menu = new FoodMenu(foodList);
        FoodMenu emptyMenu = new FoodMenu(foodList2);

        System.out.println(menu);
        System.out.println(emptyMenu);
    }
}

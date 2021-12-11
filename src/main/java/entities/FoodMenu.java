package entities;


import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import exceptions.FoodIdCollisionException;
import exceptions.CollidedFoodException;
import exceptions.UnknownFoodException;

/**
 * A menu at a food truck (list of all the foods a food truck sells)
 */

public class FoodMenu implements Serializable {
    private final HashMap<String, Food> foodMap; // a list of Entities.Food objects that are on the menu.
    private final HashSet<Food> foodSet;

    /**
     * create new FoodMenu.
     */
    public FoodMenu() {
        this.foodMap = new HashMap<>();
        this.foodSet = new HashSet<>();
    }

    /**
     *
     * @param id the id of the food
     * @return true is the menu contains food with this id.
     */
    public boolean hasFoodId(String id){
        return foodMap.containsKey(id);
    }

    /**
     * Add the given food to the menu, if the food is already present, overwrite it with the given food
     *
     * @param food The food want to add or update.
     */
    public void addFood(Food food, String id) throws FoodIdCollisionException, CollidedFoodException{
        if(foodMap.containsKey(id)){
            throw new FoodIdCollisionException();
        }
        if(foodSet.contains(food)){
            throw new CollidedFoodException();
        }
        foodSet.add(food);
        foodMap.put(id, food);
    }

    /**
     * remove food with the same name of the given food from menu if the food is in menu.
     *
     */

    public void removeFood(String id){
        Food fd = foodMap.get(id);
        if(foodMap.containsKey(id)){
            foodMap.remove(id);
            foodSet.remove(fd);
        }
    }

    /**
     * A menu
     *
     * @return a string representation of FoodMenu object
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String name : this.foodMap.keySet() ){
            Food food = foodMap.get(name);
            result.append("ID: ").append(name).append(" ").append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n").append("    ")
                    .append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }

    /**
     *
     * @param id the food's id
     * @return the Food object
     * @throws UnknownFoodException when menu doesn't have good with this id.
     */
    public Food getFood(String id) throws UnknownFoodException {
        if(!foodMap.containsKey(id)){
            throw new UnknownFoodException();
        }
        return foodMap.get(id);
    }

    /**
     *
     * @param id the food's id
     * @return the food's price
     * @throws UnknownFoodException when menu doesn't have good with this id.
     */
    public double getFoodPrice(String id) throws UnknownFoodException {
        if(!foodMap.containsKey(id)){
            throw new UnknownFoodException();
        }
        return foodMap.get(id).getPrice();
    }
}

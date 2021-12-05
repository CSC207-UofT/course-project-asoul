package com.example.asoul.Entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import exceptions.FoodIdCollisionException;
import exceptions.CollidedFoodException;

/**
 * A menu at a food truck (list of all the foods a food truck sells)
 */

public class FoodMenu implements Serializable {
    private final HashMap<String, Food> foodMap; // a list of Entities.Food objects that are on the menu.
    private final HashSet<Food> foodSet;

    public FoodMenu() {
        this.foodMap = new HashMap<>();
        this.foodSet = new HashSet<>();
    }

    /**
     * return whether the menu have the food with same name as the given food.
     *
     * @param food The given food.
     * @return true if there is the food with same name as the given food. false for not
     */
    public boolean hasFood(Food food) {
        return foodSet.contains(food);
    }

    public boolean hasFood(String food) {
        for(Food f: foodSet){
            if(f.getFoodName().equals(food)){
                return true;
            }
        }
        return false;
    }

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
     * @param food The food want to remove.
     * @return true if the food is removed successfully. false if the food is not in the menu.
     */
    public boolean removeFood(Food food) {
        return removeFood(food.getFoodName());
    }

    public boolean removeFood(String name){
        if(foodMap.containsKey(name)){
            foodMap.remove(name);
            return true;
        }
        return false;
    }

    /**
     * A menu
     *
     * @return a string representation of Foodmenu object
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String name : this.foodMap.keySet() ){
            Food food = foodMap.get(name);
            result.append(food.getFoodName()).append(" : $").append(food.getPrice()).append("\n").append("    ")
                    .append(food.getDescriptions()).append("\n");
        }
        return result.toString().trim();
    }

    public Food getFood(String id) {
        return foodMap.get(id);
    }
}


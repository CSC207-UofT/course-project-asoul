package Controllers;

import Use_case.FoodTruckManager;
import Use_case.OrderManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FoodTruckScene extends Scene {
    public String foodTruckName;
    public ArrayList<Integer> cart;
    public String cusName;
    public int orderID; // we are going to use it later, so it can't be local variable.
    private boolean checkOut; // we are going to use it later, so it can't be local variable.

    public FoodTruckScene() {
        super("FoodTruck");
        checkOut = false;
        commandSet.add("select_food");
        commandSet.add("remove_food");
        commandSet.add("check_out");
        commandSet.add("back");
    }

    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name) {
//        this.foodTruck = FoodTruckManager.getFoodTruckById(name);
        this.foodTruckName = name;
    }

    public void setUsername(String name) {
        this.cusName = name;
    }


    public void selectFood(int id, int num){
        int i = 0;
        while (!(i == num)){
            i = i + 1;
            cart.add(id);
        }
    }



    public void removeFood(int id, int num){
        int i = 0;
        while (!(i == num)){
            i = i + 1;
            if (cart.contains(id)){
                cart.remove(id);
            }
        }
    }


    public boolean checkValidFood(int id){
        return FoodTruckManager.checkFoodFromFTMenu(id, foodTruckName);
    }
}
package Controllers;

import Entities.Food;
import Entities.FoodTruck;
import Use_case.FoodTruckManager;
import Use_case.OrderManager;
import Use_case.UserManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FoodTruckScene extends Scene {
    public FoodTruck foodTruck;
    public String foodTruckName;
    public ArrayList<String> cart;
    public String cusName;
    public int orderID; // we are going to use it later, so it can't be local variable.
    private boolean checkOut; // we are going to use it later, so it can't be local variable.

    public FoodTruckScene() {
        super("FoodTruck");
        checkOut = false;
        this.commandSet.add("back");
        this.commandSet.add("check_out");
        this.commandSet.add("select");
    }

    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name) {
        this.foodTruck = FoodTruckManager.getFoodTruckById(name);
        this.foodTruckName = name;
    }

    public void setUsername(String name) {
        this.cusName = name;
    }

    public void handleInput(String input) {

    }
}
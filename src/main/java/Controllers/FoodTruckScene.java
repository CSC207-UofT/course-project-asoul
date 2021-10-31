package Controllers;

import Entities.Food;
import Entities.FoodTruck;
import Use_case.CustomerManager;
import Use_case.FoodTruckManager;
import Use_case.OrderManager;
import Use_case.SellerManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class FoodTruckScene extends Scene {
    private FoodTruck foodTruck;
    private String foodTruckName;
    private ArrayList<String> cart;
    private String cusName;
    private int orderID; // we are going to use it later, so it can't be local variable.
    private boolean checkOut; // we are going to use it later, so it can't be local variable.

    public FoodTruckScene() {
        super("Entities.FoodTruck");
        checkOut = false;
    }


    @Override
    public String constructOutputString() {
        return foodTruckName + "\n" + "rating : " + foodTruckManager.getRating(foodTruckName) + "\n" +
                foodTruckManager.getMenu(foodTruckName) + "\n----------------Cart---------------" + cart;
    }


    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name) {
        this.foodTruck = foodTruckManager.getFoodTruckById(name);
        this.foodTruckName = name;
    }

    public void setUsername(String name) {
        this.cusName = name;
    }

    public void handleInput(String input) {
        String[] text = input.split(" ");
        if (input.equals("back")) {
            cart = new ArrayList<>();
            this.switchScene(Scene.allScenes.get("Market"));
        } else if (input.equals("check out")) {
            ArrayList<Food> foodList = orderManager.getMenuFood(this.cart, this.foodTruck);
            HashMap<String, String> info = foodTruckManager.getFoodTruckDetail(foodTruckName);
            orderID = orderManager.creatOrder(this.foodTruck, foodList, customerManager.getNickname(cusName),
                    customerManager.getPhoneNumber(cusName),
                    sellerManager.getNickname(info.get("seller")), sellerManager.getPhoneNumber(info.get("seller")));
        } else if (text[0].equals("select")) {
            String[] foods = Arrays.copyOfRange(text, 1, text.length);
            Collections.addAll(cart, foods);
        }
    }
}
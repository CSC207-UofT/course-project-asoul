package default_scene_implementation;

import controllers.Scene;
import exceptions.IncorrectArgumentException;
import exceptions.IncorrectCredentialsException;
import exceptions.UnauthorizedAccessException;
import exceptions.UnknownCommandException;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class FoodTruckScene extends Scene {
    private static final FoodTruckScene fts = new FoodTruckScene();
    private final HashMap<String, Integer> cart; // String = key to the food on the food menu, integer = quantity
    private String username;
    private String accessKey;
    private String foodtruck;

    private FoodTruckScene() {
        super();
        foodtruck = "";
        cart = new HashMap<>();
    }

    public static Singleton getInstance(){
        return fts;
    }

    @Override
    public void handleInputString(String input){
        String[] text = input.split(" ");
        switch (text[0]) {
            case "back":
                switchScene((MarketScene)MarketScene.getInstance());
                break;
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "exit":
                Scene.exit = true;
                break;
            case "add":
                try {
                    this.addFoodToCart(text[1], Integer.parseInt(text[2]));
                }catch (NumberFormatException e){
                    this.state.append((new IncorrectArgumentException()).getMessage()).append("\n");
                }
                break;
            case "remove":

                break;
            case "confirm":
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    @Override
    public String constructOutputString(){
        StringBuilder sb = new StringBuilder();

        // Display Foodtruck menu
        sb.append(FoodTruckManager.getFoodTruckDetail(foodtruck)).append("\n\n");
        sb.append("------------------Your Cart--------------------\n");
        // Print Cart
        for(String key: cart.keySet()){
            try {
                String foodName = FoodTruckManager.getFoodName(foodtruck, key);
                int quantity = cart.get(key);
                double price = FoodTruckManager.getFoodPrice(foodtruck, key);
                sb.append(String.format("ID: %s Item: %s Price: %f Quantity: %d", key, foodName, price, quantity));
            }catch (Exception e){
                state.append(e.getMessage()).append("\n");
            }
        }
        sb.append("\n");

        sb.append(getHelpMessage()).append("\n");
        return sb.toString();
    }

    public void addFoodToCart(String key, int quantity){
        if(!FoodTruckManager.hasFoodId(key, foodtruck)){
            this.state.append("Selected food does not exist in the menu!\n");
        }
        cart.put(key, quantity);
    }

    public void removeFoodFromCart(String key){
        cart.remove(key);
    }

    public void setUserInfo(String name, String accessKey) {
        this.username = name;
        this.accessKey = accessKey;
    }

    public void setFoodtruck(String name){
        foodtruck = name;
    }
}
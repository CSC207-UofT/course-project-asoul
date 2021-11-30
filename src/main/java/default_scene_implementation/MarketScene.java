package default_scene_implementation;

import controllers.Scene;
import exceptions.UnknownFoodTruckException;
import singleton_pattern.Singleton;

class MarketScene extends Scene {
    private final static MarketScene ms = new MarketScene();
    public boolean unknownFoodTruckError;
    private String username;

    private MarketScene() {
        super("Market");
        this.unknownFoodTruckError = false;
        this.username = "";
        this.commandSet.add("view_user_info");
        this.commandSet.add("select");
    }

    public static Singleton getInstance(){
        return ms;
    }

    public void refreshOutputState() {
        this.unknownFoodTruckError = false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void viewFoodTruck(String id) throws UnknownFoodTruckException { // Forward to foodtruck page
        FoodTruckScene fc = (FoodTruckScene) Scene.allScenes.get("FoodTruck");
        fc.setFoodTruck(id);
        fc.setUsername(this.username);
        this.switchScene("FoodTruck");
    }
}

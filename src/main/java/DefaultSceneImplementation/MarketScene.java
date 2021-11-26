package DefaultSceneImplementation;

import Controllers.Scene;
import exceptions.UnknownFoodTruckException;

public class MarketScene extends Scene {
    public boolean unknownFoodTruckError;
    private String username;

    public MarketScene() {
        super("Market");
        this.unknownFoodTruckError = false;
        this.username = "";
        this.commandSet.add("view_user_info");
        this.commandSet.add("select");
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

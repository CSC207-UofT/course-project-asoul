package default_scene_implementation;

import controllers.Scene;
import exceptions.UnknownFoodTruckException;
import singleton_pattern.Singleton;

class MarketScene extends Scene {
    private final static MarketScene ms = new MarketScene();
    private String username;
    private String accessKey;

    private MarketScene() {
        super("Market");
        this.username = "";
    }

    @Override
    public void handleInputString(String input){
        String[] text = input.split(" ");
        if (input.equals("view user info")) {
            this.switchScene((Scene)UserInformationScene.getInstance());
        } else if (text[0].equals("select")) {
            try {
                this.viewFoodTruck(text[1]);
            } catch (UnknownFoodTruckException e) {
                this.state.append(e.getMessage());
            }
        } else{

        }
    }

    @Override
    public String constructOutputString(){
        return "";
    }

    public static Singleton getInstance(){
        return ms;
    }

    public void viewFoodTruck(String id) throws UnknownFoodTruckException { // Forward to foodtruck page
        FoodTruckScene fc = (FoodTruckScene) FoodTruckScene.getInstance();
        fc.setFoodTruck(id);
        this.switchScene(fc);
    }
}

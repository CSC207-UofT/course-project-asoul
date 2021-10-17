import Exceptions.UnknownFoodTruckException;

import java.util.HashMap;

public class MarketScene extends Scene{
    private boolean unknownFoodTruckError;
    private String username;

    public MarketScene(){
        super("Market");
        this.unknownFoodTruckError = false;
        this.username = "";
    }

    @Override
    public void handleInput(String input) {
        this.refreshOutputState();
        String[] text = input.split(" ");
        if(input.equals("view user info")){
            this.switchScene(Scene.allScenes.get("UserInformation"));
        }else if(text[0].equals("select")){
            try {
                this.viewFoodTruck(text[1]);
            }catch (UnknownFoodTruckException e){
                this.unknownFoodTruckError = true;
            }
        }else{
            // TODO: Throws unknown command error
        }
    }

    private void refreshOutputState(){
        this.unknownFoodTruckError = false;
    }

    public void setUsername(String username){
        this.username = username;
    }

    private void viewFoodTruck(String id) throws UnknownFoodTruckException { // Forward to foodtruck page
        FoodTruckScene fc = (FoodTruckScene) Scene.allScenes.get("FoodTruck");
        fc.setFoodTruck(id);
        fc.setUsername(this.username);
        this.switchScene("FoodTruck");
    }

    @Override
    public String constructOutputString() {
        HashMap<String, String> foodTruckInfo = foodTruckManager.getAllFoodTruckDescription();
        StringBuilder outputString = new StringBuilder("------------------------Market---------------------------");
        for(String field: foodTruckInfo.keySet()){
            String content = foodTruckInfo.get(field);
            outputString.append("\n\nTruckName:").append(field).append("\n").append(content);
        }
        if(this.unknownFoodTruckError){
            outputString.append("\n\n Unknown Food Truck name entered, please check your spelling before entering");
        }
        outputString.append("\ntype <select> and choose a truck");
        return outputString.toString();
    }
}

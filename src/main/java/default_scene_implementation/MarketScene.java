package default_scene_implementation;

import controllers.Scene;
import exceptions.UnknownCommandException;
import exceptions.UnknownFoodTruckException;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;

import java.util.HashMap;

class MarketScene extends Scene {
    private final static MarketScene ms = new MarketScene();
    private HashMap<String, String> info;
    private HashMap<Integer, String> pointer;

    private MarketScene() {
        super("Market");
        pointer = new HashMap<>();
    }

    private void updateInfo(){
        info = FoodTruckManager.getActiveFoodTruckDescription();
        assignPointer();
    }

    private void assignPointer(){
        int counter = 1;
        for(String name: info.keySet()){
            pointer.put(counter, name);
            counter ++;
        }
    }

    @Override
    public void handleInputString(String input){
        String[] text = input.split(" ");
        if (input.equals("view_user_info")) {
            this.switchScene((Scene)UserInformationScene.getInstance());
        } else if (text[0].equals("select")) {
            try {
                this.viewFoodTruck(text[1]);
            } catch (UnknownFoodTruckException e) {
                this.state.append(e.getMessage());
            }
        } else{
            this.state.append((new UnknownCommandException()).getMessage()).append("\n");
        }
    }

    @Override
    public String constructOutputString(){
        updateInfo();
        StringBuilder outputString = new StringBuilder("------------------------Market---------------------------");
        for (String field : info.keySet()) { //TODO: Sorting
            String content = info.get(field);
            outputString.append("\n\nTruckName:").append(field).append("\n").append(content);
        }
        outputString.append("\n\n");
        outputString.append(state);

        return outputString.toString();
    }

    public static Singleton getInstance(){
        return ms;
    }

    public void viewFoodTruck(String id) throws UnknownFoodTruckException { // Forward to foodtruck page
        FoodTruckScene fc = (FoodTruckScene) FoodTruckScene.getInstance();
        try{
            int i = Integer.parseInt(id);
            String name = pointer.get(i);
            fc.setFoodTruck(name);
        }catch (NumberFormatException e){
            if(FoodTruckManager.existsTruck(id)){
                fc.setFoodTruck(id);
            }else{
                state.append("Unknown id entered! Please enter the name of the foodtruck or its displayed id\n");
            }
        }

        this.switchScene(fc);
    }
}

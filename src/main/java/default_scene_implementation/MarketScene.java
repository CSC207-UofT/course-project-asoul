package default_scene_implementation;

import controllers.Scene;
import exceptions.UnknownCommandException;
import exceptions.UnknownFoodTruckException;
import exceptions.UnknownSorterException;
import singleton_pattern.Singleton;
import sorters.Sorter;
import sorters.SorterSimpleFactory;
import use_case.FoodTruckManager;

import java.util.ArrayList;
import java.util.HashMap;

class MarketScene extends Scene {
    private final static MarketScene ms = new MarketScene();
    private HashMap<String, String> info;
    private final HashMap<Integer, String> pointer;
    private String sorter;

    private MarketScene(){
        super();
        info = new HashMap<>();
        pointer = new HashMap<>();
        sorter = "";
        setHelpMessage("\n\nAll commands:\n" +
                "help -> View all commands on this page\n" +
                "back -> Go back to user info page\n" +
                "select + [Space] + [Foodtruck id] -> view the food menu of the specified foodtruck and prepare for ordering\n" +
                "sort_by + [Space] + [Sorting method] -> Sort the foodtrucks with specified method (See next line for possible methods)\n" +
                "        Sorting Methods: 'name length', 'rating'\n");
    }

    private void updateInfo(){
        info = FoodTruckManager.getActiveFoodTruckDescription();
        assignPointer();
    }

    private ArrayList<String> sortTruckInfo()throws UnknownSorterException{
        Sorter s = SorterSimpleFactory.constructSorter(sorter);
        HashMap<String, String> items = new HashMap<>();
        if(sorter.equals("rating")){
            for(String key: info.keySet()){
                items.put(key, Double.toString(FoodTruckManager.getRating(key)));
            }
        }else if(sorter.equals("name length")){
            for(String key: info.keySet()){
                items.put(key, FoodTruckManager.getTruckName(key));
            }
        }
        return s.sort(items);
    }

    private void assignPointer(){
        int counter = 1;
        pointer.replaceAll((key, value) -> "");
        try {
            ArrayList<String> sorted = sortTruckInfo();
            for (String name : sorted) {
                pointer.put(counter, name);
                counter++;
            }
        }catch (UnknownSorterException e){ // When Sorter is unknown, assign pointers by the order of the elements in the map
            for (String name : info.keySet()) {
                pointer.put(counter, name);
                counter++;
            }
        }
    }

    @Override
    public void handleInputString(String input){
        String[] text = input.split(" ");
        if (input.equals("back")) {
            this.switchScene((Scene)UserInformationScene.getInstance());
        } else if (text[0].equals("select")) {
            try {
                this.viewFoodTruck(text[1]);
            } catch (UnknownFoodTruckException e) {
                this.state.append(e.getMessage());
            }
        } else if(text[0].equals("sort_by")){
            if(SorterSimpleFactory.containsSorter(text[1])){
                this.sorter = text[1];
            }else{
                this.state.append((new UnknownSorterException()).getMessage()).append("\n");
            }
        }else{
            this.state.append((new UnknownCommandException()).getMessage()).append("\n");
        }
    }

    @Override
    public String constructOutputString(){
        updateInfo();
        StringBuilder outputString = new StringBuilder("------------------------Market---------------------------");
        for (int i = 1; i <= info.size(); i++) { //TODO: Sorting
            String key = pointer.get(i);
            String content = info.get(key);
            outputString.append("\n\nTruck ID: ").append(i).append("\n").append(content);
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
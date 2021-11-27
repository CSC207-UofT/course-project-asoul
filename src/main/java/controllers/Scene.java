package controllers;

import use_case.FoodTruckManager;
import use_case.OrderManager;
import use_case.UserManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

abstract public class Scene{
    protected static boolean exit = false;
    protected static final HashMap<String, Scene> allScenes = new HashMap<>();
    protected static Scene activeScene;
    // instance fields
    protected HashMap<String, String> fields; // Input fields will be stored here
    protected HashSet<String> commandSet; // These methods will be called by user typed commands
    protected String name;
    // private HashMap<String, State> stateMap;

    protected Scene(String name) {
        this.name = name;
        this.fields = new HashMap<>();
        this.commandSet = new HashSet<>();
        Scene.allScenes.put(name, this);
    }

    public static void init() throws ClassNotFoundException, IOException {
        FoodTruckManager.constructFoodTruckDataBase();
        UserManager.constructUserDataBase();
        OrderManager.constructOrderDataBase();
    }

    public static void exit() throws IOException {
        FoodTruckManager.saveFoodTruckDataBase();
        UserManager.saveUserDataBase();
        OrderManager.saveOrderDataBase();
    }

    public static void setActiveScene(Scene scene) {
        activeScene = scene;
    }

    public static Scene getActiveScene() {
        return Scene.activeScene;
    }

    public static boolean isRunning() {
        return !Scene.exit;
    }

    protected void fillInField(String field, String text) {
        this.fields.put(field, text);
    }

    protected void clearFields() { // Remove all entered information
        this.fields.replaceAll((key, value) -> "");
    }

    protected void switchScene(Scene scene) {
        Scene.activeScene = scene;
    } // switch to another scene

    protected void switchScene(String name) {
        Scene.activeScene = Scene.allScenes.get(name);
    }

//    abstract public String constructOutputString();
}

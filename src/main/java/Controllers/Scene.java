package Controllers;

import Use_case.FoodTruckManager;
import Use_case.OrderManager;
import Use_case.UserManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class Scene {
    protected static boolean exit = false;
    protected static final HashMap<String, Scene> allScenes = new HashMap<>();
    protected static Scene activeScene;
    // instance fields
    protected HashMap<String, String> fields; // Input fields will be stored here
    protected ArrayList<String> commandSet; // These methods will be called by user typed commands
    protected String name;

    public Scene(String name) {
        this.name = name;
        this.fields = new HashMap<>();
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
        System.out.println(2);
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

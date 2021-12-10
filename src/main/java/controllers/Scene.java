package controllers;

import singleton_pattern.Singleton;
import java.util.HashMap;


abstract public class Scene implements Singleton{
    protected static boolean exit = false;
    protected static Scene activeScene;

    // instance fields
    protected HashMap<String, String> fields; // Input fields will be stored here
    protected StringBuilder state;
    private String helpMessage;

    protected Scene() {
        this.fields = new HashMap<>();
        this.state = new StringBuilder();
        this.helpMessage = "";
    }

    protected void setHelpMessage(String message){
        this.helpMessage = message;
    }

    protected String getHelpMessage(){
        return this.helpMessage;
    }

    protected void fillInField(String field, String text) {
        this.fields.put(field, text);
    }

    protected void clearFields() { // Remove all entered information
        this.fields.replaceAll((key, value) -> "");
    }

    public abstract void handleInputString(String input);

    public abstract String constructOutputString();

    protected void switchScene(Scene scene) {
        Scene.activeScene = scene;
        this.clearFields();
    }
}

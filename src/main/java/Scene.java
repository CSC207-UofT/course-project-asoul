import Exceptions.UnknownCommandException;

import java.security.KeyException;
import java.util.HashMap;
import java.util.concurrent.Callable;

abstract public class Scene {
    protected static boolean exit = false;
    protected final static FoodTruckManager foodTruckManager = new FoodTruckManager(); // All scenes share the same use case classes
    protected final static SellerManager sellerManager = new SellerManager();
    protected final static CustomerManager customerManager = new CustomerManager();
    protected final static OrderManager orderManager = new OrderManager();
    protected static final HashMap<String, Scene> allScenes = new HashMap<>();
    protected static Scene activeScene;
    // instance fields
    protected HashMap<String, String> fields; // Input fields will be stored here
    protected HashMap<String, Callable<Void>> commandSet; // These methods will be called by user typed commands
    protected String name;

    public Scene(String name){
        this.name = name;
        this.commandSet = new HashMap<>();
        this.fields = new HashMap<>();
        Scene.allScenes.put(name, this);
    }

    public static void setActiveScene(Scene scene){
        activeScene = scene;
    }

    public static Scene getActiveScene(){
        return Scene.activeScene;
    }

    public static boolean isRunning(){
        return !Scene.exit;
    }

    public void addCommand(String command, Callable<Void> method){
        this.commandSet.put(command, method);
    }

    public void handleInput(String input){
        /* The input will be separated into segments, one of which will be used to determine which function to call and
        rest will be used to determine parameters for that function
        TODO: Figure out how to store methods in maps so they can be accessed by tied commands

        try{
            Object[] i = this.parseParameters(input);
            String command = (String)i[0];
            HashMap<String, Object> parameters = (HashMap<String, Object>)i[1];
            Callable method = this.commandSet.get(command);
            method.call(parameters);
        }catch(KeyException e){
            System.out.print("Command not found!");
        } catch (Exception e) {
            System.err.println("Failed to execute command!");
        }
        */

    }

    private Object[] parseParameters(String raw_command){
        //TODO: Separate parameters from command
        Object[] arr = new Object[2];
        arr[0] = ""; // command
        arr[1] = new HashMap<String, Object>(); // parameters
        return arr;
    }

    protected void fillInField(String field, String text){
        this.fields.put(field, text);
    }

    protected void clearFields(){
        this.fields.replaceAll((key, value) -> "");
    }

    protected void switchScene(Scene scene){
        Scene.activeScene = scene;
    }

    protected void switchScene(String name){
        Scene.activeScene = Scene.allScenes.get(name);
    }

    abstract public String constructOutputString();
}

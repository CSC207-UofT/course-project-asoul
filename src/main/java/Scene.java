import java.security.KeyException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Scene {
    private static foodTruckManager = new FoodTruckManager(); // All scenes shares the same use case classes
    private static userManager = new UserManager();
    private static orderManager = new OrderManager();
    private String name;
    private HashMap<String, Callable<Void>> commandSet;
    private static HashMap<String, Scene> allScenes = new HashMap<>();

    public Scene(String name){
        this.name = name;
        Scene.allScenes.put(name, this);
    }

    public void addCommand(String command, Callable<Void> method){
        this.commandSet.put(command, method);
    }

    public void handleInput(String input){
        /* The input will be separated into segments, one of which will be used to determine which function to call and
        rest will be used to determine parameters for that function
        */
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
    }

    private Object[] parseParameters(String raw_command){
        //TODO: Separate parameters from command
        Object[] arr = new Object[2];
        arr[0] = ""; // command
        arr[1] = new HashMap<String, Object>(); // parameters
        return arr;
    }

    public String construct_outputString(){
        // TODO: Construct a string represents the current information of this scene and return it to the User Interface
        return "";
    }

    public String getName(){
        return name;
    }

    public Scene update(String command){
        this.handleInput(command);
        return this;
    }

}

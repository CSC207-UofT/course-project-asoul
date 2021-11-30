package default_scene_implementation;

import controllers.Scene;
import exceptions.UnauthorizedAccessException;
import singleton_pattern.Singleton;
import use_case.UserManager;

class FoodTruckEditScene extends Scene {
    private static final FoodTruckEditScene ies = new FoodTruckEditScene();

    private FoodTruckEditScene() {
        super("TruckInfoEdit");
    }

    public static Singleton getInstance(){
        return ies;
    }

    @Override
    public void handleInputString(String input){

    }

    @Override
    public String constructOutputString(){
        return "";
    }

    public void setUserInfo(String username, String key) throws UnauthorizedAccessException {
        //
    }
}

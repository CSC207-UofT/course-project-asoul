package default_scene_implementation;

import controllers.Scene;
import singleton_pattern.Singleton;

class MenuEditScene extends Scene {
    private static MenuEditScene mes = new MenuEditScene();

    private MenuEditScene() {
        super();

    }
    @Override
    public void handleInputString(String input) {

    }

    @Override
    public String constructOutputString() {
        return null;
    }

    public static Singleton getInstance(){
        return mes;
    }
}

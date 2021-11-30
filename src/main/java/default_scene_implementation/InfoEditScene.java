package default_scene_implementation;

import controllers.Scene;
import singleton_pattern.Singleton;

class InfoEditScene extends Scene {
    private static final InfoEditScene ies = new InfoEditScene();

    private InfoEditScene() {
        super("InfoEdit");
    }

    public static Singleton getInstance(){
        return ies;
    }
}

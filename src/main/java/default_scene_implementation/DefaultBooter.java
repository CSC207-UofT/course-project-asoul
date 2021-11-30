package default_scene_implementation;
import controllers.SceneBooter;
import observer_pattern.Observer;

import java.io.IOException;
import java.util.HashSet;

public class DefaultBooter extends SceneBooter{

    @Override
    public void boot() throws IOException, ClassNotFoundException {
        super.boot();
        LoginScene ls = (LoginScene)LoginScene.getInstance();
        setActiveScene(ls);
    }

    @Override
    public String outputInString(){
        return "";
    }

    @Override
    public void handleInputInString(String input){

    }
    // TODO: Add io methods for android interface
}

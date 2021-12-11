package default_scene_implementation;

import controllers.SceneBooter;

import java.io.IOException;

public class DefaultBooter extends SceneBooter {

    @Override
    public void boot() throws IOException, ClassNotFoundException {
        super.boot();
        LoginScene ls = (LoginScene) LoginScene.getInstance();
        setActiveScene(ls);
        notifyObservers();
    }

    @Override
    public String outputInString() {
        return getActiveScene().constructOutputString();
    }

    @Override
    public void handleInputInString(String input) {
        super.handleInputInString(input);
        getActiveScene().handleInputString(input);
        notifyObservers();
    }
    // TODO: Add io methods for android interface
}

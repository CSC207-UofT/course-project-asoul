import java.io.*;
import java.util.*;

public class CommandlineInterface {

    public CommandlineInterface(){
        Scene loginScene = new LoginScene();
        Scene.setActiveScene(loginScene);
    }

    public void run(String command){
        Scene.getActiveScene().handleInput(command);
        System.out.println(Scene.getActiveScene().constructOutputString());
        System.out.print(">>> ");
    }

    public boolean isRunning(){
        return Scene.isRunning();
    }
}

import java.io.*;
import java.util.*;

public class CommandlineInterface {
    private Scene activeScene;
    private boolean finish;

    public CommandlineInterface(){
        this.activeScene = new Scene("Login");
        this.finish = false;
    }

    public void run(String command){
        Scene newScene = this.activeScene.update(command);
        System.out.println(this.activeScene.construct_outputString());
        this.activeScene = newScene;
        if(command.equals("quit")){
            this.finish = true;
        }
    }

    public boolean isRunning(){
        return !this.finish;
    }
}

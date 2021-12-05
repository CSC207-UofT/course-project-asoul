package command_line_interface;

import controllers.SceneBooter;
import default_scene_implementation.*;
import exceptions.IncorrectArgumentException;
import observer_pattern.Observer;

import java.io.*;

public class CommandlineInterface implements Observer {
    String output;
    SceneBooter sceneBooter;
    
    public CommandlineInterface(){
        sceneBooter = new DefaultBooter();
        subscribe(sceneBooter);
        output = "";
    }

    @Override
    public void update(){
        output = sceneBooter.outputInString();
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        CommandlineInterface cm = new CommandlineInterface();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cm.sceneBooter.boot();
        do {
            try {
                System.out.println(cm.output);
                System.out.print(">>> ");
                cm.sceneBooter.handleInputInString(br.readLine());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println((new IncorrectArgumentException()).getMessage());
            }
        } while (cm.sceneBooter.isRunning());
        cm.sceneBooter.terminate();
        br.close();
    }
}

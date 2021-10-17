import java.io.*;
import java.util.*;

public class CommandlineInterface {

    public CommandlineInterface(){
        Scene loginScene = new LoginScene();
        Scene.setActiveScene(loginScene);
    }

    public boolean isRunning(){
        return Scene.isRunning();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommandlineInterface cm = new CommandlineInterface();
        do{
            System.out.println(Scene.getActiveScene().constructOutputString());
            System.out.print(">>> ");
            Scene.getActiveScene().handleInput(br.readLine());
        }while(cm.isRunning());
        System.out.println("Exit Program!");
        br.close();
    }
}

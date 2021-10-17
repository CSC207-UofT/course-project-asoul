import java.io.*;
import java.util.*;

public class CommandlineInterface {

    public CommandlineInterface(){
        Scene loginScene = new LoginScene();
        new MarketScene();
        new UserInformationScene();
        new FoodTruckScene();
        Scene.setActiveScene(loginScene);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println(Scene.getActiveScene().constructOutputString());
            System.out.print(">>> ");
            Scene.getActiveScene().handleInput(br.readLine());
        }while(Scene.isRunning());
        System.out.println("Exit Program!");
        br.close();
    }
}

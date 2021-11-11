package Interface;

import Controllers.*;

import java.io.*;

public class CommandlineInterface {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoginScene loginScene = new LoginScene();
        new MarketScene();
        new UserInformationScene();
        new FoodTruckScene();
        Scene.setActiveScene(loginScene);
        Scene.init();
        do {
            System.out.println(Scene.getActiveScene().constructOutputString());
            System.out.print(">>> ");
            Scene.getActiveScene().handleInput(br.readLine());
        } while (Scene.isRunning());
        Scene.exit();
        System.out.println("Exit Program!");
        br.close();
    }
}

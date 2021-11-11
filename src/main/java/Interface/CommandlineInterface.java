package Interface;

import Controllers.*;

import java.io.*;

public class CommandlineInterface {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoginScene loginScene = new LoginScene();
        MarketScene ms = new MarketScene();
        UserInformationScene us = new UserInformationScene();
        FoodTruckScene fts = new FoodTruckScene();
        RegisterScene rs = new RegisterScene();
        InputHandler handler = new InputHandler(loginScene, ms, us, fts, rs);
        Scene.setActiveScene(rs);
        System.out.println("Program Started! Please log in or register.");

        do {
//            System.out.println(Scene.getActiveScene().constructOutputString());
//            System.out.print(">>> ");
//            Scene.getActiveScene().handleInput(br.readLine());
            try {
                String input = br.readLine();
                handler.call(input, handler);
            } catch (Exception e) {
                e.toString();
            }
        } while (Scene.isRunning());
        System.out.println("Exit Program!");
        br.close();
    }
}

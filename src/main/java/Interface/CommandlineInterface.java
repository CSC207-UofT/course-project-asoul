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
        OutputConstructor constructor = new OutputConstructor(loginScene, ms, us, fts, rs);
        SystemInOut system = new SystemInOut(handler, constructor);
        System.out.println("Program Started! Please login or register.");

        do {
//            System.out.println(Scene.getActiveScene().constructOutputString());
//            System.out.print(">>> ");
//            Scene.getActiveScene().handleInput(br.readLine());
            try {
                String input = br.readLine();
                String output = system.processInput(input);
                System.out.println(output);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO:
            }
        } while (Scene.isRunning());
        System.out.println("Exit Program!");
        br.close();
    }
}

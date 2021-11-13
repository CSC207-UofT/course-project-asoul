package Interface;

import Controllers.*;

import java.io.*;

public class CommandlineInterface {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoginScene loginScene = new LoginScene();
        MarketScene ms = new MarketScene();
        UserInformationScene us = new UserInformationScene();
        FoodTruckScene fts = new FoodTruckScene();
        RegisterScene rs = new RegisterScene();
        InputHandler handler = new InputHandler(loginScene, ms, us, fts, rs);
        Scene.setActiveScene(rs);
        OutputConstructor constructor = new OutputConstructor(loginScene, ms, us, fts, rs);
        System.out.println("Program Started! Please login or register.");
        Scene.init();
        do {
            try {
                String input = br.readLine();
                String output = constructor.outputGeneralGenerator(handler.handlingGeneralInput(input));
                System.out.println(output);
                System.out.print(">>> ");
            } catch (Exception e) {
                e.printStackTrace();
                //TODO:
            }
        } while (Scene.isRunning());
        System.out.println("Exit Program!");
        Scene.exit();
        br.close();
    }
}

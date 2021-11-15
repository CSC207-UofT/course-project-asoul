package Interface;

import Controllers.*;
import Exceptions.IncorrectCredentialsException;

import java.io.*;

public class CommandlineInterface {

    public static void main(String[] args) throws IOException, ClassNotFoundException, IncorrectCredentialsException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LoginScene loginScene = new LoginScene();
        MarketScene ms = new MarketScene();
        UserInformationScene us = new UserInformationScene();
        FoodTruckScene fts = new FoodTruckScene();
        RegisterScene rs = new RegisterScene();
        OrderScene os = new OrderScene();
        InputHandler handler = new InputHandler(loginScene, ms, us, fts, rs, os);
        Scene.setActiveScene(loginScene);
        OutputConstructor constructor = new OutputConstructor(loginScene, ms, us, fts, rs, os);
        Scene.init();
        System.out.println(constructor.outputGeneralGenerator(""));
        System.out.print(">>> ");
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

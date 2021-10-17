import java.io.*;

public class CommandlineInterface {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scene loginScene = new LoginScene();
        new MarketScene();
        new UserInformationScene();
        new FoodTruckScene();
        Scene.setActiveScene(loginScene);
        do {
            System.out.println(Scene.getActiveScene().constructOutputString());
            System.out.print(">>> ");
            Scene.getActiveScene().handleInput(br.readLine());
        } while (Scene.isRunning());
        System.out.println("Exit Program!");
        br.close();
    }
}

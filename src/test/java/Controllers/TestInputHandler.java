package Controllers;

import java.util.Map;

public class TestInputHandler {
    String input;
    InputHandler inputHandler;
    LoginScene loginScene;
    MarketScene marketScene;
    UserInformationScene userInformationScene;
    FoodTruckScene foodTruckScene;
    RegisterScene registerScene;
    OrderScene orderScene;

    @org.junit.Before
    public void Setup() {
        input = new String("select_food 1 10");
        inputHandler = new InputHandler(loginScene, marketScene, userInformationScene, foodTruckScene, registerScene,
                orderScene);
    }

    @org.junit.Test
    public void parsingInputTest() {
        String[] result = inputHandler.parsingInput(input);
        assert result[0].equals("select_food");
        assert result[1].equals("1");
        assert result[2].equals("10");
    }
}

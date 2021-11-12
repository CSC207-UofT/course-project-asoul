package Controllers;

public class OutputConstructor {
    private final LoginScene ls;
    private final MarketScene ms;
    private final UserInformationScene us;
    private final FoodTruckScene fts;
    private final RegisterScene rs;

    public OutputConstructor(LoginScene ls, MarketScene ms, UserInformationScene us, FoodTruckScene fts,
                             RegisterScene rs) {
        this.fts = fts;
        this.ls = ls;
        this.ms = ms;
        this.us = us;
        this.rs = rs;
    }


    public String outputGeneralGenerator(String inputFeedback) {
        switch (Scene.activeScene.getClass().getName()) {
            case "Controllers.LoginScene":
                return logInSceneOutputGenerator(inputFeedback);
            case "Controllers.UserInformationScene":
                return userInformationSceneOutputGenerator(inputFeedback);
            case "Controllers.MarketScene":
                return marketSceneOutputGenerator(inputFeedback);
            case "Controllers.FoodTruckScene":
                return foodTruckSceneOutputGenerator(inputFeedback);
            case "Controllers.RegisterScene":
                return registerSceneOutputGenerator(inputFeedback); //TODO: add more cases.
        }
        return ""; //TODO;
    }

    private String logInSceneOutputGenerator(String inputFeedback) {
        return ""; //TODO
    }

    private String userInformationSceneOutputGenerator(String inputFeedback) {
        return "";//TODO
    }

    private String marketSceneOutputGenerator(String inputFeedback) {
        return ""; //TODO
    }

    private String foodTruckSceneOutputGenerator(String inputFeedback) {
        return ""; //TODO
    }

    private String registerSceneOutputGenerator(String inputFeedback) {
        if (inputFeedback.equals("username received") || inputFeedback.equals("password received") ||
                inputFeedback.equals("user_type received") || inputFeedback.equals("nickname received") ||
                inputFeedback.equals("phone_number received") || inputFeedback.equals("start register")) {
            return registerGeneralInfo(rs);
        } else if (inputFeedback.equals("confirm received")) {
            return this.rs.registerUser();
        } else {
            return "register failure"; //TODO
        }
    }

    private void askRegisterInto() {
        System.out.println("Please provide your username, password, user type, Phone number");
    }

    public static void printCurrSceneCommands(Scene scene) {
        System.out.println(scene.commandSet); //TODO: toString??
    }

    public static void askLoginInfo() {
        System.out.println("Please enter your username and password");
    }

    public static String registerGeneralInfo(RegisterScene scene) {
        return "Username: " + scene.fields.get("username") + "\n" +
                "Password: " + scene.fields.get("password") + "\n" +
                "User Type: " + scene.fields.get("user_type") + "\n" +
                "Nickname: " + scene.fields.get("nickname") + "\n" +
                "Phone number: " + scene.fields.get("phone_number");
    }



    public static void loginGeneral() {

    }
}

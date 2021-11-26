package Controllers;

import DefaultSceneImplementation.*;
import exceptions.IncorrectCredentialsException;
import use_case.FoodTruckManager;
import use_case.UserManager;

import java.util.HashMap;

public class OutputConstructor {
    private final LoginScene ls;
    private final MarketScene ms;
    private final UserInformationScene us;
    private final FoodTruckScene fts;
    private final RegisterScene rs;
    private final OrderScene os;

    public OutputConstructor(LoginScene ls, MarketScene ms, UserInformationScene us, FoodTruckScene fts,
                             RegisterScene rs, OrderScene os) {
        this.fts = fts;
        this.ls = ls;
        this.ms = ms;
        this.us = us;
        this.rs = rs;
        this.os = os;
    }


    public String outputGeneralGenerator(String inputFeedback) throws IncorrectCredentialsException {
        switch (Scene.activeScene.getClass().getName()) {
            case "DefaultSceneImplementation.LoginScene":
                return logInSceneOutputGenerator(inputFeedback);
            case "DefaultSceneImplementation.UserInformationScene":
                return userInformationSceneOutputGenerator(inputFeedback);
            case "DefaultSceneImplementation.MarketScene":
                return marketSceneOutputGenerator(inputFeedback);
            case "DefaultSceneImplementation.FoodTruckScene":
                return foodTruckSceneOutputGenerator(inputFeedback);
            case "DefaultSceneImplementation.RegisterScene":
                return registerSceneOutputGenerator(inputFeedback); //TODO: add more cases.
        }
        return ""; //TODO;
    }

    private String logInSceneOutputGenerator(String inputFeedback) throws IncorrectCredentialsException {
        if (inputFeedback.equals("username received") || inputFeedback.equals("password received") ||
                inputFeedback.equals("")) {
            return loginGeneralInfo(ls);
        } else if (inputFeedback.equals("confirm received")) {
            return "";
        } else if (inputFeedback.equals("error")) {
            return "Incorrect credentials entered!\n" + loginGeneralInfo(ls);
        } else {
            return "";
        }
    }

    private String userInformationSceneOutputGenerator(String inputFeedback) {
        HashMap<String, String> userInfo = UserManager.getUserByAccountName(us.username);
        StringBuilder outputString = new StringBuilder();
        if (!us.changingPassword) {
            outputString.append("------------------------User Information---------------------------");
            for (String field : userInfo.keySet()) {
                String content = userInfo.get(field);
                outputString.append("\n").append(field).append(": ").append(content).append("\n");
            }
            if (us.invalidFundError) {
                outputString.append("\n\n").append("Invalid Fund entered.");
            } else if (us.changeNicknameSuccess) {
                outputString.append("\n\nSuccessfully changed nickname to: ").append(userInfo.get("nickname"));
            }
        } else {
            for (String field : us.fields.keySet()) {
                String content = us.fields.get(field);
                field = us.displayMap.get(field);
                outputString.append("\n").append(field).append(": ").append(content);
            }
            if (us.unmatchedPasswordError) {
                outputString.append("\nThe password you entered does not match!");
            } else if (us.incorrectOldPasswordError) {
                outputString.append("\nThe old password is incorrect!");
            }
        }
        if (us.changePasswordSuccess) {
            outputString.append("\n\n Successfully changed password!");
        }
        outputString.append("type <view market> to browse available food trucks");
        return outputString.toString();
    }

    private String marketSceneOutputGenerator(String inputFeedback) {
        HashMap<String, String> foodTruckInfo = FoodTruckManager.getAllFoodTruckDescription();
        StringBuilder outputString = new StringBuilder("------------------------Market---------------------------");
        for (String field : foodTruckInfo.keySet()) {
            String content = foodTruckInfo.get(field);
            outputString.append("\n\nTruckName:").append(field).append("\n").append(content);
        }
        if (ms.unknownFoodTruckError) {
            outputString.append("\n\n Unknown Food Truck name entered, please check your spelling before entering");
        }
        outputString.append("\ntype <select> and choose a truck");
        return outputString.toString();
    }

    private String foodTruckSceneOutputGenerator(String inputFeedback) {
        return fts.foodTruckName + "\n" + "rating : " + FoodTruckManager.getRating(fts.foodTruckName) + "\n" +
                FoodTruckManager.getMenu(fts.foodTruckName) + "\n----------------Cart---------------" ; //+ fts.printCart();
    }

    private String orderSceneOutputGenerator(String inputFeedback){
        return os.OrderID + "\n" + os.printOrder();

    }

    private String registerSceneOutputGenerator(String inputFeedback) {
        if (inputFeedback.equals("username received") || inputFeedback.equals("password received") ||
                inputFeedback.equals("user_type received") || inputFeedback.equals("nickname received") ||
                inputFeedback.equals("phone_number received") || inputFeedback.equals("start register") ||
                inputFeedback.equals("")) {
            return registerGeneralInfo(rs);
        } else if (inputFeedback.equals("confirm received")) {
            return "registered success";
        } else {
            return "register failure"; //TODO:
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
        String password = scene.fields.get("password");
        String p = "*".repeat(password.length());
        return "Username: " + scene.fields.get("username") + "\n" +
                "Password: " + p + "\n" +
                "Nickname: " + scene.fields.get("nickname") + "\n" +
                "Phone number: " + scene.fields.get("phone_number");
    }



    public static String loginGeneralInfo(LoginScene scene) {
        String password = scene.fields.get("password");
        String p = "*".repeat(password.length());
        return "Username: " + scene.fields.get("username") + "\n" +
                "Password: " + p + "\n";
    }
}

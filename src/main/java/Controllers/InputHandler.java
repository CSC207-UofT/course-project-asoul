package Controllers;

import Exceptions.*;

import java.util.Arrays;
import java.util.List;

public class InputHandler {
    private final LoginScene ls;
    private final MarketScene ms;
    private final UserInformationScene usc;
    private final FoodTruckScene fts;
    //  private boolean first = true;
    private final RegisterScene rs;
    private final OrderScene os;

    public InputHandler(LoginScene ls, MarketScene ms, UserInformationScene usc, FoodTruckScene fts,
                        RegisterScene rs, OrderScene os) {
        this.fts = fts;
        this.ls = ls;
        this.ms = ms;
        this.usc = usc;
        this.rs = rs;
        this.os = os;
    }

    public String[] parsingInput(String text) {
        return text.split(" ");
    }

    public void commandChecker(Scene scene, String command) throws UnknownCommandException {
        if (!(scene.commandSet.contains(command))) {
            throw new UnknownCommandException();
        }
    }

    public String handlingGeneralInput(String input){
        String[] arr = parsingInput(input);
        if (arr[0].equals("exit")) {
            Scene.exit = true;
            return "exit";
        }
        try {
            commandChecker(Scene.getActiveScene(), arr[0]);
        }catch (UnknownCommandException e){
            return "";
        }try {
            return switch (Scene.activeScene.getClass().getName()) {
                case "Controllers.LoginScene" -> logInSceneInputHandler(arr);
                case "Controllers.UserInformationScene" -> userInformationSceneHandler(arr);
                case "Controllers.MarketScene" -> marketSceneHandler(arr);
                case "Controllers.FoodTruckScene" -> foodTruckSceneHandler(arr);
                case "Controllers.RegisterScene" -> registerSceneHandler(arr);
                default -> //TODO: add more cases.
                        "No active scene";
            };
        }catch (IncorrectCredentialsException e){
            return "";
        }
        //TODO:

    }

    private String registerSceneHandler(String[] arr) {
        switch (arr[0]) {
            case "U":
                rs.fillInField("username", arr[1]);
                return "username received";

            case "P":
                rs.fillInField("password", arr[1]);
                return "password received";
            case "T":
                rs.fillInField("user_type", arr[1]);
                return "user_type received";

            case "N":
                rs.fillInField("nickname", arr[1]);
                return "nickname received";

            case "PN":
                rs.fillInField("phone_number", arr[1]);
                return "phone_number received";

            case "confirm":
                rs.registerUser();
                return "confirm received";
            case "login":
                rs.clearFields();
                rs.switchScene("Login");
                return "";
            default:
                return "input invalid"; //TODO
        }
    }

    private String foodTruckSceneHandler(String[] arr){
        int quantity = 0;
        int foodId = 0;
        switch (arr[0]) {
            case "select_food" -> {
                fts.selectFood(foodId, quantity);
                return "added to basket";
            }
            case "remove_food" -> {
                fts.removeFood(foodId, quantity);
                return "removed from basket";
            }
            case "check_out" -> {
                Scene.setActiveScene(os);
                return "check out page";
            }
            case "back" -> {
                Scene.setActiveScene(ms);
                return "";
            }
        }
        return "";
    }

    public String logInSceneInputHandler(String[] arr) throws IncorrectCredentialsException {
        switch (arr[0]) {
            case "register":
                ls.switchScene(rs);
                ls.clearFields();
                return "";
            case "U":
                ls.fillInField("username", arr[1]);
                return "username received";
            case "P":
                ls.fillInField("password", arr[1]);
                return "password received";
            case "confirm":
                ls.userLogin();
                return "confirm received";
            default:
                return "";
        }

    }

    private String orderSceneHandler(String[] arr) throws UnknownCommandException {
        List<String> commands = Arrays.asList("complete_order", "rate_order", "back");
        if (!(commands.contains(arr[0]))) {
            throw new UnknownCommandException();
        }
        return "";
    }

    private String marketSceneHandler(String[] arr) {
        ms.refreshOutputState();
        if (arr[0].equals("view_user_info")) {
            ms.switchScene(Scene.allScenes.get("UserInformation"));
        } else if (arr[0].equals("select")) {
            try {
                ms.viewFoodTruck(arr[1]);
            } catch (UnknownFoodTruckException e) {
                ms.unknownFoodTruckError = true;
            }
        }  else{
            //TODO: Throws unknown command error
        }
        return ""; // Temporary fix
    }

    public String userInformationSceneHandler(String[] arr) {
        usc.refreshOutputState();
        switch (arr[0]) {
            case "sign_out":
                usc.switchScene(Scene.allScenes.get("Login"));
                break;
            case "change_nickname":
                usc.changeNickname(arr[1]);
                break;
            case "change_phone_number":
                usc.changePhoneNumber(arr[1]);
                break;
            case "add_fund":
                try {
                    usc.addFund(arr[1]);
                } catch (NumberFormatException e) {
                    usc.invalidFundError = true;
                }
                break;
            case "O":
                usc.fillInField("old_password", arr[1]);
                break;
            case "N":
                usc.fillInField("new_password", arr[1]);
                break;
            case "C":
                usc.fillInField("confirm_password", arr[1]);
                break;
            case "change_password":
                usc.changingPassword = true;
                break;
            case "back":
                usc.changingPassword = false;
                break;
            case "view_market":
                usc.viewMarket();
                break;
            case "confirm":
                if (usc.changingPassword) {
                    try {
                        usc.changePassword();
                    } catch (UnmatchedPasswordException e) {
                        usc.unmatchedPasswordError = true;
                    }
                }
                break;
        }
        return ""; // Temporary fix
    }
}

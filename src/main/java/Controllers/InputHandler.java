package Controllers;

import Exceptions.IncorrectCredentialsException;
import Exceptions.InvalidInput;
import Exceptions.UnknownCommandException;

public class InputHandler {
    private final LoginScene ls;
    private final MarketScene ms;
    private final UserInformationScene usc;
    private final FoodTruckScene fts;
//  private boolean first = true;
    private final RegisterScene rs;

    public InputHandler(LoginScene ls, MarketScene ms, UserInformationScene usc, FoodTruckScene fts,
                        RegisterScene rs) {
        this.fts = fts;
        this.ls = ls;
        this.ms = ms;
        this.usc = usc;
        this.rs = rs;
    }

    public String[] parsingInput(String text) {
        return text.split(" ");
    }

    public void commandChecker(Scene scene, String command) throws UnknownCommandException {
        if (!(scene.commandSet.contains(command))) {
            throw new UnknownCommandException();
        }
    }

    public String handlingGeneralInput(String input) throws IncorrectCredentialsException, UnknownCommandException {
        String[] arr = parsingInput(input);
        if (arr[0].equals("exit")) {
            Scene.exit = true;
            return "exit";
        }
        commandChecker(Scene.getActiveScene(), arr[0]);
        return switch (Scene.activeScene.getClass().getName()) {
            case "Controllers.LoginScene" -> logInSceneInputHandler(arr);
            case "Controllers.UserInformationScene" -> userInformationSceneHandler(arr);
            case "Controllers.MarketScene" -> marketSceneHandler(arr);
            case "Controllers.FoodTruckScene" -> foodTruckSceneHandler(arr);
            case "Controllers.RegisterScene" -> registerSceneHandler(arr);
            default -> //TODO: add more cases.
                    "No active scene";
        };
        //TODO:

    }

    private String registerSceneHandler(String[] arr) {
        switch (arr[0]) {
            case "U": rs.fillInField("username", arr[1]);
                return "username received";

            case "P": rs.fillInField("password", arr[1]);
                return "password received";
            case "T": rs.fillInField("user_type", arr[1]);
                return "user_type received";

            case "N": rs.fillInField("nickname", arr[1]);
                return "nickname received";

            case "PN": rs.fillInField("phone_number", arr[1]);
                return "phone_number received";

            case "confirm": rs.registerUser();
                return "confirm received";
            case "register": rs.registerUser();
                return "start register";
            default:
                return "input invalid"; //TODO
        }
    }

    private String foodTruckSceneHandler(String[] arr) {
        return  "";
    }

    private String marketSceneHandler(String[] arr) {
        return  "";
    }

    private String userInformationSceneHandler(String[] arr) {
        return  "";
    }



    public String logInSceneInputHandler(String[] arr) throws UnknownCommandException, IncorrectCredentialsException {
////        commandChecker(ls, arr[0]); TODO
////        if (first) {
////            OutputConstructor.programStart();
////        }
//
//        switch (arr[0]) {
//            case "register":
//                registerCommand(arr);
//            break;
//            case "help":
//                OutputConstructor.printCurrSceneCommands(ls);
//            break;
//            case "login": {
//                do {
//                    loginCommand(arr);
//                } while ((arr[0].equals("confirm"))); //TODO: may introduce bugs
//                ls.switchScene(usc);
//            }
//        }
        return  "";

    }

    public void registerCommand(String[] arr) {
//        OutputConstructor.registerGeneralInfo(ls);
//        switch (arr[0]) {
//            case "U": ls.fillInField("username", arr[1]);
//            break;
//            case "P": ls.fillInField("password", arr[1]);
//            break;
//            case "T": ls.fillInField("user_type", arr[1]);
//            break;
//            case "N": ls.fillInField("nickname", arr[1]);
//            break;
//            case "PN": ls.fillInField("phone_number", arr[1]);
//            break;
//            case "confirm": ls.registerUser();
//            break;
//            default:
//                OutputConstructor.askRegisterInto();

        }


    public void loginCommand(String[] arr) throws IncorrectCredentialsException {
        switch (arr[0]) {
            case "U": ls.fillInField("username", arr[1]);
            break;
            case "P": ls.fillInField("password", arr[1]);
            break;
            case "confirm": ls.userLogin();
            break;
            default: OutputConstructor.askLoginInfo();
        }
    }

//    public void call(String input, InputHandler handler) throws InvalidInput, IncorrectCredentialsException, UnknownCommandException {
//        String[] arr = handler.parsingInput(input);
//        handler.handlingGeneralInput(arr);
//    }
}

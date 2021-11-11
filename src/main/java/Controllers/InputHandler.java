package Controllers;

import Exceptions.IncorrectCredentialsException;
import Exceptions.InvalidInput;
import Exceptions.UnknownCommandException;

public class InputHandler {
    private final LoginScene ls;
    private final MarketScene ms;
    private final UserInformationScene usc;
    private final FoodTruckScene fts;
    private boolean first = true;
    private final RegisterScene rs;

    public InputHandler(LoginScene ls, MarketScene ms, UserInformationScene usc, FoodTruckScene fts,
                        RegisterScene rs) {
        this.fts = fts;
        this.ls = ls;
        this.ms = ms;
        this.usc = usc;
        this.rs = rs;
    }

    public String[] parsingInput(String text) throws InvalidInput {
        return text.split(" ");
    }


    public void handlingGeneralInput(String[] arr) throws IncorrectCredentialsException, UnknownCommandException {

        if (arr[0].equals("exit")) {
            Scene.exit = true;
//        } else if (arr.length != 2) {
//            throw new InvalidInput();
        }
        switch (Scene.activeScene.getClass().getName()) {
            case "Controllers.LoginScene":
                logInSceneInputHandler(arr);
                break;
            case "Controllers.UserInformationScene":
                userInformationSceneHandler(arr);
                break;
            case "Controllers.MarketScene":
                marketSceneHandler(arr);
                break;
            case "Controllers.FoodTruckScene":
                foodTruckSceneHandler(arr);
            case "Controllers.RegisterScene":
                registerSceneHandler(arr);
        }


    }

    private void registerSceneHandler(String[] arr) {
        System.out.println(1);
        OutputConstructor.registerGeneralInfo(this.rs);
        switch (arr[0]) {
            case "U": rs.fillInField("username", arr[1]);
                break;
            case "P": rs.fillInField("password", arr[1]);
                break;
            case "T": rs.fillInField("user_type", arr[1]);
                break;
            case "N": rs.fillInField("nickname", arr[1]);
                break;
            case "PN": rs.fillInField("phone_number", arr[1]);
                break;
            case "confirm": rs.registerUser();
                break;
            default:
                OutputConstructor.askRegisterInto();

        }
        rs.getfield();

    }

    private void foodTruckSceneHandler(String[] arr) {
    }

    private void marketSceneHandler(String[] arr) {
    }

    private void userInformationSceneHandler(String[] arr) {

    }

//    public void commandChecker(Scene scene, String command) throws UnknownCommandException {
//        if (!(scene.commandSet.containsKey(command))) {
//            throw new UnknownCommandException();
//        }
//    } //TODO: set keys

    public void logInSceneInputHandler(String[] arr) throws UnknownCommandException, IncorrectCredentialsException {
//        commandChecker(ls, arr[0]); TODO
//        if (first) {
//            OutputConstructor.programStart();
//        }

        switch (arr[0]) {
            case "register":
                registerCommand(arr);
            break;
            case "help":
                OutputConstructor.printCurrSceneCommands(ls);
            break;
            case "login": {
                do {
                    loginCommand(arr);
                } while ((arr[0].equals("confirm"))); //TODO: may introduce bugs
                ls.switchScene(usc);
            }
        }

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

    public void call(String input, InputHandler handler) throws InvalidInput, IncorrectCredentialsException, UnknownCommandException {
        String[] arr = handler.parsingInput(input);
        handler.handlingGeneralInput(arr);
    }
}

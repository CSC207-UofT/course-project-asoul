package Controllers;

import Exceptions.IncorrectCredentialsException;
import Exceptions.InvalidInput;
import Exceptions.UnknownCommandException;

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

    private String foodTruckSceneHandler(String[] arr) throws UnknownCommandException {
        List<String> commands = Arrays.asList("select_food", "remove_food", "check_out", "back");
        int quantity = 0;
        int foodId = 0;
        if (!commands.contains(arr[0])){
            throw new UnknownCommandException();
        }
        if (arr[0].equals(commands.get(0)) || arr[0].equals(commands.get(1))){
            try {
                quantity = Integer.parseInt(arr[2]);
                foodId = Integer.parseInt(arr[1]);
            }
            catch (NumberFormatException e){
                throw new UnknownCommandException();
            }
        }
        if (!(fts.checkValidFood(foodId))){
            throw new UnknownCommandException();
        }
        switch (arr[0]) {
            case "select_food" -> {
                fts.selectFood(foodId, quantity);
                return "added to basket";
            }
            case "remove_food" -> {
                fts.removeFood(foodId, quantity);
                return "removed from basket";
            }
    //        case "check_out" -> {
    //            if (fts.chekOut(arr[1]) > -1){
    //                Scene.setActiveScene(os);
    //                return "";
    //            }
    //            return "Unable to pay";
    //        }
            case "back" -> {
                Scene.setActiveScene(ms);
                return "";
            }
            default -> {
                throw new UnknownCommandException();
            }
        }
    }


    private String orderSceneHandler(String[] arr) throws UnknownCommandException {
        List<String> commands = Arrays.asList("complete_order", "rate_order", "back");
        if (!(commands.contains(arr[0]))){
            throw new UnknownCommandException();
        }
        switch (arr[0]) {
            case "complete_order" -> {

            }
            case "rate_order" -> {
                double rating = Double.parseDouble(arr[1]);
                if (0 <= rating & rating <= 10) {
                    os.rateOrder(rating);
                }
            }
            case "back" -> {
                Scene.setActiveScene(fts);
                return "";
            }
            default -> {
                throw new UnknownCommandException();
            }
        }
        return "";
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

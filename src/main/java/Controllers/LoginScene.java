package Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.IncorrectCredentialsException;
import Use_case.UserManager;

public class LoginScene extends Scene {
    // Output States
//    private boolean unknownCommandError;
//    private boolean incorrectCredentialError;
//    private boolean help;
//    private boolean register;
//    private boolean successRegistration;
    private final HashMap<String, String> displayMap;
    private final UserManager userManager = new UserManager();



    public LoginScene() {
        super("Login");
        this.fields.put("username", "");
        this.fields.put("password", "");
        this.fields.put("user_type", "");
        this.fields.put("nickname", "");
        this.fields.put("phone_number", "");
        this.displayMap = new HashMap<>();
        this.displayMap.put("username", "Username");
        this.displayMap.put("password", "Password");
        this.displayMap.put("nickname", "Nickname");
        this.displayMap.put("user_type", "Entities.User Type");
        this.displayMap.put("phone_number", "Phone Number");
        this.commandSet.add("U");
        this.commandSet.add("P");
        this.commandSet.add("confirm");
//        this.unknownCommandError = false;
//        this.incorrectCredentialError = false;
//        this.help = false;
//        this.register = false;
//        this.successRegistration = false;
    }

//    @Override
//    public void handleInput(String input) {
//        // TODO: Use commands to access methods in the map instead of hardcoding
//        this.refreshOutputState();
//        String[] text = input.split(" ");
//        switch (text[0]) {
//            case "U":
//                this.fillInField("username", text[1]);
//                break;
//            case "P":
//                this.fillInField("password", text[1]);
//                break;
//            case "T":
//                this.fillInField("user_type", text[1]);
//                break;
//            case "N":
//                this.fillInField("nickname", text[1]);
//                break;
//            case "PN":
//                this.fillInField("phone_number", text[1]);
//                break;
//            case "confirm":
//                if (!this.register) {
//                    try {
//                        this.userLogin();
//                    } catch (IncorrectCredentialsException e) {
//                        this.incorrectCredentialError = true;
//                    }
//                } else {
//                    this.registerUser();
//                }
//                break;
//            case "register":
//                this.register = true;
//                this.clearFields();
//                break;
//            case "login":
//                this.register = false;
//                this.clearFields();
//                break;
//            case "help":
//                this.help = true;
//                break;
//            case "hide":
//                this.help = false;
//                break;
//            case "exit":
//                Scene.exit = true;
//                break;
//            default:
//                // TODO: UnknownCommandException handling
//                this.unknownCommandError = true;
//                break;
//        }
//    }
//
//    @Override
//    public String constructOutputString() {
//        StringBuilder outputString = new StringBuilder();
//        if (this.incorrectCredentialError) {
//            outputString.append("Incorrect credentials entered, please check your spellings before reentering");
//        } else if (!this.register) {
//            outputString.append("Please login with your username and password, enter 'help' to see all available commands");
//        } else if (!this.successRegistration) {
//            outputString.append("Please enter the information for your new account");
//        } else {
//            outputString.append("Successfully created new account!");
//        }
//        ArrayList<String> requiredFields = new ArrayList<>();
//        if (this.register) {
//            requiredFields.addAll(this.fields.keySet());
//        } else {
//            requiredFields.add("username");
//            requiredFields.add("password");
//        }
//        for (String field : requiredFields) {
//            String content = this.fields.get(field);
//            field = displayMap.get(field);
//            outputString.append("\n").append(field).append(": ").append(content);
//        }
//        if (this.help) {
//            outputString.append("\n\n").append("All commands:\n").append("help -> View all commands on this page\n").
//                    append("hide -> Hide helping commands\n").append("U + [Space] + [your username] -> " +
//                            "Enter your username\n").append("P + [Space] + [your password] -> Enter your password\n").
//                    append("register -> Start account registration\n").append("login -> Start logging in\n").
//                    append("confirm -> Login / Register new account with the information you entered\n").append("" +
//                            "T + [Space] + [Entities.User Type] -> Enter the user type for your new account\n").append("" +
//                            "N + [Space] + [Nickname] -> Enter the nickname for your new account\n").append("" +
//                            "PN + [Space] + [Phone Number] -> Enter the phone number for your new account");
//        }
//        return outputString.toString();
//    }

//    private void refreshOutputState() { // reset output flags
//        this.incorrectCredentialError = false;
//        this.unknownCommandError = false;
//        this.successRegistration = false;
//    }

    public String userLogin() throws IncorrectCredentialsException { // attempt to login
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        userManager.login(username, password);
        return "login success";
    }


    @Override
    protected void switchScene(Scene scene) {
        super.switchScene(scene);
        this.clearFields();
//        this.refreshOutputState();
    }
}

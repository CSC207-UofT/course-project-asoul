package default_scene_implementation;

import controllers.Scene;
import exceptions.UnknownCommandException;
import singleton_pattern.Singleton;
import java.util.ArrayList;
import use_case.UserManager;

class RegisterScene extends Scene {
    private static final RegisterScene rs = new RegisterScene();

    private RegisterScene() {
        super();
        this.fields.put("Username", "");
        this.fields.put("Password", "");
        this.fields.put("Nickname", "");
        this.fields.put("Phone Number", "");

        this.setHelpMessage("\n\nAll commands:" +
                "help -> View all commands on this page\n" +
                "U + [Space] + [your username] -> Enter your username\n" +
                "P + [Space] + [your password] -> Enter your password\n" +
                "N + [Space] + [Nickname] -> Enter the nickname for your new account\n" +
                "PN + [Space] + [Phone Number] -> Enter the phone number for your new account\n" +
                "confirm -> Create a new user with the information you entered\n" +
                "login -> Start logging in\n");
    }

    public static Singleton getInstance(){
        return rs;
    }

    public void handleInputString(String input){
        String[] arr = input.split(" ");
        switch (arr[0]) {
            case "U":
                fillInField("Username", arr[1]);
                break;
            case "P":
                fillInField("Password", arr[1]);
                break;
            case "N":
                fillInField("Nickname", arr[1]);
                break;
            case "PN":
                fillInField("Phone Number", arr[1]);
                break;
            case "help":
                this.state.append(getHelpMessage()).append("\n");
                break;
            case "confirm":
                registerUser();
                break;
            case "login":
                switchScene((Scene)LoginScene.getInstance());
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    public String constructOutputString() {
        StringBuilder outputString = new StringBuilder();
        ArrayList<String> requiredFields = new ArrayList<>(this.fields.keySet());
        for (String field : requiredFields) {
            String content = fields.get(field);
            outputString.append("\n").append(field).append(": ").append(content);
        }
        outputString.append("\n");
        outputString.append(state);
        return outputString.toString();
    }

    public void registerUser() { // Create new users
        String username = this.fields.get("Username");
        String password = this.fields.get("Password");
        String nickname = this.fields.get("Nickname");
        String phoneNumber = this.fields.get("Phone Number");
        if(UserManager.createUser(username, password, nickname, phoneNumber)){
            this.clearFields();
            this.state.append("Successfully registered new user, you can now proceed to log in!\n");
        }else{
            this.state.append("User with the given username has already been registered!\n");
        }
    }
}
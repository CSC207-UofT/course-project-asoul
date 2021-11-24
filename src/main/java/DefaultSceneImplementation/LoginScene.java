package DefaultSceneImplementation;

import java.util.HashMap;

import Controllers.Scene;
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

    public LoginScene() {
        super("Login");
        this.fields.put("username", "");
        this.fields.put("password", "");
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
        this.commandSet.add("register");
        this.commandSet.add("exit");
//        this.unknownCommandError = false;
//        this.incorrectCredentialError = false;
//        this.help = false;
//        this.register = false;
//        this.successRegistration = false;
    }

    public String userLogin() throws IncorrectCredentialsException { // attempt to login
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        UserManager.login(username, password);
        this.switchScene("UserInformation");
        UserInformationScene s = (UserInformationScene) Scene.allScenes.get("UserInformation");
        s.setUserInfo(username);
        return "login success";
    }


    @Override
    protected void switchScene(Scene scene) {
        super.switchScene(scene);
        this.clearFields();
//        this.refreshOutputState();
    }

    @Override
    protected void switchScene(String name) {
        super.switchScene(name);
        this.clearFields();
//        this.refreshOutputState();
    }
}

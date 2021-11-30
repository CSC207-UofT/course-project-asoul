package default_scene_implementation;


import controllers.Scene;
import exceptions.IncorrectCredentialsException;
import singleton_pattern.Singleton;
import use_case.UserManager;

class LoginScene extends Scene {
    private final static LoginScene ls = new LoginScene();
    // Output States
//    private boolean unknownCommandError;
//    private boolean incorrectCredentialError;
//    private boolean help;
//    private boolean register;
//    private boolean successRegistration;
    private final HashMap<String, String> displayMap;

    private LoginScene() {
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

    public static Singleton getInstance(){
        return ls;
    }

    public String userLogin() throws IncorrectCredentialsException { // attempt to login
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String key = UserManager.login(username, password);
        UserInformationScene s = (UserInformationScene)UserInformationScene.getInstance();
        s.setUserInfo(username, key); //set all scenes
        this.switchScene(s);
        UserInfoEditScene infoEditScene = (UserInfoEditScene)UserInfoEditScene.getInstance();
        infoEditScene.setUserInfo(username, key);
        FoodTruckEditScene truckEditScene = (FoodTruckEditScene) FoodTruckEditScene.getInstance();
        truckEditScene.setUserInfo(username, key);
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

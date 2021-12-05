package default_scene_implementation;


import controllers.Scene;
import exceptions.IncorrectCredentialsException;
import exceptions.UnauthorizedAccessException;
import exceptions.UnknownCommandException;
import singleton_pattern.Singleton;
import use_case.UserManager;

class LoginScene extends Scene {
    private final static LoginScene ls = new LoginScene();

    private LoginScene() {
        super();
        this.fields.put("username", "");
        this.fields.put("password", "");
        this.setHelpMessage("\n\nAll commands:\n" +
                "help -> View all commands on this page\n" +
                "U + [Space] + [your username] -> Enter your username\n" +
                "P + [Space] + [your password] -> Enter your password\n" +
                "confirm -> Login with the information you entered\n");
    }

    public static Singleton getInstance(){
        return ls;
    }

    public void handleInputString(String input){
        String[] text = input.split(" ");
        switch (text[0]) {
            case "U":
                this.fillInField("username", text[1]);
                break;
            case "P":
                this.fillInField("password", text[1]);
                break;
            case "confirm":
                try {
                    this.userLogin();
                } catch (IncorrectCredentialsException | UnauthorizedAccessException e) {
                    this.state.append(e.getMessage()).append("\n");
                }
                break;
            case "register":
                this.switchScene((Scene)RegisterScene.getInstance());
                break;
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "exit":
                Scene.exit = true;
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    public String constructOutputString() {
        StringBuilder outputString = new StringBuilder();
        String enteredPassword = "*".repeat(this.fields.get("password").length());
        String enteredUsername = this.fields.get("username");
        outputString.append("Username: ").append(enteredUsername).append("\n");
        outputString.append("Password: ").append(enteredPassword).append("\n");
        outputString.append(this.state);
        return outputString.toString();
    }

    public void userLogin() throws IncorrectCredentialsException, UnauthorizedAccessException { // attempt to login
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String key = UserManager.login(username, password);
        UserInformationScene s = (UserInformationScene)UserInformationScene.getInstance();
        s.setUserInfo(username, key); //set all scenes
        this.switchScene(s);
        // Scene setup
        UserInfoEditScene infoEditScene = (UserInfoEditScene)UserInfoEditScene.getInstance();
        infoEditScene.setUserInfo(username, key);
        FoodTruckEditScene truckEditScene = (FoodTruckEditScene) FoodTruckEditScene.getInstance();
        truckEditScene.setUserInfo(username, key);
        FoodTruckScene ftc = (FoodTruckScene) FoodTruckScene.getInstance();
        ftc.setUserInfo(username, key);
        MenuEditScene menuEditScene = (MenuEditScene) MenuEditScene.getInstance();
        menuEditScene.setUserInfo(username);
        OrderScene os = (OrderScene)OrderScene.getInstance();
        os.setUserInfo(username, key);
        OrderListScene ols = (OrderListScene)OrderListScene.getInstance();
        ols.setOrderListInfo(username, key);
    }
}

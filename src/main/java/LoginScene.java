import java.security.KeyException;
import java.util.HashMap;
import java.util.concurrent.Callable;

import Exceptions.IncorrectCredentialsException;
import Exceptions.UnknownCommandException;

public class LoginScene extends Scene{
    // Output States
    private boolean unknownCommandError;
    private boolean incorrectCredentialError;
    private boolean help;
    private boolean register;

    public LoginScene(){
        super("Login");
        this.fields.put("username", "");
        this.fields.put("password", "");
        this.fields.put("user_type", "");
        this.fields.put("nickname", "");
        this.fields.put("phone_number", "");
        this.unknownCommandError = false;
        this.incorrectCredentialError = false;
        this.help = false;
        this.register = false;
    }

    @Override
    public void handleInput(String input){
        // TODO: Use commands to access methods in the map instead of hardcoding
        this.refreshErrorState();
        String[] text = input.split(" ");
        switch (text[0]) {
            case "U":
                this.fillInField("username", text[1]);
                break;
            case "P":
                this.fillInField("password", text[1]);
                break;
            case "T":
                this.fillInField("user_type", text[1]);
                break;
            case "N":
                this.fillInField("nickname", text[1]);
                break;
            case "PN":
                this.fillInField("phone_number", text[1]);
                break;
            case "confirm":
                if (!this.register) {
                    try {
                        this.userLogin();
                    } catch (IncorrectCredentialsException e) {
                        this.incorrectCredentialError = true;
                    }
                } else {
                    this.registerUser();
                }
                break;
            case "register":
                this.register = true;
                break;
            case "login":
                this.register = false;
                break;
            default:
                // TODO: UnknownCommandException handling
                this.unknownCommandError = true;
                break;
        }
    }

    @Override
    public String constructOutputString() {
        StringBuilder outputString = new StringBuilder("");
        if(this.incorrectCredentialError){
            outputString.append("Incorrect credentials entered, please check your spellings before reentering");
        }else{
            outputString.append("Please login with your username and password");
        }
        for(String field: this.fields.keySet()){
            String content = this.fields.get(field);
            outputString.append("\n").append(field).append(": ").append(content);
        }
        return outputString.toString();
    }

    private void refreshErrorState(){
        this.incorrectCredentialError = false;
        this.unknownCommandError = false;
    }

    private void userLogin() throws IncorrectCredentialsException{
        String username = this.fields.get("username");
        String[] expectedInfo = UserManager.getUserCredentials(username); // expectInfo = (Password of the user, User type)
        if(expectedInfo[0].equals(this.fields.get("password"))){
            UserInformationScene nextScene = (UserInformationScene) Scene.allScenes.get("UserInformation"); // Forward information to next scene
            Scene.activeScene = nextScene;
            if(expectedInfo[1].equals("seller")){
                nextScene.setUserInfo("seller", username);
            }else{
                nextScene.setUserInfo("customer", username);
            }
        }else{
            throw new IncorrectCredentialsException();
        }
    }

    private void registerUser(){
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String userType = this.fields.get("user_type");
        String nickname = this.fields.get("nickname");
        String phoneNumber = this.fields.get("phone_number");
        if(userType.equals("seller")){
            Scene.sellerManager.creatSeller(username, password, nickname, phoneNumber); // TODO: User creation exception handling
        }else if(userType.equals("customer")){
            Scene.customerManager.creatCustomer(username, password, nickname, phoneNumber);
        }else{
            // TODO: Throw exception
        }
    }
}

package Controllers;

import Exceptions.IncorrectOldPasswordException;
import Exceptions.UnmatchedPasswordException;
import Use_case.UserManager;
import Utilities.State;

import java.util.HashMap;

public class UserInformationScene extends Scene {
    private String username;

    public UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.fields.put("old_password", "");
        this.fields.put("new_password", "");
        this.fields.put("confirm_password", "");
    }

    private HashMap<String, State> declareStates(){
        return null; // TODO: Define states of this scene here
    }

    private String changePhoneNumber(String phoneNumber) {
        UserManager.setPhoneNumber(this.username, phoneNumber);
        return "Phone number changed successfully!";
    }

    private String changeNickname(String nickname) {
        UserManager.setNickname(this.username, nickname);
        return "Nickname changed successfully!";
    }

    private String changePassword(String oldPassword, String newPassword) throws UnmatchedPasswordException {
        try {
            UserManager.setPassword(this.username, newPassword, oldPassword);
            return "Successfully changed password!";
        } catch (IncorrectOldPasswordException e) {
            return "Incorrect old password entered!";
        }
    }

    private String viewMarket() {
        this.switchScene("Market");
        MarketScene scene = (MarketScene) Scene.allScenes.get("Market");
        scene.setUsername(this.username);
        return "";
    }

    private void addFund(String fund) {
        UserManager.addMoney(this.username, Integer.parseInt(fund));
    }

    public void setUserInfo(String username) {
        this.username = username;
    }
}

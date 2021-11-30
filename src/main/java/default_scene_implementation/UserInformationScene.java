package default_scene_implementation;

import controllers.Scene;
import exceptions.IncorrectOldPasswordException;
import exceptions.UnmatchedPasswordException;
import singleton_pattern.Singleton;
import use_case.UserManager;

import java.util.HashMap;

class UserInformationScene extends Scene {
    private final static UserInformationScene us = new UserInformationScene();
    public String username;

    // Output State
    public boolean invalidFundError;
    public boolean changeNicknameSuccess;
    public boolean incorrectOldPasswordError;
    public boolean unmatchedPasswordError;
    public boolean changePasswordSuccess;
    public boolean changingPassword;
    public final HashMap<String, String> displayMap;

    private UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
        this.incorrectOldPasswordError = false;
        this.unmatchedPasswordError = false;
        this.changePasswordSuccess = false;
        this.changingPassword = false;
        this.fields.put("old_password", "");
        this.fields.put("new_password", "");
        this.fields.put("confirm_password", "");
        this.displayMap = new HashMap<>();
        this.displayMap.put("old_password", "Old Password");
        this.displayMap.put("new_password", "New Password");
        this.displayMap.put("confirm_password", "Confirm Password");
        this.commandSet.add("sign_out");
        this.commandSet.add("view_market");
        this.commandSet.add("change_password");
        this.commandSet.add("change_nickname");
        this.commandSet.add("change_phone_number");
        this.commandSet.add("O");
        this.commandSet.add("N");
        this.commandSet.add("C");
        this.commandSet.add("confirm");
        this.commandSet.add("back");
        this.commandSet.add("add_fund");
    }

    public static Singleton getInstance(){
        return us;
    }

    void refreshOutputState() { // reset output flags
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
        this.incorrectOldPasswordError = false;
        this.unmatchedPasswordError = false;
        this.changePasswordSuccess = false;
    }

    public void changePhoneNumber(String phoneNumber) {
        UserManager.setPhoneNumber(this.username, phoneNumber);
    }

    public void changeNickname(String nickname) {
        UserManager.setNickname(this.username, nickname);
    }

    void changePassword() throws UnmatchedPasswordException {
        String oldPassword = this.fields.get("old_password");
        String newPassword = this.fields.get("new_password");
        String confirmPassword = this.fields.get("confirm_password");
        try {
            if (!confirmPassword.equals(newPassword)) {
                throw new UnmatchedPasswordException();
            }
            UserManager.setPassword(this.username, newPassword, oldPassword);
            this.changePasswordSuccess = true;
            this.changingPassword = false;
        } catch (IncorrectOldPasswordException e) {
            this.incorrectOldPasswordError = true;
        }
    }

    public void viewMarket() {
        this.switchScene("Market");
        MarketScene scene = (MarketScene) Scene.allScenes.get("Market");
        scene.setUsername(this.username);
    }

    public void addFund(String fund) {
        UserManager.addMoney(this.username, Integer.parseInt(fund));
    }

    public void setUserInfo(String username) {
        this.username = username;
    }
}

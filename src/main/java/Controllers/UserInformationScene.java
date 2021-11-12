package Controllers;

import Exceptions.IncorrectOldPasswordException;
import Exceptions.UnmatchedPasswordException;

import java.util.HashMap;

public class UserInformationScene extends Scene {
    private String userType;
    private String username;
    private final HashMap<String, String> displayMap;
    // Output State
    private boolean invalidFundError;
    private boolean changeNicknameSuccess;
    private boolean incorrectOldPasswordError;
    private boolean unmatchedPasswordError;
    private boolean changePasswordSuccess;

    private boolean changingPassword;

    public UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.userType = "";
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
    }

    private void changeNickname(String nickname) {
        Scene.customerManager.setNickname(this.username, nickname);
        this.changeNicknameSuccess = true;
    }

    private void refreshOutputState() { // reset output flags
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
        this.incorrectOldPasswordError = false;
        this.unmatchedPasswordError = false;
        this.changePasswordSuccess = false;
    }

    private void changePassword() throws UnmatchedPasswordException {
        String oldPassword = this.fields.get("old_password");
        String newPassword = this.fields.get("new_password");
        String confirmPassword = this.fields.get("confirm_password");
        try {
            if (!confirmPassword.equals(newPassword)) {
                throw new UnmatchedPasswordException();
            }
            customerManager.setPassword(this.username, newPassword, oldPassword);
            this.changePasswordSuccess = true;
            this.changingPassword = false;
        } catch (IncorrectOldPasswordException e) {
            this.incorrectOldPasswordError = true;
        }
    }

    private void viewMarket() {
        this.switchScene("Market");
        MarketScene scene = (MarketScene) Scene.allScenes.get("Market");
        scene.setUsername(this.username);
    }

    private void addFund(String fund) {
        Scene.customerManager.addMoney(this.username, Integer.parseInt(fund));
    }

    public void setUserInfo(String userType, String username) {
        assert userType.equals("Entities.Customer") || userType.equals("Entities.Seller");
        this.userType = userType;
        this.username = username;
    }
}

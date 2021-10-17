import Exceptions.IncorrectCredentialsException;
import Exceptions.IncorrectOldPasswordException;
import Exceptions.UnmatchedPasswordException;

import java.util.HashMap;

public class UserInformationScene extends Scene{
    private String userType;
    private String username;
    private HashMap<String, String> displayMap;
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

    @Override
    public void handleInput(String input) {
        this.refreshOutputState();
        String[] text = input.split(" ");
        if(input.equals("sign out")){
            this.switchScene(Scene.allScenes.get("Login"));
        }else if(text[0].equals("change_nickname")){
            this.changeNickname(text[1]);
        }else if(text[0].equals("add_fund")){
            try {
                this.addFund(text[1]);
            }catch (NumberFormatException e){
                this.invalidFundError = true;
            }
        }else if(text[0].equals("O")){
            this.fillInField("old_password", text[1]);
        }else if(text[0].equals("N")){
            this.fillInField("new_password", text[1]);
        }else if(text[0].equals("C")){
            this.fillInField("confirm_password", text[1]);
        }else if(input.equals("change_password")){
            this.changingPassword = true;
        }else if(input.equals("back")){
            this.changingPassword = false;
        }else if(input.equals("view market")){
            this.viewMarket();
        }else if(input.equals("confirm")){
            if(this.changingPassword){
                try {
                    this.changePassword();
                }catch (UnmatchedPasswordException e){
                    this.unmatchedPasswordError = true;
                }
            }
        }else{
            // TODO: Throws unknown command error
        }
    }

    private void changeNickname(String nickname){
        Scene.customerManager.setNickname(this.username, nickname);
        this.changeNicknameSuccess = true;
    }

    private void refreshOutputState(){ // reset output flags
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
        this.incorrectOldPasswordError = false;
        this.unmatchedPasswordError = false;
        this.changePasswordSuccess = false;
    }

    private void changePassword() throws UnmatchedPasswordException{
        String oldPassword = this.fields.get("old_password");
        String newPassword = this.fields.get("new_password");
        String confirmPassword = this.fields.get("confirm_password");
        try {
            if(!confirmPassword.equals(newPassword)){
                throw new UnmatchedPasswordException();
            }
            customerManager.setPassword(this.username, newPassword, oldPassword);
            this.changePasswordSuccess = true;
            this.changingPassword = false;
        }catch (IncorrectOldPasswordException e){
            this.incorrectOldPasswordError = true;
        }
    }

    private void viewMarket(){
        this.switchScene("Market");
        MarketScene scene = (MarketScene) Scene.allScenes.get("Market");
        scene.setUsername(this.username);
    }

    private void addFund(String fund){
        Scene.customerManager.addMoney(this.username, Integer.parseInt(fund));
    }

    @Override
    public String constructOutputString() {
        HashMap<String, String> userInfo;
        StringBuilder outputString = new StringBuilder();
        if(!this.changingPassword) {
            if (this.userType.equals("seller")) {
                userInfo = Scene.sellerManager.getUserByAccountName(this.username);
            } else {
                userInfo = Scene.customerManager.getUserByAccountName(this.username);
            }
            outputString.append("------------------------User Information---------------------------");
            for (String field : userInfo.keySet()) {
                String content = userInfo.get(field);
                outputString.append("\n").append(field).append(": ").append(content);
            }
            if(this.invalidFundError){
                outputString.append("\n\n").append("Invalid Fund entered.");
            }else if(this.changeNicknameSuccess){
                outputString.append("\n\nSuccessfully changed nickname to: ").append(userInfo.get("nickname"));
            }
        }else{
            for(String field: this.fields.keySet()){
                String content = this.fields.get(field);
                field = displayMap.get(field);
                outputString.append("\n").append(field).append(": ").append(content);
            }
            if(this.unmatchedPasswordError){
                outputString.append("\nThe password you entered does not match!");
            }else if(this.incorrectOldPasswordError){
                outputString.append("\nThe old password is incorrect!");
            }
        }
        if(this.changePasswordSuccess){
            outputString.append("\n\n Successfully changed password!");
        }

        return outputString.toString();
    }

    public void setUserInfo(String userType, String username){
        assert userType.equals("Customer") || userType.equals("Seller");
        this.userType = userType;
        this.username = username;
    }
}

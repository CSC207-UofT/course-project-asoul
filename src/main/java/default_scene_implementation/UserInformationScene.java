package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.UserManager;

import java.util.HashMap;

class UserInformationScene extends Scene {
    private final static UserInformationScene us = new UserInformationScene();
    public String username;

    // Output State

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

    public void handleInputString(String input){
        String[] text = input.split(" ");
        switch (text[0]) {
            case "view_market":
                this.viewMarket();
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "exit":
                Scene.exit = true;
                break;
            case "sign_out":
                try{
                    UserManager.logOut(username, accessKey);
                    switchScene((LoginScene) LoginScene.getInstance());
                }catch (UnauthorizedAccessException e) {
                    state.append(e.getMessage()).append("\n");
                }
                break;
            case "change_user_info":
                changeUserInfo();
                break;
            case "change_truck_info":
                changeTruckInfo();
                break;
            case "add_money":
                try {
                    double money = Double.parseDouble(text[1]);
                    addFund(money);
                } catch (NumberFormatException | IncorrectArgumentException e) {
                    this.state.append((new IncorrectArgumentException()).getMessage());
                }
                break;
            case "withdraw_money":
                try {
                    double money = Double.parseDouble(text[1]);
                    withdrawFund(money);
                } catch (NumberFormatException | IncorrectArgumentException e) {
                    this.state.append((new IncorrectArgumentException()).getMessage());
                }
                break;
            case "view_order":
                // TODO
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    @Override
    public String constructOutputString(){
        return "Username: " + username + "\n" +
                "Nickname: " + nickname + "\n" +
                "Phone Number: " + phoneNum + "\n" +
                "Truck Name: " + truckName + "\n" +
                "Account Balance: " + accBalance + "\n" +
                "Buy order history: " + buyOrderHistory + "\n" +
                "Sell order history: " + sellOrderHistory + "\n" +
                this.state;
    }

    public void updateUserInfo(){
        try {
            this.truckName = UserManager.getTruckName(username, accessKey);
            this.nickname = UserManager.getNickname(username, accessKey);
            this.accBalance = UserManager.getBalance(username, accessKey);
            this.phoneNum = UserManager.getPhoneNumber(username, accessKey);
            this.sellOrderHistory = UserManager.getSellOrderHistory(username, accessKey).toString();
            this.buyOrderHistory = UserManager.getBuyOrderHistory(username, accessKey).toString();
        }catch(UnauthorizedAccessException e){
            this.state.append(e.getMessage());
        }
    }

    public void viewMarket() {
        this.switchScene("Market");
        MarketScene scene = (MarketScene) Scene.allScenes.get("Market");
        scene.setUsername(this.username);
    }

    public void changeUserInfo() {
        UserInfoEditScene scene = (UserInfoEditScene) UserInfoEditScene.getInstance();
        this.switchScene(scene);
    }

    public void changeTruckInfo() {
        FoodTruckEditScene scene = (FoodTruckEditScene) FoodTruckEditScene.getInstance();
        this.switchScene(scene);
    }

    public void addFund(String fund) {
        UserManager.addMoney(this.username, Integer.parseInt(fund));
    }
    public void withdrawFund(double fund) throws IncorrectArgumentException {
        UserManager.withdrawMoney(username, fund);
        updateUserInfo();
    }

    public void setUserInfo(String username, String key){
        this.username = username;
        this.accessKey = key;
        this.updateUserInfo();
    }

}

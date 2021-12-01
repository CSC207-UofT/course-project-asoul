package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;
import use_case.UserManager;

import java.util.HashMap;

class UserInformationScene extends Scene {
    private final static UserInformationScene us = new UserInformationScene();
    public String username;
    public String nickname;
    public String phoneNum;
    public String truckName;
    public double accBalance;
    private String accessKey;
    private String buyOrderHistory;
    private String sellOrderHistory;


    // Output State

    private UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.accessKey = "";
        this.nickname = "";
        this.phoneNum = "";
        this.truckName = "";
        this.accBalance = 0;
        this.buyOrderHistory = "";
        this.sellOrderHistory = "";
        this.setHelpMessage("\n\nAll commands:\n" +
                "help -> View all commands on this page\n" +
                "view_market -> View all food trucks\n" +
                "change_user_info -> Change user information\n" +
                "change_truck_info -> Change user's food truck information\n" +
                "add_money + [Space] + [amount of money] -> add money to balance\n" +
                "withdraw_money + [Space] + [amount of money] -> withdraw money from balance\n" +
                "view_order + [Space] + [order id] -> view the order\n" +
                "change_truck_status -> Change Truck Status.");
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
            case "change_truck_status":
                try {
                    if (FoodTruckManager.isActive(username, accessKey)) {
                        FoodTruckManager.deactivateTruck(username);
                    } else{
                        FoodTruckManager.activateTruck(username);
                    }
                    updateUserInfo();
                }
                catch (UnauthorizedAccessException | UnknownFoodTruckException e) {
                        this.state.append(e.getMessage());
                }
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
        MarketScene scene = (MarketScene) MarketScene.getInstance();
        this.switchScene(scene);
    }

    public void changeUserInfo() {
        UserInfoEditScene scene = (UserInfoEditScene) UserInfoEditScene.getInstance();
        this.switchScene(scene);
    }

    public void changeTruckInfo() {
        FoodTruckEditScene scene = (FoodTruckEditScene) FoodTruckEditScene.getInstance();
        this.switchScene(scene);
    }

    public void addFund(double fund) throws IncorrectArgumentException {
        UserManager.addMoney(username, fund);
        updateUserInfo();
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

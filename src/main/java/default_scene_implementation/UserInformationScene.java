package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;
import use_case.UserManager;



class UserInformationScene extends Scene {
    private final static UserInformationScene us = new UserInformationScene();
    private String username;
    private String nickname;
    private String phoneNum;
    private String truckName;
    private double accBalance;
    private String accessKey;
    private String truckActive;


    private UserInformationScene() {
        super();
        this.username = "";
        this.accessKey = "";
        this.nickname = "";
        this.phoneNum = "";
        this.truckName = "";
        this.truckActive = "";
        this.accBalance = 0;
        this.setHelpMessage("\n\nAll commands:\n" +
                "help -> View all commands on this page\n" +
                "view_market -> View all food trucks\n" +
                "change_user_info -> Change user information\n" +
                "change_truck_info -> Change user's food truck information\n" +
                "add_money + [Space] + [amount of money] -> add money to balance\n" +
                "withdraw_money + [Space] + [amount of money] -> withdraw money from balance\n" +
                "view_orders -> View all orders\n" +
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
                break;
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
                } catch (UnauthorizedAccessException e){
                    this.state.append(e.getMessage()).append("\n");
                }
                break;
            case "withdraw_money":
                try {
                    double money = Double.parseDouble(text[1]);
                    withdrawFund(money);
                } catch (NumberFormatException | IncorrectArgumentException e) {
                    this.state.append((new IncorrectArgumentException()).getMessage());
                } catch (InsufficientBalanceException | UnauthorizedAccessException e){
                    this.state.append(e.getMessage()).append("\n");
                }
                break;
            case "view_orders":
                viewOrders();
                break;
            case "change_truck_status":
                try {
                    FoodTruckManager.changeTruckStatus(username, accessKey);
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
        updateUserInfo();
        return "------------------------------User Information----------------------------------\n" +
                "Username: " + username + "\n" +
                "Nickname: " + nickname + "\n" +
                "Phone Number: " + phoneNum + "\n" +
                "Truck Name: " + truckName + "\n" +
                "Truck Status: " + truckActive + "\n" +
                "Account Balance: " + accBalance + "\n" +
                this.state;
    }

    public void updateUserInfo(){
        try {
            this.truckName = UserManager.getTruckName(username, accessKey);
            this.nickname = UserManager.getNickname(username, accessKey);
            this.accBalance = UserManager.getBalance(username, accessKey);
            this.phoneNum = UserManager.getPhoneNumber(username, accessKey);
            boolean flag = FoodTruckManager.isActive(username, accessKey);
            if(flag){
                this.truckActive = "Activated";
            }else{
                this.truckActive = "Deactivated";
            }

        }catch(UnauthorizedAccessException e){
            this.state.append(e.getMessage());
        }
    }

    public void viewMarket() {
        MarketScene scene = (MarketScene) MarketScene.getInstance();
        this.switchScene(scene);
        this.state.setLength(0);
    }

    public void changeUserInfo() {
        UserInfoEditScene scene = (UserInfoEditScene) UserInfoEditScene.getInstance();
        this.switchScene(scene);
    }

    public void viewOrders() {
        OrderListScene scene = (OrderListScene) OrderListScene.getInstance();
        this.switchScene(scene);
    }

    public void changeTruckInfo() {
        FoodTruckEditScene scene = (FoodTruckEditScene) FoodTruckEditScene.getInstance();
        this.switchScene(scene);
    }

    public void addFund(double fund) throws IncorrectArgumentException, UnauthorizedAccessException {
        UserManager.addMoney(username, accessKey, fund);
        updateUserInfo();
    }
    public void withdrawFund(double fund) throws IncorrectArgumentException,
            InsufficientBalanceException, UnauthorizedAccessException{
        UserManager.withdrawMoney(username, accessKey, fund);
        updateUserInfo();
    }

    public void setUserInfo(String username, String key){
        this.username = username;
        this.accessKey = key;
        this.updateUserInfo();
    }

}

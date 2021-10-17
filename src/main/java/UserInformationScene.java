import java.util.HashMap;

public class UserInformationScene extends Scene{
    private String userType;
    private String username;
    private HashMap<String, String> displayMap;
    // Output State
    private boolean invalidFundError;
    private boolean changeNicknameSuccess;

    public UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.userType = "";
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
    }

    @Override
    public void handleInput(String input) {
        this.refreshOutputState();
        String[] text = input.split(" ");
        if(input.equals("sign out")){
            this.switchScene(Scene.allScenes.get("Login"));
<<<<<<< HEAD
        }else if(input.equals("Find Foods")){
            this.switchScene(Scene.allScenes.get("Market"));
=======
        }else if(text[0].equals("add_fund")){
            try {
                this.addFund(text[1]);
            }catch (NumberFormatException e){
                this.invalidFundError = true;
            }
        }else if(input.equals("view market")){
            this.viewMarket();
>>>>>>> bf2a18b3293ec14919de68ff415513cde7de0b4b
        }else{
            // TODO: Throws unknown command error
        }
    }

    private void refreshOutputState(){
        this.invalidFundError = false;
        this.changeNicknameSuccess = false;
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
        if(this.userType.equals("seller")){
            userInfo = Scene.sellerManager.getUserByAccountName(this.username);
        }else{
            userInfo = Scene.customerManager.getUserByAccountName(this.username);
        }
        StringBuilder outputString = new StringBuilder("------------------------User Information---------------------------");
        for(String field: userInfo.keySet()){
            String content = userInfo.get(field);
            outputString.append("\n").append(field).append(": ").append(content);
        }
        if(this.invalidFundError){
            outputString.append("\n\n").append("Invalid Fund entered.");
        }else if(this.changeNicknameSuccess){
            outputString.append("\n\nSuccessfully changed nickname to: ").append(userInfo.get("nickname"));
        }
        return outputString.toString();
    }

    public void setUserInfo(String userType, String username){
        assert userType.equals("Customer") || userType.equals("Seller");
        this.userType = userType;
        this.username = username;
    }
}

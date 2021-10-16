import java.util.HashMap;

public class UserInformationScene extends Scene{
    private String userType;
    private String username;

    public UserInformationScene() {
        super("UserInformation");
        this.username = "";
        this.userType = "";
    }

    @Override
    public void handleInput(String input) {
        if(input.equals("sign out")){
            Scene.setActiveScene(Scene.allScenes.get("Login"));
        }else{
            // TODO: Throws unknown command error
        }
    }

    @Override
    public String constructOutputString() {
        HashMap<String, String> userInfo;
        if(this.userType.equals("seller")){
            userInfo = Scene.sellerManager.getUserInfo(this.username);
        }else{
            userInfo = Scene.customerManager.getUserInfo(this.username);
        }
        StringBuilder outputString = new StringBuilder("------------------------User Information---------------------------");
        for(String field: userInfo.keySet()){
            String content = userInfo.get(field);
            outputString.append("\n").append(field).append(": ").append(content);
        }
        return outputString.toString();
    }

    public void setUserInfo(String userType, String username){
        assert userType.equals("Customer") || userType.equals("Seller");
        this.userType = userType;
        this.username = username;
    }
}
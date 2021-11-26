package DefaultSceneImplementation;

import Controllers.Scene;
import use_case.FoodTruckManager;
import use_case.UserManager;

import java.util.HashMap;

public class RegisterScene extends Scene {
    private final HashMap<String, String> displayMap;
    private final UserManager userManager = new UserManager();
    private final FoodTruckManager foodTruckManager = new FoodTruckManager();

    public RegisterScene() {
        super("Register");
        this.fields.put("username", "");
        this.fields.put("password", "");
        this.fields.put("nickname", "");
        this.fields.put("phone_number", "");
        this.displayMap = new HashMap<>();
        this.displayMap.put("username", "Username");
        this.displayMap.put("password", "Password");
        this.displayMap.put("nickname", "Nickname");
        this.displayMap.put("phone_number", "Phone Number");
        commandSet.add("U"); //TODO: modify
        commandSet.add("P");
        commandSet.add("N");
        commandSet.add("PN");
        commandSet.add("confirm");
        commandSet.add("register");
        commandSet.add("login");
}
    public String registerUser() { // Create new users
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String nickname = this.fields.get("nickname");
        String phoneNumber = this.fields.get("phone_number"); //TODO: let use cases throw Exceptions.
        userManager.createUser(username, password, nickname, phoneNumber); // TODO: Entities.User creation exception handling
        this.clearFields();
        return "register success";
        //TODO: return register failure
    }

    @Override
    protected void switchScene(Scene scene) {
        super.switchScene(scene);
        this.clearFields();
}
    public String getfieldString() {
        return this.fields.toString();
    }
}
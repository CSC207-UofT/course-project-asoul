package Controllers;

import Use_case.FoodTruckManager;
import Use_case.UserManager;

import java.util.ArrayList;
import java.util.HashMap;

public class RegisterScene extends Scene{
    private final HashMap<String, String> displayMap;
    private final UserManager userManager = new UserManager();
    private final FoodTruckManager foodTruckManager = new FoodTruckManager();

    public RegisterScene() {
        super("register");
        this.fields.put("username", "");
        this.fields.put("password", "");
        this.fields.put("user_type", "");
        this.fields.put("nickname", "");
        this.fields.put("phone_number", "");
        this.displayMap = new HashMap<>();
        this.displayMap.put("username", "Username");
        this.displayMap.put("password", "Password");
        this.displayMap.put("nickname", "Nickname");
        this.displayMap.put("user_type", "User Type");
        this.displayMap.put("phone_number", "Phone Number");
        ArrayList<String> commands = new ArrayList<>();
        commands.add("U"); //TODO: modify
        commands.add("P");
        commands.add("N");
        commands.add("T");
        commands.add("PN");
        commands.add("confirm");
        commands.add("register");
        this.commandSet =  commands;
}
    public String registerUser() { // Create new users
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String userType = this.fields.get("user_type");
        String nickname = this.fields.get("nickname");
        String phoneNumber = this.fields.get("phone_number"); //TODO: let use cases throw Exceptions.
        userManager.createUser(username, password, nickname, phoneNumber); // TODO: Entities.User creation exception handling
        FoodTruckManager.createEmptyFoodTruck(this.fields.get("username") + " 's Food truck.");


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
package Controllers;

import java.util.HashMap;

public class RegisterScene extends Scene{
    private final HashMap<String, String> displayMap;

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
        this.displayMap.put("user_type", "Entities.User Type");
        this.displayMap.put("phone_number", "Phone Number");
}
    public void registerUser() { // Create new users
        String username = this.fields.get("username");
        String password = this.fields.get("password");
        String userType = this.fields.get("user_type");
        String nickname = this.fields.get("nickname");
        String phoneNumber = this.fields.get("phone_number"); //TODO: let use cases throw Exceptions.
        Scene.sellerManager.createUser(userType, username, password, nickname, phoneNumber); // TODO: Entities.User creation exception handling
        if (userType.equals("Entities.Seller")) {
            Scene.foodTruckManager.createDefaultFoodTruck(Scene.sellerManager, username);
        }
        this.clearFields();
    }

    @Override
    protected void switchScene(Scene scene) {
        super.switchScene(scene);
        this.clearFields();
}
    public void getfield() {
        System.out.println(((Scene) this).fields);
    }
}
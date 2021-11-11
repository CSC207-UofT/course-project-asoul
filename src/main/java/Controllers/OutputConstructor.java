package Controllers;

public class OutputConstructor {
    public static void askRegisterInto() {
        System.out.println("Please provide your username, password, user type, Phone number");
    }

    public static void printCurrSceneCommands(Scene scene) {
        System.out.println(scene.commandSet); //TODO: toString??
    }

    public static void askLoginInfo() {
        System.out.println("Please enter your username and password");
    }

    public static void registerGeneralInfo(RegisterScene scene) {
        String str = "Username: " + scene.fields.get("username") + "\n" +
                "Password: " + scene.fields.get("password") + "\n" +
                "User Type: " + scene.fields.get("user_type") + "\n" +
                "Nickname: " + scene.fields.get("nickname") + "\n" +
                "Phone number: " + scene.fields.get("phone_number");
        System.out.println(str);
    }

    public static void loginGeneral() {

    }
}

package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;

class MenuEditScene extends Scene {
    private static final MenuEditScene mes = new MenuEditScene();
    private String username;

    private MenuEditScene() {
        super();
        this.fields.put("Food Name", "");
        this.fields.put("Description", "");
        this.fields.put("Price", "");
        this.fields.put("ID", "");
        String helpMessage = "\n\n" + "All commands:\n" + "help -> View all commands on this page\n" +
                "food_name + [Space] + [food name] -> Enter food name\n" +
                "description + [Space] + [food description] -> Enter description of the food\n" +
                "price + [Space] + [food price] -> Enter food price\n" +
                "id + [Space] + [food id] -> Enter food id\n" +
                "add -> Add food to menu(you must have entered food name, food description, food price, and food id to add food to menu)\n" +
                "delete + [Space] + [food name] -> Delete the food\n" +
                "confirm -> Save all changes about menu and back to user information page\n" +
                "back -> Discard changes and return to edit food truck page";
        this.setHelpMessage(helpMessage);
    }

    @Override
    public void handleInputString(String input) {
        String[] text = input.split(" ");
        switch (text[0]) {
            case "back":
            case "confirm":
                switchScene((FoodTruckEditScene)FoodTruckEditScene.getInstance());
                break;
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "food_name":
                this.fillInField("Food Name", text[1]);
                break;
            case "description":
                this.fillInField("Description", text[1]);
                break;
            case "id":
                this.fillInField("ID", text[1]);
                break;
            case "price":
                this.fillInField("Price", text[1]);
                break;
            case "add":
                try{
                    checkAllFilled();
                    FoodTruckManager.addFoodToMenu(fields.get("Food Name"), fields.get("Price"),
                            fields.get("Description"), fields.get("ID"), username);
                    clearFields();
                } catch (UnfilledArgumentException | CollidedFoodException | FoodIdCollisionException e) {
                    this.state.append(e.getMessage());
                }
                break;
            case "delete":
                try{
                    if(!FoodTruckManager.hasFoodId(text[1], username)){
                        throw new UnknownFoodException();
                    }
                    FoodTruckManager.removeFoodFromMenu(text[1], username);
                } catch (UnknownFoodException e) {
                    state.append(e.getMessage());
                }
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    @Override
    public String constructOutputString() {
        StringBuilder outputString = new StringBuilder();
        String name = this.fields.get("Food Name");
        String description = this.fields.get("Description");
        String price = this.fields.get("Price");
        String id = this.fields.get("ID");
        try {
        outputString.append("---------------Current Menu---------------\n").append(FoodTruckManager.getMenu(username)).append("\n").append("---------------------------------------------\n");}
        catch (UnknownFoodTruckException e){
            state.append(e.getMessage());
        }
        outputString.append("Food Name: ").append(name).append("\n");
        outputString.append("ID: ").append(id).append("\n");
        outputString.append("Description: ").append(description).append("\n");
        outputString.append("Price: ").append(price).append("\n");
        outputString.append(this.state);
        return outputString.toString();
    }

    public static Singleton getInstance(){
        return mes;
    }

    public void setUserInfo(String username) {
        this.username = username;
    }

    private void checkAllFilled() throws UnfilledArgumentException {
        for (String key: fields.keySet()){
            if (fields.get(key).equals("")){
                throw new UnfilledArgumentException();
            }
        }
    }
}

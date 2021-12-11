package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.FoodTruckManager;

import java.util.HashMap;

class FoodTruckScene extends Scene {
    private static final FoodTruckScene fts = new FoodTruckScene();
    private final HashMap<String, Integer> cart; // String = key to the food on the food menu, integer = quantity
    private String username;
    private String accessKey;
    private String foodtruck;

    private FoodTruckScene() {
        super();
        foodtruck = "";
        cart = new HashMap<>();
        setHelpMessage("""
                select + [Food id] + [Quantity] -> add food to your cart
                remove + [Food id] + [Quantity] -> Remove food from your cart
                clear_cart -> Empty your cart
                back -> Back to market
                confirm -> Proceed to checkout
                """
        );
    }

    public static Singleton getInstance() {
        return fts;
    }

    /**
     * @param input input from commandline interface.
     */
    @Override
    public void handleInputString(String input) {
        String[] text = input.split(" ");
        switch (text[0]) {
            case "back":
                cart.clear();
                switchScene((MarketScene) MarketScene.getInstance());
                break;
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "exit":
                Scene.exit = true;
                break;
            case "select":
                try {
                    this.addFoodToCart(text[1], Integer.parseInt(text[2]));
                } catch (NumberFormatException e) {
                    this.state.append((new IncorrectArgumentException()).getMessage()).append("\n");
                }
                break;
            case "remove":
                try {
                    this.removeFoodFromCart(text[1], Integer.parseInt(text[2]));
                } catch (NumberFormatException e) {
                    this.state.append((new IncorrectArgumentException()).getMessage()).append("\n");
                }
                break;
            case "clear_cart":
                cart.clear();
                break;
            case "confirm":
                this.placeOrder();
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    /**
     * @return the output string of this string.
     */
    @Override
    public String constructOutputString() {
        StringBuilder sb = new StringBuilder();

        // Display Foodtruck menu
        try {
            sb.append(FoodTruckManager.getFoodTruckDetail(foodtruck)).append("\n\n");
        } catch (UnknownFoodTruckException e) {
            state.append(e.getMessage()).append("\n");
        }

        sb.append("------------------Your Cart--------------------\n");
        // Print Cart
        for (String key : cart.keySet()) {
            try {
                String foodName = FoodTruckManager.getFoodName(foodtruck, key);
                int quantity = cart.get(key);
                double price = FoodTruckManager.getFoodPrice(foodtruck, key);
                sb.append(String.format("ID: %s Item: %s Price: %f Quantity: %d", key, foodName, price, quantity)
                ).append("\n");
            } catch (Exception e) {
                state.append(e.getMessage()).append("\n");
            }
        }
        try {
            double price = FoodTruckManager.calculatePrice(foodtruck, cart);
            sb.append("\nTotal Price: ").append(price).append("\n");
        } catch (UnknownFoodTruckException | UnknownFoodException e) {
            state.append(e.getMessage()).append("\n");
        }
        sb.append("\n");
        sb.append(state.toString()).append("\n");
        return sb.toString();
    }

    /**
     * @param key      the key of the food in the menu.
     * @param quantity the quantity of food.
     */
    private void addFoodToCart(String key, int quantity) {
        if (FoodTruckManager.notHaveFoodId(key, foodtruck)) {
            this.state.append("Selected food does not exist in the menu!\n");
            return;
        }
        if (quantity <= 0) {
            this.state.append((new IllegalArgumentException()).getMessage());
        }
        cart.put(key, quantity);
    }

    /**
     * @param key      the key of the food in the menu.
     * @param quantity the quantity of food.
     */
    public void removeFoodFromCart(String key, int quantity) {
        if (!cart.containsKey(key)) {
            this.state.append("The food does not appear to be in your cart!\n");
            return;
        }
        if (quantity < 0) {
            this.state.append((new IllegalArgumentException()).getMessage());
            return;
        }
        int q = cart.get(key);
        int amount = q - quantity;
        if (amount <= 0) {
            cart.remove(key);
        } else {
            cart.put(key, amount);
        }

    }

    /**
     * @param name      username
     * @param accessKey access key
     */
    public void setUserInfo(String name, String accessKey) {
        this.username = name;
        this.accessKey = accessKey;
    }

    /**
     * @param name name of the food truck.
     */
    public void setFoodtruck(String name) {
        foodtruck = name;
    }

    /**
     * Place this order in this food truck.
     */
    private void placeOrder() {
        if (cart.size() == 0) {
            state.append("Your cart is empty! Please add some food before proceeding to checkout!\n");
            return;
        }
        try {
            FoodTruckManager.placeOrder(username, accessKey, foodtruck, cart);
            switchScene((Scene) UserInformationScene.getInstance());
            this.cart.clear(); // Clear the cart if the action is successful
        } catch (UnknownFoodTruckException | UnknownFoodException | UnauthorizedAccessException |
                InsufficientBalanceException | IncorrectArgumentException | UnknownUserException e) {
            state.append(e.getMessage()).append("\n");
        }
    }
}
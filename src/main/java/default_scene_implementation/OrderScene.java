package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.OrderManager;
import use_case.UserManager;

class OrderScene extends Scene {
    private final static OrderScene os = new OrderScene();

    private String username;
    private String accessKey;
    private String order;

    private OrderScene() {
        super();
        this.username = "";
        this.accessKey = "";
        this.setHelpMessage("""
                rate + [0-10] -> rate this order (Only as customer)
                complete -> complete this order (Only as seller)
                back -> return to last page
                """
        );
    }

    public static Singleton getInstance() {
        return os;
    }

    /**
     * @param input input from commandline interface.
     */
    @Override
    public void handleInputString(String input) {
        String[] text = input.split(" ");
        switch (text[0]) {
            case "rate" -> {
                double rate = Double.parseDouble(text[1]);
                rateOrder(rate);
            }
            case "complete" -> completeOrder();
            case "back" -> this.switchScene((Scene) OrderListScene.getInstance());
            case "help" -> this.state.append(this.getHelpMessage());
            case "exit" -> Scene.exit = true;
            default -> this.state.append((new UnknownCommandException()).getMessage()).append("\n");
        }
    }

    /**
     * @return Output to commandline interface.
     */
    @Override
    public String constructOutputString() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(OrderManager.getOrderDetail(order));
        } catch (UnknownOrderException e) {
            state.append(e.getMessage()).append("\n");
        }
        sb.append(state);
        return sb.toString();
    }

    /**
     * @param rating rating to the order.
     */
    private void rateOrder(double rating) {
        try {
            OrderManager.rateOrder(username, accessKey, rating, order);
        } catch (IncorrectArgumentException | UnknownOrderException | UnauthorizedAccessException e) {
            state.append(e.getMessage()).append("\n");
        }
    }

    /**
     * Complete this order.
     */
    private void completeOrder() {
        try {
            UserManager.completeOrder(order, username, accessKey);
        } catch (UnauthorizedAccessException | UnknownUserException | UnknownOrderException e) {
            state.append(e.getMessage()).append("\n");
        }
    }

    /**
     * @param username  username of the user.
     * @param accessKey access key
     */
    public void setUserInfo(String username, String accessKey) {
        this.username = username;
        this.accessKey = accessKey;
    }

    public void setOrderID(String id) {
        order = id;
    }
}

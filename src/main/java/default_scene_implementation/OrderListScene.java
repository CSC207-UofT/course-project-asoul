package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
import use_case.OrderManager;
import use_case.UserManager;

import java.util.HashMap;
import java.util.HashSet;

public class OrderListScene extends Scene {
    private final static OrderListScene uls = new OrderListScene();
    private HashMap<Integer, String> sellOrderHistory;
    private HashMap<Integer, String> buyOrderHistory;
    private HashMap<Integer, String> sellInProgress;
    private HashMap<Integer, String> buyInProgress;
    private String username;
    private String accessKey;

    private OrderListScene() {
        super();
        this.username = "";
        this.accessKey = "";
        sellOrderHistory = new HashMap<>();
        buyOrderHistory = new HashMap<>();
        sellInProgress = new HashMap<>();
        buyInProgress = new HashMap<>();
        this.setHelpMessage("""


                All commands:
                help -> View all commands on this page
                buy_in_progress + [Space] + [id] -> View the in progress buy order
                sell_in_progress + [Space] + [id] -> View the in progress sell order
                buy_history + [Space] + [id] -> View the history buy buy order
                sell_history + [Space] + [id] -> View the history sell buy order
                back -> View user information
                """);
    }

    /**
     * @param input input from interface.
     */
    @Override
    public void handleInputString(String input) {
        String[] text = input.split(" ");
        OrderScene scene = (OrderScene) OrderScene.getInstance();
        switch (text[0]) {
            case "help" -> this.state.append(this.getHelpMessage());
            case "back" -> switchScene((UserInformationScene) UserInformationScene.getInstance());
            case "buy_in_progress" -> {
                scene.setOrderID(buyInProgress.get(Integer.parseInt(text[1])));
                switchScene(scene);
            }
            case "sell_in_progress" -> {
                scene.setOrderID(sellInProgress.get(Integer.parseInt(text[1])));
                switchScene(scene);
            }
            case "buy_history" -> {
                scene.setOrderID(buyOrderHistory.get(Integer.parseInt(text[1])));
                switchScene(scene);
            }
            case "sell_history" -> {
                scene.setOrderID(sellOrderHistory.get(Integer.parseInt(text[1])));
                switchScene(scene);
            }
            default -> this.state.append((new UnknownCommandException()).getMessage()).append("\n");
        }


    }

    /**
     * @return output String to interface.
     */
    @Override
    public String constructOutputString() {
        updateMaps();
        try {

            return "Buy Order History: \n" + constructMapString(buyOrderHistory) + "\n" +
                    "Sell Order History: \n" + constructMapString(sellOrderHistory) + "\n" +
                    "Buy In Progress: \n" + constructMapString(buyInProgress) + "\n" +
                    "Sell In Progress: \n" + constructMapString(sellInProgress) + "\n" +
                    this.state;
        } catch (UnknownOrderException e) {
            return state.append(e.getMessage()).toString();
        }
    }

    public static Singleton getInstance() {
        return uls;
    }

    public void setOrderListInfo(String username, String key) {
        this.username = username;
        this.accessKey = key;
    }

    /**
     * Update the OrderLists.
     */
    private void updateMaps() {
        try {
            sellInProgress = constructOrderMap(UserManager.getSellInProgress(username, accessKey));
            buyInProgress = constructOrderMap(UserManager.getBuyInProgress(username, accessKey));
            sellOrderHistory = constructOrderMap(UserManager.getSellOrderHistory(username, accessKey));
            buyOrderHistory = constructOrderMap(UserManager.getBuyOrderHistory(username, accessKey));
        } catch (UnauthorizedAccessException e) {
            state.append(e.getMessage());
        }

    }

    /**
     * @param map a map from an integer to a string of order description.
     * @return a string of this map
     * @throws UnknownOrderException if some orders are unknown.
     */
    private String constructMapString(HashMap<Integer, String> map) throws UnknownOrderException {
        StringBuilder result = new StringBuilder();
        for (Integer i : map.keySet()) {
            String orderDescription = OrderManager.getOrderDescription(map.get(i));
            result.append("ID: ").append(i).append(" ").append(orderDescription).append("  ");
        }
        return result.toString();

    }

    /**
     * @param set a set of orders.
     * @return a hashmap from food ID to order description.
     */
    private HashMap<Integer, String> constructOrderMap(HashSet<String> set) {
        HashMap<Integer, String> result = new HashMap<>();
        Integer key = 1;
        for (String element : set) {
            result.put(key, element);
            key++;
        }
        return result;
    }
}

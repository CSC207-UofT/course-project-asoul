package default_scene_implementation;

import controllers.Scene;
import exceptions.*;
import singleton_pattern.Singleton;
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

    private OrderListScene(){
        super();
        this.username = "";
        this.accessKey = "";
        sellOrderHistory = new HashMap<>();
        buyOrderHistory = new HashMap<>();
        sellInProgress = new HashMap<>();
        buyInProgress = new HashMap<>();
        this.setHelpMessage("\n\nAll commands:\n" +
                "help -> View all commands on this page\n" +
                "buy_in_progress + [Space] + [id] -> View the in progress buy order\n" +
                "sell_in_progress + [Space] + [id] -> View the in progress sell order\n" +
                "buy_history + [Space] + [id] -> View the history buy buy order\n" +
                "sell_history + [Space] + [id] -> View the history sell buy order\n" +
                "back -> View user information\n" );
    }

    @Override
    public void handleInputString(String input) {
        String[] text = input.split(" ");
        OrderScene scene = (OrderScene)OrderScene.getInstance();
        switch (text[0]) {
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "back":
                switchScene((UserInformationScene)UserInformationScene.getInstance());
                break;
            case "buy_in_progress":
                switchScene(scene);
                scene.setOrderID(buyInProgress.get(Integer.parseInt(text[1])));
                break;
            case "sell_in_progress":
                switchScene(scene);
                scene.setOrderID(sellInProgress.get(Integer.parseInt(text[1])));
                break;
            case "buy_history":
                switchScene(scene);
                scene.setOrderID(buyOrderHistory.get(Integer.parseInt(text[1])));
                break;
            case "sell_history":
                switchScene(scene);
                scene.setOrderID(sellOrderHistory.get(Integer.parseInt(text[1])));
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }


    }

    @Override
    public String constructOutputString() {
        updateMaps();
        return "Buy Order History: \n" + buyOrderHistory + "\n" +
                "Sell Order History: \n" + sellOrderHistory + "\n" +
                "Buy In Progress: \n" + buyInProgress + "\n" +
                "Sell In Progress: \n" + sellInProgress + "\n" +
                this.state;
    }

    public static Singleton getInstance(){
        return uls;
    }

    public void setOrderListInfo(String username, String key){
        this.username = username;
        this.accessKey = key;
    }

    private void updateMaps() {
        try{
        sellInProgress = constructOrderMap(UserManager.getSellInProgress(username, accessKey));
        buyInProgress = constructOrderMap(UserManager.getBuyInProgress(username, accessKey));
        sellOrderHistory = constructOrderMap(UserManager.getSellOrderHistory(username, accessKey));
        buyOrderHistory = constructOrderMap(UserManager.getBuyOrderHistory(username, accessKey));}
        catch (UnauthorizedAccessException e) {
            state.append(e.getMessage());
        }

    }

    private HashMap<Integer, String> constructOrderMap(HashSet<String> set) {
        HashMap<Integer, String> result = new HashMap<>();
        Integer key = 1;
        for (String element: set) {
            result.put(key, element);
            key ++;
        }
        return result;
    }
}

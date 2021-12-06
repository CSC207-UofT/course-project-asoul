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
        this.setHelpMessage("rate + [0-10] -> rate this order (Only as customer)\n" +
                "complete -> complete this order (Only as seller)\n" +
                "back -> return to last page\n"
        );
    }

    public static Singleton getInstance(){
        return os;
    }

    @Override
    public void handleInputString(String input){
        String[] text = input.split(" ");
        switch (text[0]) {
            case "rate":
                double rate = Double.parseDouble(text[1]);
                rateOrder(rate);
                break;
            case "complete":
                completeOrder();
                break;
            case "back":
                this.switchScene((Scene)OrderListScene.getInstance());
                break;
            case "help":
                this.state.append(this.getHelpMessage());
                break;
            case "exit":
                Scene.exit = true;
                break;
            default:
                this.state.append((new UnknownCommandException()).getMessage()).append("\n");
                break;
        }
    }

    @Override
    public String constructOutputString(){
        StringBuilder sb = new StringBuilder();
        try{
            sb.append(OrderManager.getOrderDetail(order));
        }catch (UnknownOrderException e){
            state.append(e.getMessage()).append("\n");
        }
        sb.append(state);
        return sb.toString();
    }

    private void rateOrder(double rating){
        try {
            OrderManager.rateOrder(username, accessKey, rating, order);
        }catch (IncorrectArgumentException | UnknownOrderException | UnauthorizedAccessException e){
            state.append(e.getMessage()).append("\n");
        }
    }

    private void completeOrder(){
        try{
            UserManager.completeOrder(order, username, accessKey);
        }catch (UnauthorizedAccessException | UnknownUserException | UnknownOrderException e){
            state.append(e.getMessage()).append("\n");
        }
    }

    public void setUserInfo(String username, String accessKey){
        this.username = username;
        this.accessKey = accessKey;
    }

    public void setOrderID(String id){
        order = id;
    }
}

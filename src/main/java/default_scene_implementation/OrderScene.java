package default_scene_implementation;
import controllers.Scene;
import singleton_pattern.Singleton;
import use_case.OrderManager;

class OrderScene extends Scene {
    private final static OrderScene os = new OrderScene();

    public String OrderID;

    private OrderScene() {
        super("Order");
    }

    public static Singleton getInstance(){
        return os;
    }

    public void rateOrder(Double rating){
        OrderManager.rateOrder(rating, OrderID);
    }

    public void setOrderID(int id){
        OrderID = Integer.toString(id);
    }

    public String printOrder(){
        return "order details coming soon";
    }

}

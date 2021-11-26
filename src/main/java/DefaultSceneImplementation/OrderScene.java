package DefaultSceneImplementation;
import Controllers.Scene;
import use_case.OrderManager;

public class OrderScene extends Scene {
    public String OrderID;


    public OrderScene() {
        super("Order");
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

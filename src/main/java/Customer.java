import java.util.ArrayList;

public class Customer extends User{
    private ArrayList<Order> orderHistory;
    public Customer(String accName, String password, String nickname, String phoneNumber) {
        super(accName, password, nickname, phoneNumber);
        orderHistory = new ArrayList<Order>();
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public boolean storeOrder(Order order){
        try {
            this.orderHistory.add(order);
        }catch (Exception e){
            System.out.println("Add fail");
            return false;
        }
        System.out.println("Add success");
        return true;
    }

    /* TODO
    public boolean cancelOrder(Order order){
        + money \This order should be cancelled in Order Manager and only send back money cost here
    }
    public boolean checkOrderStatus(Order order)[

    }
    public Order orderFood(FoodTruck foodtruck){
        - money \Order placed here? or just withdraw money here.
    }

    public boolean rateOrder(int rate, Order order){
        try{
            order.setRating(rate);
        }catch (Exception e){
            System.out.println("Rate Fail!");
            return false;
        }
        System.out.println("Rate success!");
        return true;
    }

     */

}
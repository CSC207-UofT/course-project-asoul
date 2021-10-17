import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Order> orderHistory; //A List that stores all history orders of this Customer

    /**
     * Construct an instance of a Customer
     *
     * @param accName     The account name of this Customer
     * @param password    The password of this Customer account
     * @param nickname    The nickname of this Customer
     * @param phoneNumber A string representing the phone number of this Customer
     */
    public Customer(String accName, String password, String nickname, String phoneNumber) {
        super(accName, password, nickname, phoneNumber);
        orderHistory = new ArrayList<Order>();
    }

    @Override
    public String toString() {
        String initial = "Account Name: " + this.getAccountName() + "; Password: " + this.getPassword() + "; Nickname: " +
                this.getNickname() + "; PhoneNumber: " + this.getPhoneNumber() + ".";
        return initial;
    }


    /**
     * Add given order to order history.
     *
     * @param order the order that will be added
     * @return Return True if success, and False otherwise.
     */
    public boolean storeOrder(Order order) {
        try {
            this.orderHistory.add(order);
        } catch (Exception e) {
            System.out.println("Add fail");
            return false;
        }
        System.out.println("Add success");
        return true;
    }

    /**
     * Remove given order to order history.
     *
     * @param order the order that will be removed
     * @return Return True if success, and False otherwise.
     */
    public boolean removeOrder(Order order) {
        try {
            this.orderHistory.remove(order);
        } catch (Exception e) {
            System.out.println("Remove fail");
            return false;
        }
        System.out.println("Remove success");
        return true;
    }

    /**
     * Getting for all instance variables
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

}


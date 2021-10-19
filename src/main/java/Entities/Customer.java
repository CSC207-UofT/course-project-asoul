package Entities;

import java.util.ArrayList;

public class Customer extends User {
    private final ArrayList<Order> orderHistory; //A List that stores all history orders of this Entities.Customer

    /**
     * Construct an instance of a Entities.Customer
     *
     * @param accName     The account name of this Entities.Customer
     * @param password    The password of this Entities.Customer account
     * @param nickname    The nickname of this Entities.Customer
     * @param phoneNumber A string representing the phone number of this Entities.Customer
     */
    public Customer(String accName, String password, String nickname, String phoneNumber) {
        super(accName, password, nickname, phoneNumber);
        orderHistory = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Account Name: " + this.getAccountName() + "; Password: " + this.getPassword() + "; Nickname: " +
                this.getNickname() + "; PhoneNumber: " + this.getPhoneNumber() + ".";
    }


    /**
     * Add given order to order history.
     *
     * @param order the order that will be added
     */
    public void storeOrder(Order order) {
        try {
            this.orderHistory.add(order);
        } catch (Exception e) {
            System.out.println("Add fail");
            return;
        }
        System.out.println("Add success");
    }

    /**
     * Remove given order to order history.
     *
     * @param order the order that will be removed
     */
    public void removeOrder(Order order) {
        try {
            this.orderHistory.remove(order);
        } catch (Exception e) {
            System.out.println("Remove fail");
            return;
        }
        System.out.println("Remove success");
    }

    /**
     * Getting for all instance variables
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

}


import Exceptions.IncorrectCredentialsException;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class UserManager {
    protected static HashMap<String, User> userMap = new HashMap<>();
    protected static HashMap<String, Customer> customerMap = new HashMap<>();
    protected static HashMap<String, Seller> sellerMap = new HashMap<>();

    abstract public void login (String accName, String password) throws IncorrectCredentialsException;

    public void addMoney(User user, int money) {
        user.addMoney(money);
    }

    public void withdrawMoney(User user, int money) {
        user.withdrawMoney(money);
    }

    public double checkBalance(User user) {
        return user.checkBalance();
    }

    public HashMap<String, String> getUserByAccountName(String userName) {
        User user = userMap.get(userName);
        HashMap<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("accName", user.getAccountName());
        userInfoMap.put("accBalance", "" + user.getAccountBalance());
        userInfoMap.put("password", user.getPassword());
        userInfoMap.put("nickname", user.getNickname());
        userInfoMap.put("phoneNum", user.getPhoneNumber());
        if (user instanceof Customer) {
            StringBuilder orderHistoryString = new StringBuilder(new String(""));
            for (Order order: ((Customer) user).getOrderHistory()) {
                orderHistoryString.append(order.toString()).append("\n");
            }
            userInfoMap.put("orderHistory", orderHistoryString.toString());
        }
//        else {
//            StringBuilder trucks = new StringBuilder(new String(""));
//            for (FoodTruck truck: ((Seller) user).getFoodTruck()) {
//                orderHistoryString.append(order.toString()).append("\n");
//            }
//        }
        return userInfoMap;
    }

    public String getUserType(String accName) {
        if (customerMap.containsKey(accName)) {
            return "Customer";
        }
        else if (sellerMap.containsKey(accName)) {
            return "Seller";
        }
        return "no this account";
    }




    public boolean createUser(String userType, String accName, String password,
                              String nickname, String phoneNum) {
        if (userMap.containsKey(accName)) {
            return false;
        }

        if (userType.equals("Customer")) {
            Customer newCustomer = new Customer(accName, password, nickname, phoneNum);
            userMap.put(accName, newCustomer);
            customerMap.put(accName, newCustomer);
            return true;
        }
        else {
            Seller newSeller = new Seller(accName, password, nickname, phoneNum);
            userMap.put(accName, newSeller);
            sellerMap.put(accName, newSeller);
            return true;
        }
    }

}

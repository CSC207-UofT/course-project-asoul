package Use_case;

import Entities.Customer;
import Entities.Order;
import Entities.Seller;
import Entities.User;
import Exceptions.IncorrectCredentialsException;
import Exceptions.IncorrectOldPasswordException;


import java.util.HashMap;

/**
 * A Use_case.UserManager that manages all the Users.
 */
abstract public class UserManager {
    protected static HashMap<String, User> userMap = new HashMap<>(); // A map from user's account name to Entities.User object.
    protected static HashMap<String, Customer> customerMap = new HashMap<>(); // A map from customer's account name to Entities.Customer
    // object.
    protected static HashMap<String, Seller> sellerMap = new HashMap<>(); // A map from seller's account name to Entities.Seller object.

    /**
     * @param accName  A String that represents the account Name.
     * @param password A String of password that the user typed in.
     * @throws IncorrectCredentialsException Exception if the password doesn't match the account name or there is no
     *                                       such account name.
     */
    abstract public void login(String accName, String password) throws IncorrectCredentialsException;

    /**
     * @param accountName The username of the user that wants to add money.
     * @param money       The amount of money the user wants to add.
     */
    public void addMoney(String accountName, int money) {
        User user = userMap.get(accountName);
        user.addMoney(money);

    }

    /**
     * @param accountName The user that wants to withdraw money.
     * @param money       The amount of money the user wants to withdraw.
     */
    public void withdrawMoney(String accountName, int money) {
        User user = userMap.get(accountName);
        user.withdrawMoney(money);
    }

    /**
     * @param accName The account name of user that wants to check their balance.
     * @return The amount of money in the user's balance.
     */
    public double checkBalance(String accName) {
        User user = userMap.get(accName);
        return user.checkBalance();
    }

    /**
     * @param userName A user's account name.
     * @return A map that from the user's information keyword to the user's information.
     */
    public HashMap<String, String> getUserByAccountName(String userName) {
        User user = userMap.get(userName);
        HashMap<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("accName", user.getAccountName());
        userInfoMap.put("accBalance", "" + user.getAccountBalance());
        userInfoMap.put("password", user.getPassword());
        userInfoMap.put("nickname", user.getNickname());
        userInfoMap.put("phoneNum", user.getPhoneNumber());
        if (user instanceof Customer) {
            StringBuilder orderHistoryString = new StringBuilder();
            for (Order order : ((Customer) user).getOrderHistory()) {
                orderHistoryString.append(order.toString()).append("\n");
            }
            userInfoMap.put("orderHistory", orderHistoryString.toString());
        } //to do for seller parts in else.


        return userInfoMap;
    }

    /**
     * @param accName A user's account name.
     * @return A string that represent the user's type.
     */
    public String getUserType(String accName) {
        if (customerMap.containsKey(accName)) {
            return "Entities.Customer";
        } else if (sellerMap.containsKey(accName)) {
            return "Entities.Seller";
        }
        return "no this account";
    }


    /**
     * @param userType The type of the user want to create.
     * @param accName  The account name user wants to have.
     * @param password The password name user wants to have.
     * @param nickname The nickname user wants to have.
     * @param phoneNum The user's phone number.
     * @return true if the user created successfully.
     */
    public boolean createUser(String userType, String accName, String password,
                              String nickname, String phoneNum) {
        if (userMap.containsKey(accName)) {
            return false;
        }

        if (userType.equals("Entities.Customer")) {
            Customer newCustomer = new Customer(accName, password, nickname, phoneNum);
            userMap.put(accName, newCustomer);
            customerMap.put(accName, newCustomer);
        } else {
            Seller newSeller = new Seller(accName, password, nickname, phoneNum);
            userMap.put(accName, newSeller);
            sellerMap.put(accName, newSeller);
        }
        return true;
    }


    /**
     * @param accountName the account name that want to check exists or not.
     * @return true if the account name already exists.
     */
    public boolean checkUserExist(String accountName) {
        return userMap.containsKey(accountName);
    }

    // the account name must exist, I do not
    public void deleteUser(String accountName) {
        String type = getUserType(accountName);
        userMap.remove(accountName);
        if (type.equals("Entities.Customer")) {
            customerMap.remove(accountName);
        } else {
            sellerMap.remove(accountName);
        }
    }

    public void setNickname(String accName, String nickname) {
        userMap.get(accName).setNickname(nickname);
    }

    public void setPassword(String username, String newPassword, String oldPassword) throws IncorrectOldPasswordException {
        userMap.get(username).setPassword(newPassword, oldPassword);
    }

    public String getNickname(String accName) {
        return userMap.get(accName).getNickname();
    }

    public String getPhoneNumber(String accName) {
        return userMap.get(accName).getPhoneNumber();
    }

    public double getBalance(String accName) {
        return userMap.get(accName).getAccountBalance();
    }

}
import Exceptions.IncorrectCredentialsException;


import java.util.HashMap;

/**
 * A UserManager that manages all the Users.
 */
abstract public class UserManager {
    protected HashMap<String, User> userMap = new HashMap<>(); // A map from user's account name to User object.
    protected HashMap<String, Customer> customerMap = new HashMap<>(); // A map from customer's account name to Customer
    // object.
    protected HashMap<String, Seller> sellerMap = new HashMap<>(); // A map from seller's account name to Seller object.

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
     * @param user  The user that wants to withdraw money.
     * @param money The amount of money the user wants to withdraw.
     */
    public void withdrawMoney(User user, int money) {
        user.withdrawMoney(money);
    }

    /**
     * @param user The user that wants to check their balance.
     * @return The amount of money in the user's balance.
     */
    public double checkBalance(User user) {
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
        } //to do for seller parts.


        return userInfoMap;
    }

    /**
     * @param accName A user's account name.
     * @return A string that represent the user's type.
     */
    public String getUserType(String accName) {
        if (customerMap.containsKey(accName)) {
            return "Customer";
        } else if (sellerMap.containsKey(accName)) {
            return "Seller";
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

        if (userType.equals("Customer")) {
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

}

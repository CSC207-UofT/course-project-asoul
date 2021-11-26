package use_case;

import Entities.FoodTruck;
import Entities.Order;
import Entities.User;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.IOException;
import java.util.HashMap;

/**
 * A Use_case.UserManager that manages all the Users.
 */
public class UserManager{
    protected static HashMap<String, User> userMap = new HashMap<>(); // A map from user's account name to Entities.User object.
    private static final Serializer uSerializer = new Serializer("./data/user info", userMap);
    private static final Deserializer uDeserializer = new Deserializer("./data/user info", userMap);

    /**
     * @param accName  A String that represents the account Name.
     * @param password A String of password that the user typed in.
     * @throws IncorrectCredentialsException Exception if the password doesn't match the account name or there is no
     *                                       such account name.
     */
    public static void login(String accName, String password) throws IncorrectCredentialsException {
        if (userMap.containsKey(accName)) {
            if (userMap.get(accName).login(password)) {
                return;
            }
        }
        throw new IncorrectCredentialsException();
    }

    /**
     * @param accountName The username of the user that wants to add money.
     * @param money       The amount of money the user wants to add.
     */

    public static void addMoney(String accountName, int money) throws IncorrectArgumentException {
        User user = userMap.get(accountName);
        boolean addSuccess = user.addMoney(money);
        if (!addSuccess) {
            throw new IncorrectArgumentException();
        }

    }

    /**
     * @param accountName The user that wants to withdraw money.
     * @param money       The amount of money the user wants to withdraw.
     */

    public void withdrawMoney(String accountName, int money) throws IncorrectArgumentException {
        User user = userMap.get(accountName);
        boolean withSuccess = user.withdrawMoney(money);
        if (!withSuccess) {
            throw new IncorrectArgumentException();
        }
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
    public static HashMap<String, String> getUserByAccountName(String userName) {
        User user = userMap.get(userName);
        HashMap<String, String> userInfoMap = new HashMap<>();
        userInfoMap.put("accName", user.getAccountName());
        userInfoMap.put("accBalance", "" + user.getAccountBalance());
        userInfoMap.put("password", user.getPassword());
        userInfoMap.put("nickname", user.getNickname());
        userInfoMap.put("phoneNum", user.getPhoneNumber());

        /*
        StringBuilder orderHistoryString = new StringBuilder();
        for (String orderID : user.getOrderHistory()) {
            Order order =
            orderHistoryString.append(order.toString()).append("\n");
        }
        userInfoMap.put("orderHistory", orderHistoryString.toString());
        */
        return userInfoMap;
    }


    /**
     * @param accName  The account name user wants to have.
     * @param password The password name user wants to have.
     * @param nickname The nickname user wants to have.
     * @param phoneNum The user's phone number.
     * @return true if the user created successfully.
     */
    public boolean createUser(String accName, String password,
                              String nickname, String phoneNum) {
        if (userMap.containsKey(accName)) {
            return false;
        }
        FoodTruckManager.createEmptyFoodTruck(accName);
        User newUser = new User(accName, password, nickname, phoneNum);
        userMap.put(accName, newUser);
        return true;
    }

    /**
     * Return the list of OrderHistory given a User account name.
     */
    public static ArrayList<String> getOrderHistoryByName(String accName){
        if(userMap.containsKey(accName)){
            return userMap.get(accName).getOrderHistory();
        }
        else {
            //TODO Raise Exception here
            return new ArrayList<>();
        }
    }


    /**
     * @param accountName the account name that want to check exists or not.
     * @return true if the account name already exists.
     */
    public static boolean checkUserExist(String accountName) {
        return userMap.containsKey(accountName);
    }

    // the account name must exist, I do not
    public static void deleteUser(String accountName) {
        userMap.remove(accountName);
    }

    public static void setNickname(String accName, String nickname) {
        userMap.get(accName).setNickname(nickname);
    }

    public static void setPhoneNumber(String accName, String phoneNumber) {
        userMap.get(accName).setPhoneNumber(phoneNumber);
    }

    public static void setPassword(String username, String accessKey, String newPassword, String oldPassword)
            throws IncorrectOldPasswordException, UnauthorizedAccessException{
        accessCheck(username, accessKey);
        userMap.get(username).setPassword(newPassword, oldPassword);
    }

    public static String getNickname(String accName) {
        return userMap.get(accName).getNickname();
    }

    public static String getPhoneNumber(String accName) {
        return userMap.get(accName).getPhoneNumber();
    }

    public static double getBalance(String accName) {
        return userMap.get(accName).getAccountBalance();
    }

    /**
     * @param payer the account name of the payer.
     * @param payee the account name of payee.
     * @param password the buyer's password.
     * @param amount the amount of money buyer will pay.
     * @return true if pay is successful.
     */
    public static boolean pay(String payer, String payee, String password, double amount, String accessKey) throws UnauthorizedAccessException{
        accessCheck(payer, accessKey);
        User buyer = UserManager.userMap.get(payer);
        User seller = UserManager.userMap.get(payee);
        double balance = buyer.getAccountBalance();
        if (amount < 0) {
            return false;
        }
        if(balance < amount){
            return false; // buyer does not have enough money to pay
        }
        if (!password.equals(buyer.getPassword())){
            return false;
        }
        buyer.withdrawMoney(amount);
        seller.addMoney(amount);
        return true;
    }

    public static void constructUserDataBase() throws IOException, ClassNotFoundException {
        uDeserializer.deserialize();
    }

    public static void saveUserDataBase() throws IOException {
        uSerializer.serialize();
    }

    private static void addBuyOrder(String user, String orderID){
        User us = userMap.get(user);
        us.storeBuyOrder(orderID);
    }

    private static void addSellOrder(String user, String orderID){
        User us = userMap.get(user);
        us.storeSellOrder(orderID);
    }

    public static void updateOrderHistory(String orderID) throws UnknownUserException {
        Order order = OrderManager.getOrder(orderID);
        String buyer = Objects.requireNonNull(order).getCustomerName();
        String seller = order.getSellerName();
        if (userMap.containsKey(buyer) && userMap.containsKey(seller)) {
            addBuyOrder(buyer, orderID);
            addSellOrder(seller, orderID);
        } else {
            throw new UnknownUserException();
        }
    }

    public static void completeOrder(String orderID, String username, String accessKey) throws UnauthorizedAccessException{
        accessCheck(username, accessKey);
        Order order = OrderManager.orders.get(orderID);
        String seller = order.getSellerName();
        if(seller.equals(username)) {
            order.changeOrderStatus();
            FoodTruck ft = FoodTruckManager.foodTrucks.get(username);
            ft.removeOrderWithID(orderID);
        }else{
            throw new UnauthorizedAccessException();
        }
    }
}

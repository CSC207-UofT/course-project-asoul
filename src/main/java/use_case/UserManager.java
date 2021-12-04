package use_case;

import entities.FoodTruck;
import entities.Order;
import entities.User;
import exceptions.*;
import serialization.Deserializer;
import serialization.Serializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import helper.RandomStringGenerator;
/**
 * A Use_case.UserManager that manages all the Users.
 */
public class UserManager{
    protected static HashMap<String, User> userMap = new HashMap<>(); // A map from user's account name to User object.
    protected static HashMap<String, User> loggedInUsers = new HashMap<>();
    private static final Serializer uSerializer = new Serializer();
    private static final Deserializer uDeserializer = new Deserializer();

    /**
     * @param accName  A String that represents the account Name.
     * @param password A String of password that the user typed in.
     * @throws IncorrectCredentialsException Exception if the password doesn't match the account name or there is no
     *                                       such account name.
     */
    public static String login(String accName, String password) throws IncorrectCredentialsException {
        try{
            User user = userMap.get(accName);
            String psw = user.getPassword();
            if(psw.equals(password)){
                RandomStringGenerator rg = new RandomStringGenerator();
                String key = rg.generateRandomString();
                while(loggedInUsers.containsKey(key)){ // Make sure the key is unique
                    key = rg.generateRandomString();
                }
                loggedInUsers.put(key, user);
                return key;
            }else{
                throw new IncorrectCredentialsException(); // Incorrect password entered
            }
        }catch (NullPointerException e){ // User does not exist
            throw new IncorrectCredentialsException();
        }
    }

    public static void logOut(String username, String accessKey) throws UnauthorizedAccessException {
        accessCheck(username, accessKey);
        loggedInUsers.remove(accessKey);
    }

    public static void userExist(String accName) throws UnknownUserException{
        if(!userMap.containsKey(accName)){
            throw new UnknownUserException();
        }
    }

    /**
     * @param accountName The username of the user that wants to add money.
     * @param money       The amount of money the user wants to add.
     */

    public static void addMoney(String accountName, double money) throws IncorrectArgumentException {
        User user = userMap.get(accountName);
        boolean addSuccess = user.addMoney(money);
        if (!addSuccess) {
            throw new IncorrectArgumentException();
        }

    }

    protected static void accessCheck(String username, String accessKey) throws UnauthorizedAccessException{
        User user = loggedInUsers.get(accessKey);
        try{
            if(!user.getAccountName().equals(username)){
                throw new UnauthorizedAccessException(); // Access key and username does not match
            }
        }catch (NullPointerException e){ // User does not exist or is not logged in
            throw new UnauthorizedAccessException();
        }
    }

    /**
     * @param accountName The user that wants to withdraw money.
     * @param money       The amount of money the user wants to withdraw.
     */

    public static void withdrawMoney(String accountName, double money) throws IncorrectArgumentException {
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
     * @param accName  The account name user wants to have.
     * @param password The password name user wants to have.
     * @param nickname The nickname user wants to have.
     * @param phoneNum The user's phone number.
     * @return true if the user created successfully.
     */
    public static boolean createUser(String accName, String password,
                                     String nickname, String phoneNum) {
        if (userMap.containsKey(accName)) {
            return false;
        }
        try {
            FoodTruckManager.createEmptyFoodTruck(accName);
            User newUser = new User(accName, password, nickname, phoneNum);
            userMap.put(accName, newUser);
        }catch (Exception e){
            e.printStackTrace();
            // Do nothing, this scenario can never happen
        }
        return true;
    }

    /**
     * Return the list of OrderHistory given a User account name.
     */
    public static HashSet<String> getBuyOrderHistory(String accName, String accessKey) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        User user = loggedInUsers.get(accessKey);
        return user.getBuyOrderHistory();
    }

    protected static HashSet<String> getBuyOrderHistory(String accName) throws UnknownUserException{
        userExist(accName);
        User user = userMap.get(accName);
        return user.getBuyOrderHistory();
    }

    public static HashSet<String> getSellOrderHistory(String accName, String accessKey) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        User user = loggedInUsers.get(accessKey);
        return user.getSellOrderHistory();
    }

    protected static HashSet<String> getSellOrderHistory(String accName) throws UnknownUserException{
        userExist(accName);
        User user = userMap.get(accName);
        return user.getSellOrderHistory();
    }


    /**
     * @param accountName the account name that want to check exists or not.
     * @return true if the account name already exists.
     */
    public static boolean checkUserExist(String accountName) {
        return userMap.containsKey(accountName);
    }

    // the account name must exist
    public static void logOutUser(String loginKey, String username) throws UnauthorizedAccessException {
        accessCheck(loginKey, username);
        loggedInUsers.remove(loginKey);
    }

    public static void setNickname(String accName, String accessKey, String nickname) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        userMap.get(accName).setNickname(nickname);
    }

    public static void setPhoneNumber(String accName, String accessKey, String phoneNumber) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        userMap.get(accName).setPhoneNumber(phoneNumber);
    }

    public static void setPassword(String username, String accessKey, String newPassword, String oldPassword)
            throws IncorrectOldPasswordException, UnauthorizedAccessException{
        accessCheck(username, accessKey);
        userMap.get(username).setPassword(newPassword, oldPassword);
    }

    public static String getNickname(String accName, String accessKey) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        return userMap.get(accName).getNickname();
    }

    public static String getPhoneNumber(String accName, String accessKey) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
        return userMap.get(accName).getPhoneNumber();
    }

    public static double getBalance(String accName, String accessKey) throws UnauthorizedAccessException{
        accessCheck(accName, accessKey);
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

    @SuppressWarnings("unchecked")
    public static void constructUserDataBase() throws IOException, ClassNotFoundException {
        uDeserializer.deserialize("./data/user info");
        HashMap<String, User> m = (HashMap<String, User>) uDeserializer.getObject();
        if(m != null){
            userMap = m;
        }
    }

    public static void saveUserDataBase() throws IOException {
        uSerializer.serialize("./data/user info", userMap);
    }

    // These two methods should only be called from within the class for convenience purposes
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

    public static String getTruckName(String username, String accessKey) throws UnauthorizedAccessException {
        accessCheck(username, accessKey);
        FoodTruck ft = FoodTruckManager.foodTrucks.get(username);
        return ft.getTruckName();
    }

    public static void completeOrder(String orderID, String username, String accessKey) throws UnauthorizedAccessException, NullPointerException{
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

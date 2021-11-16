package Use_case;

import Entities.FoodTruck;
import Entities.Order;
import Entities.User;
import Exceptions.IncorrectCredentialsException;
import Exceptions.IncorrectOldPasswordException;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Use_case.UserManager that manages all the Users.
 */
public class UserManager{
    protected static HashMap<String, User> userMap = new HashMap<>(); // A map from user's account name to Entities.User object.

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

    public static void addMoney(String accountName, int money) {
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

    public static void setPassword(String username, String newPassword, String oldPassword) throws IncorrectOldPasswordException {
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

    public static boolean pay(String payer, String payee, String password, double amount){
        User buyer = UserManager.userMap.get(payer);
        User seller = UserManager.userMap.get(payee);
        double balance = buyer.getAccountBalance();
        if(balance < amount){
            return false; // buyer does not have enough money to pay
        }
        if(!password.equals(buyer.getPassword())){
            return false;
        }
        double newBalance = balance - amount;
        buyer.setAccountBalance(newBalance);
        seller.addMoney(amount);
        return true;
    }

    @SuppressWarnings("unchecked")
    public static void constructUserDataBase() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/user info"));
            userMap = (HashMap<String, User>) ois.readObject();
            ois.close();
        }catch(EOFException e){
            // Do nothing, no seller has been registered
        }
    }

    public static void saveUserDataBase() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/user info"));
        oos.writeObject(userMap);
        oos.flush();
        oos.close();
    }
}

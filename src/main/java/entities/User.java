package entities;

import exceptions.IncorrectArgumentException;
import exceptions.IncorrectOldPasswordException;
import exceptions.InsufficientBalanceException;
import exceptions.UnknownOrderException;

import java.io.Serializable;
import java.util.HashSet;

public class User implements Serializable {
    private final String accountName; //The account name of this User
    private double accountBalance; //The current account balance of this User (in double)
    private String password; //The string representing the password of this User's account
    private String nickname; //The nickname of this User
    private String phoneNumber; //A string that represents the phone number of this User
    private final HashSet<String> buyOrderHistory; // order id
    private final HashSet<String> sellOrderHistory; // order id
    private final HashSet<String> buyInProgress; //order id
    private final HashSet<String> sellInProgress; //order id

    /**
     * Construct an instance of a Entities.User
     *
     * @param accName     The account name of this Entities.User
     * @param password    The password of this Entities.User account
     * @param nickname    The nickname of this Entities.User
     * @param phoneNumber A string representing the phone number of this Entities.User
     */
    public User(String accName, String password, String nickname, String phoneNumber) {
        this.accountName = accName;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.buyOrderHistory = new HashSet<>();
        accountBalance = 0.0;
        this.sellOrderHistory = new HashSet<>();
        this.sellInProgress = new HashSet<>();
        this.buyInProgress = new HashSet<>();
    }

    public String toString() {
        return "Account Name: " + this.getAccountName() + "; Password: " + this.getPassword() + "; Nickname: " +
                this.getNickname() + "; PhoneNumber: " + this.getPhoneNumber() + ".";
    }


    /**
     * Add money to this Entities.User's account balance.
     *
     * @param money The amount of money in double that will be added to the account balance.
     * @return Return True if successfully added and False otherwise.
     */
    public boolean addMoney(double money) { // we are going to use the return value later.
        if (money >= 0.0) {
            this.accountBalance = this.accountBalance + money;
            return true;
        }
        return false;
    }

    /**
     * Withdraw money from this Entities.User's account balance.
     *
     * @param money The amount of money in double that will be withdrawn from the account balance.
     */
    public void withdrawMoney(double money) throws InsufficientBalanceException, IncorrectArgumentException {
        if (money < 0) {
            throw new IncorrectArgumentException();
        }
        if (money > accountBalance) {
            throw new InsufficientBalanceException();
        }
        accountBalance -= money;
    }

    /**
     * @param nickname the new nickname the user wants to have.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @param phoneNumber user's new phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @param newPassword user's new password.
     * @param oldPassword user's original password.
     * @throws IncorrectOldPasswordException if the old password is not correct.
     */
    public void setPassword(String newPassword, String oldPassword) throws IncorrectOldPasswordException {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        } else {
            throw new IncorrectOldPasswordException();
        }
    }

    /**
     * @param id order id
     * @throws UnknownOrderException if there is no order with this id.
     */
    public void completeOrder(String id) throws UnknownOrderException {
        if (sellInProgress.contains(id)) {
            sellInProgressToHistory(id);
            return;
        }
        if (buyInProgress.contains(id)) {
            buyInProgressToHistory(id);
            return;
        }
        throw new UnknownOrderException();
    }

    // the rests are getters.
    private void buyInProgressToHistory(String id) {
        this.buyInProgress.remove(id);
        this.buyOrderHistory.add(id);
    }

    private void sellInProgressToHistory(String id) {
        this.sellInProgress.remove(id);
        this.sellOrderHistory.add(id);
    }

    public void storeBuyOrder(String orderID) { // we are going to use the return value later.
        this.buyInProgress.add(orderID);
    }

    public void storeSellOrder(String orderID) { // we are going to use the return value later.
        this.sellInProgress.add(orderID);
    }

    /**
     * Getting for all the instance variables
     */

    public String getAccountName() {
        return this.accountName;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public HashSet<String> getBuyOrderHistory() {
        return buyOrderHistory;
    }

    public HashSet<String> getSellOrderHistory() {
        return sellOrderHistory;
    }

    public HashSet<String> getBuyInProgress() {
        return buyInProgress;
    }

    public HashSet<String> getSellInProgress() {
        return sellInProgress;
    }
}

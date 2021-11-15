package Entities;

import Exceptions.IncorrectOldPasswordException;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private final String accountName; //The account name of this Entities.User
    private double accountBalance; //The current account balance of this Entities.User (in double)
    private String password; //The string representing the password of this Entities.User's account
    private String nickname; //The nickname of this Entities.User
    private String phoneNumber; //A string that represents the phone number of this Entities.User
    private boolean login; //Login status. True if logged in, False otherwise.
    private ArrayList<String> orderHistory;

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
        this.orderHistory = new ArrayList<>();
        accountBalance = 0.0;
        login = false;
    }

    public String toString() {
        return "Account Name: " + this.getAccountName() + "; Password: " + this.getPassword() + "; Nickname: " +
                this.getNickname() + "; PhoneNumber: " + this.getPhoneNumber() + ".";
    }

    /**
     * Login to the account.
     *
     * @param password The password of this Entities.User account
     * @return Return True if the account is logged in and False otherwise.
     */
    public boolean login(String password) {
        if (this.password.equals(password)) {
            this.login = true;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Logout to the account.
     *
     * @return Return True if the account is logged out and False otherwise.v
     */
    public boolean logout() {
        if (!this.login) {
            return false;
        } else {
            this.login = false;
            return true;
        }
    }

    /**
     * Add money to this Entities.User's account balance.
     *
     * @param money The amount of money in double that will be added to the account balance.
     * @return Return True if successfully added and False otherwise.
     */
    public boolean addMoney(double money) { // we are going to use the return value later.
        try {
            this.accountBalance += money;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Check the current account balance
     *
     * @return Return the Balance of this Entities.User account in double.
     */
    public double checkBalance() {
        return this.accountBalance;
    }

    /**
     * Withdraw money from this Entities.User's account balance.
     *
     * @param money The amount of money in double that will be withdrawn from the account balance.
     * @return Return True if successfully withdrawn and False otherwise.
     */
    public boolean withdrawMoney(double money) { // we are going to use the return value later.
        if (this.accountBalance < money) {
            return false;
        } else {
            this.accountBalance -= money;
            return true;
        }
    }
    public void setAccountBalance(double amount){this.accountBalance = amount;}

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String newPassword, String oldPassword) throws IncorrectOldPasswordException {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        } else {
            throw new IncorrectOldPasswordException();
        }
    }

    public boolean storeOrder(String orderID) { // we are going to use the return value later.
        try {
            this.orderHistory.add(orderID);
        } catch (Exception e) {
            return false;
        }
        return true;
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

    public boolean getLoginStatus() {
        return this.login;
    }

    public ArrayList<String> getOrderHistory() {
        return orderHistory;
    }
}

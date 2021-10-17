public abstract class User {
    private String accountName; //The account name of this User
    private double accountBalance; //The current account balance of this User (in double)
    private String password; //The string representing the password of this User's account
    private String nickname; //The nickname of this User
    private String phoneNumber; //A string that represents the phone number of this User
    private boolean login; //Login status. True if logged in, False otherwise.

    /**
     * Construct an instance of a User
     * @param accName The account name of this User
     * @param password The password of this User account
     * @param nickname The nickname of this User
     * @param phoneNumber A string representing the phone number of this User
     */
    public User(String accName, String password, String nickname, String phoneNumber){
        this.accountName = accName;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        accountBalance = 0.0;
        login = false;
    }

    /**
     * Login to the account. Return True if the account is logged in and False otherwise.
     * @param password The password of this User account
     */
    public boolean login(String password){
        if (this.password.equals(password)){
            this.login = true;
            System.out.println("Log in success!");
            return true;
        }else{
            System.out.println("Password incorrect!");
            return false;
        }
    }

    /**
     * Logout to the account. Return True if the account is logged out and False otherwise.
     */
    public boolean logout(){
        if (!this.login){
            System.out.println("You need log in first");
            return false;
        }else{
            this.login = false;
            System.out.println("Log out success!");
            return true;
        }
    }

    /**
     * Add money to this User's account balance. Return True if successfully added and False otherwise.
     * @param money The amount of money in double that will be added to the account balance.
     */
    public boolean addMoney(double money){
        try{
            this.accountBalance += money;
        }catch (Exception e){
            System.out.println("Add fail");
            return false;
        }
        System.out.println("Add success!");
        return true;
    }

    /**
     * Return the Balance of this User account in double.
     */
    public double checkBalance(){
        System.out.println("Your current balance is: " + this.accountBalance);
        return this.accountBalance;
    }

    /**
     * Withdraw money from this User's account balance. Return True if successfully withdrawn and False otherwise.
     * @param money The amount of money in double that will be withdrawn from the account balance.
     */
    public boolean withdrawMoney(double money){
        if (this.accountBalance < money){
            System.out.println("Insufficient balance!");
            return false;
        }else{
            this.accountBalance -= money;
            System.out.println("Withdraw Success!");
            return true;
        }
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

    public boolean getLoginStatus(){
        return this.login;
    }
}

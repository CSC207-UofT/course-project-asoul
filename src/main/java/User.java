public abstract class User {
    private String accountName;
    private double accountBalance;
    private String password;
    private String nickname;
    private String phoneNumber;
    private boolean login;

    public User(String accName, String password, String nickname, String phoneNumber){
        this.accountName = accName;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        accountBalance = 0.0;
        login = false;
    }
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


    public double checkBalance(){
        System.out.println("Your current balance is: " + this.accountBalance);
        return this.accountBalance;
    }

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

}

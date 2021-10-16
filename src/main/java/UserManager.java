import java.util.ArrayList;

abstract public class UserManager {

    abstract public boolean login(String accName, String password);

    public void addMoney(User user, int money) {
        user.addMoney(money);
    }

    public void withdrawMoney(User user, int money) {
        user.withdrawMoney(money);
    }

    public double checkBalance(User user) {
        return user.checkBalance();
    }

}

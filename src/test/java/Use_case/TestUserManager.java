package Use_case;

import Exceptions.IncorrectCredentialsException;
import org.junit.Test;

public class TestUserManager {
    @org.junit.Test
    public void createUserTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        assert UserManager.checkUserExist("a");
    }


    @org.junit.Test
    public void loginTest() throws IncorrectCredentialsException {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        UserManager.login("a", "b");
        assert UserManager.userMap.get("a").getLoginStatus();
    }

    @org.junit.Test
    public void addMoneyTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        UserManager.addMoney("a", 100);
        assert UserManager.userMap.get("a").getAccountBalance() == 100;
    }

    @org.junit.Test
    public void withdrawMoneyTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        userManager.withdrawMoney("a", 50);
        assert UserManager.userMap.get("a").getAccountBalance() == 50;
    }

//    @org.junit.Test
//    public void checkBalanceTest() {
//        UserManager userManager = new UserManager();
//        userManager.createUser("a", "b", "c", "c");
//        assert UserManager.userMap.get("a").checkBalance() == 50;
//    }

    @org.junit.Test
    public void checkUserExistTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        assert UserManager.checkUserExist("a");
    }

    @org.junit.Test
    public void deleteUserTest() {
        UserManager userManager = new UserManager();
        userManager.createUser("a", "b", "c", "c");
        UserManager.deleteUser("a");
        assert !UserManager.checkUserExist("a");
    }


}

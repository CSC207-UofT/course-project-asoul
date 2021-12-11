package use_case;


import exceptions.*;

public class TestUserManager {

    @org.junit.Test
    public void createUserTest() throws Exception {
        UserManager.createUser("a", "a", "tom", "a");
        assert UserManager.getNickname("a").equals("tom");
    }

    @org.junit.Test
    public void createUserUnknownTest() {
        try {
            UserManager.getNickname("b");
        } catch (Exception e) {
            assert e instanceof UnknownUserException;
        }

    }

    @org.junit.Test
    public void addMoneyTest() throws IncorrectArgumentException, UnknownUserException {
        UserManager.createUser("b", "a", "tom", "a");
        UserManager.addMoney("b", 100);
        double a = UserManager.getUser("b").getAccountBalance();
        assert a == 100;
    }

    @org.junit.Test
    public void withdrawMoneyTest() throws IncorrectArgumentException, UnknownUserException, InsufficientBalanceException {
        UserManager.createUser("c", "a", "tom", "a");
        UserManager.addMoney("c", 100);
        UserManager.withdrawMoney("c", 50);
        double a = UserManager.getUser("c").getAccountBalance();
        assert a == 50;
    }

    @org.junit.Test
    public void loginTest() throws IncorrectCredentialsException {
        assert UserManager.login("b", "a") != null;

    }

    @org.junit.Test
    public void logOutTest() {
        try {
            String a = UserManager.login("c", "a");
            UserManager.logOut("c", a);
        } catch (Exception e) {
            assert false;
        }
    }


    @org.junit.Test
    public void getBuyOrderHistoryTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("d", "d", "d", "d");
        String a = UserManager.login("d", "d");
        assert UserManager.getBuyOrderHistory("d", a).isEmpty();
    }

    @org.junit.Test
    public void getSellOrderHistoryTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("e", "e", "d", "d");
        String a = UserManager.login("e", "e");
        assert UserManager.getSellOrderHistory("e", a).isEmpty();
    }

    @org.junit.Test
    public void getBuyInProgressTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("f", "f", "d", "d");
        String a = UserManager.login("f", "f");
        assert UserManager.getBuyInProgress("f", a).isEmpty();
    }

    @org.junit.Test
    public void getSellInProgressTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("g", "f", "d", "d");
        String a = UserManager.login("g", "f");
        assert UserManager.getSellInProgress("g", a).isEmpty();
    }

}


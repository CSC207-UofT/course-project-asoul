package entities;

import exceptions.IncorrectArgumentException;
import exceptions.InsufficientBalanceException;

public class TestUser {
    User user;

    @org.junit.Before
    public void Setup(){
        user = new User("Yx", "yxyyds", "yuanxiao", "110108");
    }

    @org.junit.Test
    public void addWithdrawMoneyTest() throws InsufficientBalanceException, IncorrectArgumentException {
        user.addMoney(1);
        assert user.getAccountBalance() == 1;
        user.withdrawMoney(1);
        assert user.getAccountBalance() == 0;
    }

    @org.junit.Test
    public void testGetAccountName() {
        assert user.getAccountName().equals("Yx");
    }

    @org.junit.Test
    public void testGetAccountBalance() {
        assert user.getAccountBalance() == 0;
    }

    @org.junit.Test
    public void testGetNickname() {
        assert user.getAccountName().equals("yuanxiao");
    }

    @org.junit.Test
    public void testGetPassword() {
        assert user.getPassword().equals("yxyyds");
    }

    @org.junit.Test
    public void testGetPhoneNumber() {
        assert user.getPhoneNumber().equals("110108");
    }

    @org.junit.Test
    public void testSetNickname() {
        user.setNickname("12344");
        assert user.getNickname().equals("12344");
    }

    @org.junit.Test
    public void testSetPhoneNumber() {
        user.setPhoneNumber("12344");
        assert user.getPhoneNumber().equals("12344");
    }
}
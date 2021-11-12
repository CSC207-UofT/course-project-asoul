package Use_case;

import Entities.Customer;
import org.junit.Test;

import java.util.HashMap;

public class TestCustomerManager {

    @Test
    public void loginTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        Boolean actual = manager.checkUserExist("ABC");
        assert actual.equals(true);
        manager.deleteUser("ABC");
    }


    @org.junit.Test
    public void addMoneyTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        double actual = manager.checkBalance("ABC");
        assert actual == 100;
        manager.deleteUser("ABC");
    }


    @org.junit.Test
    public void withdrawTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        manager.withdrawMoney("ABC", 50);
        double actual = manager.checkBalance("ABC");
        assert actual == 50;
        manager.deleteUser("ABC");
    }

    @org.junit.Test
    public void checkBalanceTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        manager.addMoney("ABC", 100);
        double actual = manager.checkBalance("ABC");
        assert actual == 200;
        manager.deleteUser("ABC");
    }


    @org.junit.Test
    public void getUserByAccountNameTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        HashMap<String, String> actual = manager.getUserByAccountName("ABC");
        HashMap<String, String> expected = new HashMap<>();
        expected.put("accName", "ABC");
        expected.put("accBalance", "0.0");
        expected.put("password", "123");
        expected.put("nickname", "nick");
        expected.put("phoneNum", "12345");
        expected.put("orderHistory", "");
        assert actual.equals(expected);
        manager.deleteUser("ABC");

    }

    @org.junit.Test
    public void getUserTypeTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        String type = manager.getUserType("ABC");
        assert type.equals("Entities.Customer");
        manager.deleteUser("ABC");
    }


    @org.junit.Test
    public void checkUserExistTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        Boolean actual = manager.checkUserExist("ABC");
        assert actual.equals(true);
        manager.deleteUser("ABC");

    }

    @org.junit.Test
    public void getCustomersTest() {
        CustomerManager manager = new CustomerManager();
        manager.createUser("Entities.Customer", "ABC", "123", "nick",
                "12345");
        Customer customer = new Customer("ABC", "123", "nick",
                "12345");
        int actual = manager.getCustomers().size();
        assert actual == 1;
        manager.deleteUser("ABC");

    }

}

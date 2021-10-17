import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TestCustomerManager {
    CustomerManager manager;


    @org.junit.Before
    public void setUp() {

        manager = new CustomerManager();

    }


    @org.junit.Test
    public void loginTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        Boolean actual = manager.checkUserExist("ABC");
        assert actual.equals(true);
    }


    @org.junit.Test
    public void addMoneyTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        double actual = manager.checkBalance("ABC");
        assert actual == 100;

    }


    @org.junit.Test
    public void withdrawTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        manager.withdrawMoney("ABC", 50);
        double actual = manager.checkBalance("ABC");
        assert actual == 50;
    }

    @org.junit.Test
    public void checkBalanceTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        manager.addMoney("ABC", 100);
        manager.addMoney("ABC", 100);
        double actual = manager.checkBalance("ABC");
        assert actual == 200;
    }


    @org.junit.Test
    public void getUserByAccountNameTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
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

    }

    @org.junit.Test
    public void getUserTypeTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        String type = manager.getUserType("ABC");
        assert type.equals("Customer");
    }

    @org.junit.Test
    public void checkUserExistTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        Boolean actual = manager.checkUserExist("ABC");
        assert actual.equals(true);

    }

    @org.junit.Test
    public void getCustomersTest() {
        manager.createUser("Customer", "ABC", "123", "nick",
                "12345");
        Customer customer = new Customer("ABC", "123", "nick",
                "12345");
        int actual = manager.getCustomers().size();
        assert actual == 1;

    }

}

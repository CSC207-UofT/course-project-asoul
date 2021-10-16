import java.util.HashMap;

public class TestCustomerManager {
    CustomerManager manager;


    @org.junit.Before
    public void setUp() throws Exception {
        HashMap<String, Customer> map = new HashMap<>();

        manager = new CustomerManager(map);

    }

    @org.junit.Test
    public void creatCustomerTest() {
        Boolean result = manager.creatCustomer("a", "123", "Tom", "123456");
        assert result.equals(true);
    }

    @org.junit.Test
    public void creatCustomerTest2() {
        manager.creatCustomer("a", "123", "Tom", "123456");
        Boolean result = manager.creatCustomer("a", "123", "To", "123456");
        assert result.equals(false);
    }

    @org.junit.Test
    public void getCustomerTest() {
        manager.creatCustomer("a", "123", "Tom", "123456");
        manager.creatCustomer("ab", "123", "Tom", "123456");
        assert manager.getCustomers().size() == 2;

    }
}

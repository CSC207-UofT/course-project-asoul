import java.util.ArrayList;
import java.util.HashMap;

public class TestSellerManager {
    SellerManager manager;

    @org.junit.Before
    public void setUp() throws Exception {
        HashMap<String, Seller> map = new HashMap<>();

        manager = new SellerManager();
    }

    @org.junit.Test
    public void createSellerTest() {
        Boolean result = manager.creatSeller("123",
                "1234", "Tom", "2167");
        assert result.equals(true);
    }

    @org.junit.Test
    public void addFoodTruckTest() {
        manager.creatSeller("123",
                "1234", "Tom", "2167");
        FoodMenu menu = new FoodMenu(new ArrayList<>());
        FoodTruck truck = new FoodTruck("a", "Toronto", "12:00",
                "15:00", manager.getSellers().get(0), menu);

        Boolean result = manager.addFoodTruck("123", truck);
        assert result.equals(true);

    }

    @org.junit.Test
    public void getCustomerTest() {
        manager.creatSeller("a", "123", "Tom", "123456");
        manager.creatSeller("ab", "123", "Tom", "123456");
        assert manager.getSellers().size() == 2;
    }
}

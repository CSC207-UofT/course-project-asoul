import java.awt.*;
import java.util.ArrayList;

public class TestOrder {
    Order order;

    @org.junit.Before
    public void Setup() throws Exception {
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Italian");
        labels.add("Fast food");
        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        ArrayList<Food> foodList = new ArrayList<Food>();

        foodList.add(pizza);
        FoodMenu menu = new FoodMenu(foodList);

        Seller seller = new Seller("David", "BigBoss", "D.", "6478863531");

        FoodTruck foodTruck = new FoodTruck("Ideal Catering", "Bahen", "8:00",
                "18:00", seller, menu);
        order = new Order(1, foodTruck, foodList, "Paul", "4169990000",
                "David", "6478863531");
    }


    /**
     * Test changeOrderStatus method
     */
    @org.junit.Test
    public void changeOrderStatusTest(){
        assert order.getStatus().equals("order created");

        Boolean result1 = order.changeOrderStatus();
        assert result1;
        assert order.getStatus().equals("in progress");

        Boolean result2 = order.changeOrderStatus();
        assert result2;
        assert order.getStatus().equals("order completed");

        Boolean result3 = order.changeOrderStatus();
        assert !result3;
        assert order.getStatus().equals("order completed");
    }


    /**
     * Test rateOrder method
     */
    @org.junit.Test
    public void rateOrderTest(){
        assert order.getRating().equals(-0.1);

        Boolean result1 = order.rateOrder(9.5);
        assert result1;
        assert order.getRating().equals(9.5);

        Boolean result2 = order.rateOrder(11.1);
        assert !result2;
        assert order.getRating().equals(9.5);
    }


    /**
     * Test getFoodList method
     */
    @org.junit.Test
    public void getFoodListTest(){
        String result = order.getFoodList();
        assert result.equals("Pizza : $5.0");
    }


    /**
     * Test toString method
     */
    @org.junit.Test
    public void toStringTest(){
        String result = order.toString();
        String answer = "1\nPaul : 4169990000\nIdeal Catering : 6478863531\nPizza : $5.0\nTotal :" +
                " $5.0\norder received";
        assert result.equals(answer);
        order.changeOrderStatus();
        String result2 = order.toString();
        String answer2 = "1\nPaul : 4169990000\nIdeal Catering : 6478863531\nPizza : $5.0\nTotal :" +
                " $5.0\nin Progress";
    }
}

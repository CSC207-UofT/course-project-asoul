package Entities;

import java.util.ArrayList;

public class TestOrder {
    Order order;

    @org.junit.Before
    public void Setup() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("Italian");
        labels.add("Fast food");
        Food pizza = new Food("Pizza", 5.00, 1, labels,
                "One large slice of Hawaii Piazza");
        ArrayList<Food> foodList = new ArrayList<>();

        foodList.add(pizza);
        FoodMenu menu = new FoodMenu(foodList);

        User seller = new User("David", "BigBoss", "D.", "6478863531");

        FoodTruck foodTruck = new FoodTruck("Ideal Catering", "Bahen", "8:00",
                "18:00", seller.getAccountName(), menu);
        order = new Order(foodTruck, foodList, "Paul", "4169990000",
                "David", "6478863531", 1);
    }


    /**
     * Test changeOrderStatus method
     */
    @org.junit.Test
    public void changeOrderStatusTest() throws Exception {
        assert order.getStatus().equals("in progress");

        String result1 = order.changeOrderStatus();
        assert result1.equals("Change Successfully");
        assert order.getStatus().equals("order completed");

        String result2 = order.changeOrderStatus();
        assert result2.equals("Change Failed");
        assert order.getStatus().equals("order completed");
    }


    /**
     * Test rateOrder method
     */
    @org.junit.Test
    public void rateOrderTest() throws Exception {
        assert order.getRating().equals("No Rating");

        boolean result1 = order.rateOrder(9.5);
        assert result1;
        assert order.getRating().equals(9.5);

        boolean result2 = order.rateOrder(11.1);
        assert !result2;
        assert order.getRating().equals(9.5);
    }


    /**
     * Test getFoodList method
     */
    @org.junit.Test
    public void getFoodListTest() {
        String result = order.getFoodList();
        assert result.equals("Pizza : $5.0");
    }


    /**
     * Test toString method
     */
    @org.junit.Test
    public void toStringTest() throws Exception {
        String result = order.toString();
        String answer = "Order Id: 1\n" +
                "Order Time: " + order.getFormattedTime() + "\n" +
                "Customer Name: Paul\n" +
                "Customer Number: 4169990000\n" +
                "Food Truck: Ideal Catering\n" +
                "Seller Name: David\n" +
                "Seller Number: 6478863531\n" +
                "Food List: Pizza : $5.0\n" +
                "Total Price: $5.0\n" +
                "Status: in progress\n" +
                "Rating: No Rating";
        assert result.equals(answer);
        order.changeOrderStatus();
        String result2 = order.toString();
        String answer2 = "Order Id: 1\n" +
                "Order Time: " + order.getFormattedTime() + "\n" +
                "Customer Name: Paul\n" +
                "Customer Number: 4169990000\n" +
                "Food Truck: Ideal Catering\n" +
                "Seller Name: David\n" +
                "Seller Number: 6478863531\n" +
                "Food List: Pizza : $5.0\n" +
                "Total Price: $5.0\n" +
                "Status: order completed\n" +
                "Rating: No Rating";
        assert result2.equals(answer2);
    }
}

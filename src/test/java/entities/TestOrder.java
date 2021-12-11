package entities;

import exceptions.CollidedFoodException;
import exceptions.FoodIdCollisionException;


public class TestOrder {
    Order order;

    @org.junit.Before
    public void Setup() throws CollidedFoodException, FoodIdCollisionException {

        Food pizza = new Food("Pizza", 5.00, "One large slice of Hawaii Piazza");

        FoodMenu menu = new FoodMenu();
        menu.addFood(pizza, "1");

        order = new Order("1", "William", "Paul", "4169990000",
                "David", "D.", "6478863531", "Ideal Catering");
    }

    /**
     * Test changeOrderStatus method
     */
    @org.junit.Test
    public void getDescriptionTest() {
        String result = "Date: " + order.getFormattedTime() + "\n" +
                "TruckName: " + "Ideal Catering" + "\n";
        assert order.getDescription().equals(result);
    }

    /**
     * Test changeOrderStatus method
     */
    @org.junit.Test
    public void toStringTest() {
        String result = "Order Time: " + order.getFormattedTime() + "\n" +
                "Buyer Name: " + "Paul" + "\n" +
                "Buyer Number: " + "4169990000" + "\n" +
                "Seller Name: " + "D." + "\n" +
                "Seller Number: " + "6478863531" + "\n" +
                "1" + "\n" +
                "Status: " + "in progress" + "\n" +
                "Rating: " + "TBD" + "\n";
        assert order.toString().equals(result);
    }


    /**
     * Test changeOrderStatus method
     */
    @org.junit.Test
    public void changeOrderStatusTest() {
        order.changeOrderStatus();
        String result = "Order Time: " + order.getFormattedTime() + "\n" +
                "Buyer Name: " + "Paul" + "\n" +
                "Buyer Number: " + "4169990000" + "\n" +
                "Seller Name: " + "D." + "\n" +
                "Seller Number: " + "6478863531" + "\n" +
                "1" + "\n" +
                "Status: " + "order completed" + "\n" +
                "Rating: " + 0.0 + "" + "\n";
        assert order.toString().equals(result);
    }

    @org.junit.Test
    public void getBuyerTest() {
        assert order.getBuyer().equals("William");
    }

    @org.junit.Test
    public void getSellerTest() {
        assert order.getSeller().equals("David");
    }

    @org.junit.Test
    public void getSellerNameTest() {
        assert order.getSellerName().equals("D.");
    }

    @org.junit.Test
    public void getSellerNumberTest() {
        assert order.getSellerNumber().equals("6478863531");
    }
}



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FoodTruckScene extends Scene {
    private FoodTruck foodTruck;
    private String foodTruckName;
    private ArrayList<String> cart;
    private final FoodTruckManager ftm;
    private final OrderManager om;
    private CustomerManager cm;
    private SellerManager sm;
    private User customer;
    private String cusName;
    private User seller;
    private int orderID;
    private boolean checkOut;

    public FoodTruckScene() {
        super("FoodTruck");
        ftm = foodTruckManager;
        om = orderManager;
        cm = customerManager;
        sm = sellerManager;
        checkOut = false;
    }


    @Override
    public String constructOutputString() {
        return foodTruckName + "\n" + ftm.getRating(foodTruckName) + "\n" +
                ftm.getMenu(foodTruckName) + "----------------Cart---------------" + cart;
    }


    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name) {
        this.foodTruck = ftm.getFoodTruckById(name);
        this.foodTruckName = name;
    }

    public void setUsername(String name) {
        this.customer = cm.returnUser(name);
        this.cusName = name;
    }

    public void handleInput(String input) {
        String[] text = input.split(" ");
        if (input.equals("back")) {
            cart = new ArrayList<String>();
            this.switchScene(Scene.allScenes.get("Market"));
        } else if (input.equals("check out")) {
            ArrayList<Food> foodList = om.getMenuFood(this.cart, this.foodTruck);
            
            orderID = om.creatOrder(this.foodTruck, foodList, cm.getNickname(cusName), cm.getPhoneNumber(cusName),
                    sm.getNickname(), sm.getPhoneNumber());
        } else if (text[0].equals("select")) {
            String[] foods = Arrays.copyOfRange(text, 1, text.length);
            Collections.addAll(cart, foods);
        }

    }


}
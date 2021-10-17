import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FoodTruckScene extends Scene{
    private FoodTruck foodTruck;
    private String foodTruckName;
    private ArrayList<String> cart;
    private final FoodTruckManager ftm;
    private final OrderManager om;
    private UserManager um;
    private User customer;
    private User seller;

    public FoodTruckScene() {
        super("FoodTruck");
        ftm = new FoodTruckManager();
        om = new OrderManager();
    }


    @Override
    public String constructOutputString() {
    }


    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name){
        this.foodTruck = ftm.getFoodTruckById(name);
        seller = this.foodTruck.getSeller();
    }

    public void setUsername(String name){
        this.customer = um.returnUser(name);
    }

    public void handleInput(String input){
        String[] text = input.split(" ");
        if (input.equals("back")){
            cart = new ArrayList<String>();
            this.switchScene(Scene.allScenes.get("Market"));
        }else if(input.equals("check out")){
            ArrayList<Food> foodList = om.getMenuFood(this.cart, this.foodTruck);

            om.creatOrder(this.foodTruck, foodList, this.customer.getNickname(), this.customer.getPhoneNumber(),
                    this.seller.getAccountName(), this.seller.getPhoneNumber())
        }else if(text[0].equals("select")){
            String[] foods = Arrays.copyOfRange(text, 1, text.length);
            Collections.addAll(cart, foods);
        }
    }
}

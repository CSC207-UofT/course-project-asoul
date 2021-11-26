package default_scene_implementation;

import controllers.Scene;
import use_case.FoodTruckManager;

import java.util.ArrayList;

public class FoodTruckScene extends Scene {
    public String foodTruckName;
    private ArrayList<Integer> cart;
    private String cusName;
    private int orderID; // we are going to use it later, so it can't be local variable.
    private boolean checkOut; // we are going to use it later, so it can't be local variable.

    public FoodTruckScene() {
        super("Entities.FoodTruck");
        checkOut = false;
    }


//    @Override
//    public String constructOutputString() {
//        return foodTruckName + "\n" + "rating : " + ftm.getRating(foodTruckName) + "\n" +
//                ftm.getMenu(foodTruckName) + "\n----------------Cart---------------" + cart;
//    }


    /**
     * set Foodtruck name to name
     */
    public void setFoodTruck(String name) {
//        this.foodTruck = FoodTruckManager.getFoodTruckById(name);
        this.foodTruckName = name;
    }

    public void setUsername(String name) {
        this.cusName = name;
    }

    public void selectFood(int id, int num){
        int i = 0;
        while (!(i == num)){
            i = i + 1;
            cart.add(id);
        }
    }

    public void removeFood(int id, int num){
        int i = 0;
        while (!(i == num)){
            i = i + 1;
            if (cart.contains(id)){
                cart.remove(id);
            }
        }
    }

    public boolean checkValidFood(int id){
        return FoodTruckManager.checkFoodFromFTMenu(id, foodTruckName);
    }


 //   public int chekOut(String password){
 //       Double cartTotal =  FoodTruckManager.checkOut(foodTruckName, cart);
 //       String seller = FoodTruckManager.getUserByFTName(foodTruckName);
 //       if (UserManager.pay(cusName, seller, password, cartTotal)){
 //           OrderScene orderScene = (OrderScene) Scene.allScenes.get("order");
 //           orderID = OrderManager.createOrder(FoodTruckManager.getFoodTruckById(foodTruckName),
 //                   FoodTruckManager.getFoodById(cart), cusName, UserManager.getPhoneNumber(cusName), seller,
 //                   UserManager.getPhoneNumber(seller));
 //           orderScene.setOrderID(orderID);
 //           return orderID;
  //      }

  //      return -1;
  //  }


 //   public StringBuilder printCart(){
 //       String[] cartFoodList = FoodTruckManager.getFoodById(cart);
  //      StringBuilder result = new StringBuilder("Your cart: \n");
 //       for (String each: cartFoodList){
//            result.append(each);
 //           result.append("\n");
 //       }
 //       Double cartTotal =  FoodTruckManager.checkOut(foodTruckName, cart);
 //       result.append("total: ");
 //       result.append(cartTotal);
 //       return result;
 //   }
}
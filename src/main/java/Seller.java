import java.util.ArrayList;

public class Seller extends User{
    private ArrayList<FoodTruck> ownedFoodTruck;

    public Seller(String accName, String password, String nickname, String phoneNumber) {
        super(accName, password, nickname, phoneNumber);
        this.ownedFoodTruck = new ArrayList<FoodTruck>();
    }

    public boolean addFoodTruck(FoodTruck foodtruck){
        //comment
        try{
            ownedFoodTruck.add(foodtruck);
        }catch (Exception e){
            System.out.println("Add fail!");
            return false;
        }
        System.out.println("Add success!");
        return true;
    }

    public boolean removeFoodTruck(FoodTruck foodtruck){
        try{
            ownedFoodTruck.remove(foodtruck);
        }catch (Exception e){
            System.out.println("Remove Fail!");
            return false;
        }
        System.out.println("Remove Success!");
        return true;
    }

    public ArrayList<FoodTruck> getFoodTruck(){
        return this.ownedFoodTruck;
    }




}

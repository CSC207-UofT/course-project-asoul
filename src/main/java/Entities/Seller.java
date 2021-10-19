package Entities;


import java.util.ArrayList;

public class Seller extends User {
    private final ArrayList<FoodTruck> ownedFoodTruck; //A List stored all FoodTrucks this Entities.Seller owned

    /**
     * Construct an instance of a Entities.Seller
     *
     * @param accName     The account name of this Entities.Seller
     * @param password    The password of this Entities.Seller account
     * @param nickname    The nickname of this Entities.Seller
     * @param phoneNumber A string representing the phone number of this Entities.Seller
     */

    public Seller(String accName, String password, String nickname, String phoneNumber) {
        super(accName, password, nickname, phoneNumber);
        this.ownedFoodTruck = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Account Name: " + this.getAccountName() + "; Password: " + this.getPassword()
                + "; Nickname: " + this.getNickname() + "; PhoneNumber: " + this.getPhoneNumber() + ".";
    }

    /**
     * Add a foodtruck to ownedFoodTruck that this seller owned.
     *
     * @param foodtruck the foodtruck that want to be added to the ownedFoodTruck.
     */
    public void addFoodTruck(FoodTruck foodtruck) {
        //comment
        try {
            ownedFoodTruck.add(foodtruck);
        } catch (Exception e) {
            System.out.println("Add fail!");
            return;
        }
        System.out.println("Add success!");
    }

    /**
     * Remove a foodtruck from ownedFoodTruck that this seller owned.
     *
     * @param foodtruck the foodtruck that want to be removed from the ownedFoodTruck.
     */
    public void removeFoodTruck(FoodTruck foodtruck) {
        try {
            ownedFoodTruck.remove(foodtruck);
        } catch (Exception e) {
            System.out.println("Remove Fail!");
            return;
        }
        System.out.println("Remove Success!");
    }

    /**
     * To return a list of all owned FoodTrucks of this Entities.Seller.
     *
     * @return a list of all owned FoodTrucks of this Entities.Seller.
     */
    public ArrayList<FoodTruck> getFoodTruck() {
        return this.ownedFoodTruck;
    }


}

import java.util.ArrayList;

public class Seller extends User {
    private ArrayList<FoodTruck> ownedFoodTruck; //A List stored all FoodTrucks this Seller owned

    /**
     * Construct an instance of a Seller
     *
     * @param accName     The account name of this Seller
     * @param password    The password of this Seller account
     * @param nickname    The nickname of this Seller
     * @param phoneNumber A string representing the phone number of this Seller
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
     * @return Return True if successfully added and False otherwise.
     */
    public boolean addFoodTruck(FoodTruck foodtruck) {
        //comment
        try {
            ownedFoodTruck.add(foodtruck);
        } catch (Exception e) {
            System.out.println("Add fail!");
            return false;
        }
        System.out.println("Add success!");
        return true;
    }

    /**
     * Remove a foodtruck from ownedFoodTruck that this seller owned.
     *
     * @param foodtruck the foodtruck that want to be removed from the ownedFoodTruck.
     * @return Return True if successfully removed and False otherwise.
     */
    public boolean removeFoodTruck(FoodTruck foodtruck) {
        try {
            ownedFoodTruck.remove(foodtruck);
        } catch (Exception e) {
            System.out.println("Remove Fail!");
            return false;
        }
        System.out.println("Remove Success!");
        return true;
    }

    /**
     * To return a list of all owned FoodTrucks of this Seller.
     *
     * @return a list of all owned FoodTrucks of this Seller.
     */
    public ArrayList<FoodTruck> getFoodTruck() {
        return this.ownedFoodTruck;
    }


}

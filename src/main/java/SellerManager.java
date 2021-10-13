import java.util.ArrayList;
import java.util.HashMap;

/**
 * A SellerManager that manages all the Sellers.
 */

public class SellerManager extends UserManager{
    private HashMap<String, Seller> sellers;

    /**
     *
     * @param sellers a map that maps a seller's account name to the Seller object.
     */
    public SellerManager(HashMap<String, Seller> sellers) {
        super();
        this.sellers = sellers;
    }

    /**
     *
     * @param accName A string of the account name.
     * @param password A string of the password.
     * @return true if the customer login successfully.
     */
    @Override
    public boolean login(String accName, String password) {
        if (sellers.containsKey(accName)) {
            return sellers.get(accName).login(password);
        }
        else {
            return false;
        }
    }


    /**
     *
     * @return a list of all Sellers.
     */
    public ArrayList<Seller> getSellers() {
        ArrayList<Seller> sellerList = new ArrayList<Seller>();
        for (String accName : sellers.keySet()) {
            sellerList.add(sellers.get(accName));
        }
        return sellerList;
    }

    /**
     *
     * @param accName A string of the account name.
     * @param password A string of the password.
     * @param nickname A string of the nickname.
     * @param phoneNum A string of the phone number of the customer.
     * @return true if the seller being created successfully.
     */
    public boolean creatSeller(String accName, String password, String nickname, String phoneNum) {

        if (sellers.containsKey(accName)) {
            return false;
        }
        else {
            Seller newSeller = new Seller(accName, password, nickname, phoneNum);
            sellers.put(accName, newSeller);
            return true;
        }
    }

    public void addFoodTruck(String accName, FoodTruck foodTruck) {
        Seller seller = sellers.get(accName);
        seller.addFoodtruck(foodTruck);
    }
}

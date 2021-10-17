import Exceptions.IncorrectCredentialsException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A SellerManager that manages all the Sellers.
 */

public class SellerManager extends UserManager{

    /**
     * Creat a SellerManager.
     */
    public SellerManager() {
        super();
    }

    /**
     *
     * @param accName A string of the account name.
     * @param password A string of the password.
     *
     */
    @Override
    public void login(String accName, String password) throws IncorrectCredentialsException {
        if (sellerMap.containsKey(accName)) {
            if (sellerMap.get(accName).login(password)) {
                return;
            }
            throw new IncorrectCredentialsException();
        }
        else {
            throw new IncorrectCredentialsException();
        }
    }



    /**
     *
     * @return a list of all Sellers.
     */
    public ArrayList<Seller> getSellers() {
        ArrayList<Seller> sellerList = new ArrayList<Seller>();
        for (String accName : sellerMap.keySet()) {
            sellerList.add(sellerMap.get(accName));
        }
        return sellerList;
    }

//    /**
//     *
//     * @param accName A string of the account name.
//     * @param password A string of the password.
//     * @param nickname A string of the nickname.
//     * @param phoneNum A string of the phone number of the customer.
//     * @return true if the seller being created successfully.
//     */
//    public boolean creatSeller(String accName, String password, String nickname, String phoneNum) {
//
//        if (sellerMap.containsKey(accName)) {
//            return false;
//        }
//        else {
//            Seller newSeller = new Seller(accName, password, nickname, phoneNum);
//            sellerMap.put(accName, newSeller);
//            return true;
//        }
//    }


    // this does not handle multiple invalid FoodTruck.
    // this needs to address the case that the Seller already has the truck.
    // return ture.
    public boolean addFoodTruck(String accName, FoodTruck foodTruck) {
        if (sellerMap.containsKey(accName)) {
            Seller seller = sellerMap.get(accName);
            seller.addFoodTruck(foodTruck);
            return true;
        }
        return false;

    }
}

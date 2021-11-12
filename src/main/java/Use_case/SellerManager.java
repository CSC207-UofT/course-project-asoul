package Use_case;

import Entities.FoodTruck;
import Entities.Seller;
import Exceptions.IncorrectCredentialsException;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Use_case.SellerManager that manages all the Sellers.
 */

public class SellerManager extends UserManager implements CommandExecutable{

    /**
     * Creat a Use_case.SellerManager.
     */
    public SellerManager() {
        super();
    }

    /**
     * @param accName  A String that represents the account Name.
     * @param password A String of password that the user typed in.
     * @throws IncorrectCredentialsException Exception if the password doesn't match the account name or there is no
     *                                       such account name.
     */
    @Override
    public void login(String accName, String password) throws IncorrectCredentialsException {
        if (sellerMap.containsKey(accName)) {
            if (sellerMap.get(accName).login(password)) {
                return;
            }
        }
        throw new IncorrectCredentialsException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void constructUserDataBase() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/seller info"));
            SellerManager.sellerMap = (HashMap<String, Seller>) ois.readObject();
            UserManager.userMap.putAll(sellerMap);
            ois.close();
        }catch(EOFException e){
            // Do nothing, no seller has been registered
        }
    }

    @Override
    public void saveUserDataBase() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/seller info"));
        oos.writeObject(sellerMap);
        oos.flush();
        oos.close();
    }


    /**
     * @return an array of sellers objects that already been created.
     */
    public ArrayList<Seller> getSellers() {
        ArrayList<Seller> sellerList = new ArrayList<>();
        for (String accName : sellerMap.keySet()) {
            sellerList.add(sellerMap.get(accName));
        }
        return sellerList;
    }


    // this does not handle multiple invalid Entities.FoodTruck.
    // this needs to address the case that the Entities.Seller already has the truck.
    // return ture.

    /**
     * @param accName   The account name of the seller who wants to add truck.
     * @param foodTruck The Entities.FoodTruck that the user wants to add.
     * @return true if the Entities.FoodTruck has been added successfully.
     */
    public boolean addFoodTruck(String accName, FoodTruck foodTruck) {
        if (sellerMap.containsKey(accName)) {
            Seller seller = sellerMap.get(accName);
            seller.addFoodTruck(foodTruck);
            return true;
        }
        return false;
    }

    /**
     * @param accName The account name of the seller.
     * @return The Entities.Seller with the accName.
     */
    public Seller getSellerByAccName(String accName) {
        return sellerMap.get(accName);
    }

    @Override
    public HashMap<String, Method> getAvailableCommands() {
        return null;
    }
}

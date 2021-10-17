import Exceptions.IncorrectCredentialsException;

import java.util.ArrayList;

/**
 * A CustomerManager that manages all the Customers.
 */
public class CustomerManager extends UserManager {

    /**
     * Create a CustomerManager.
     */
    public CustomerManager() {
        super();
    }


    /**
     * @return an array of customer objects that already been created.
     */
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        for (String accName : customerMap.keySet()) {
            customerList.add(customerMap.get(accName));
        }
        return customerList;
    }


    /**
     * @param accName  A String that represents the account Name.
     * @param password A String of password that the user typed in.
     * @throws IncorrectCredentialsException Exception if the password doesn't match the account name or there is no
     *                                       such account name.
     */
    @Override
    public void login(String accName, String password) throws IncorrectCredentialsException {
        if (customerMap.containsKey(accName)) {
            if (customerMap.get(accName).login(password)) {
                return;
            }
        }
        throw new IncorrectCredentialsException();
    }
}

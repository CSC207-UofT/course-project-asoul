package Use_case;

import Entities.Customer;
import Exceptions.IncorrectCredentialsException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A Use_case.CustomerManager that manages all the Customers.
 */
public class CustomerManager extends UserManager implements CommandExecutable{

    /**
     * Create a Use_case.CustomerManager.
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

    @Override
    public HashMap<String, Method> getAvailableCommands() {
        return null;
    }
}

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A CustomerManager that manages all the Customers.
 */
public class CustomerManager extends UserManager{
    private HashMap<String, Customer> customers;

    /**
     *
     * @param customers a map that maps a customer's account name to the customer object.
     *
     * Create a CustomerManager with the given customers.
     */
    public CustomerManager(HashMap<String, Customer> customers){
        super();
        this.customers = customers;
    }


    /**
     *
     * @return a list of all Customers.
     *
     * This will return a list of customers.
     */
    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customerList = new ArrayList<Customer>();
        for (String accName : customers.keySet()) {
            customerList.add(customers.get(accName));
        }
        return customerList;
    }


    /**
     *
     * @param accName A string of the account name.
     * @param password A string of the password.
     * @param nickname A string of the nickname.
     * @param phoneNum A string of the phone number of the customer.
     * @return true if the customer being created successfully.
     */
    public boolean creatCustomer(String accName, String password, String nickname, String phoneNum) {

        if (customers.containsKey(accName)) {
            return false;
        }
        else {
            Customer newCustomer = new Customer(accName, password, nickname, phoneNum);
            customers.put(accName, newCustomer);
            return true;
        }
    }

    /**
     *
     * @param accName A string of the account name.
     * @param password A string of the password.
     * @return true if the customer login successfully.
     */
    @Override
    public boolean login(String accName, String password) {
        if (customers.containsKey(accName)) {
            return customers.get(accName).login(password);
        }
        else {
            return false;
        }
    }
}

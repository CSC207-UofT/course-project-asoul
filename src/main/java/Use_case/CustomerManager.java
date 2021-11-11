package Use_case;

import Entities.Customer;
import Entities.Seller;
import Exceptions.IncorrectCredentialsException;

import java.io.*;
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

    @Override
    public HashMap<String, Method> getAvailableCommands() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void constructUserDataBase() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/customer info"));
            CustomerManager.customerMap = (HashMap<String, Customer>) ois.readObject();
            UserManager.userMap.putAll(customerMap);
            ois.close();
        }catch (EOFException e){
            // Do nothing, no customer has been created
        }
    }

    @Override
    public void saveUserDataBase() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./data/customer info"));
        oos.writeObject(customerMap);
        oos.flush();
        oos.close();
    }
}

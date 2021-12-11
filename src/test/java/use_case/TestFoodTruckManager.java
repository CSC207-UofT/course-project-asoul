package use_case;

import exceptions.*;

public class TestFoodTruckManager {
    @org.junit.Test
    public void exitsTruckTest() {
        assert !FoodTruckManager.existsTruck("dajjlajdf");
    }

    @org.junit.Test
    public void addFoodToMenuTest() throws CollidedFoodException, FoodIdCollisionException {
        UserManager.createUser("a", "a", "a", "a");
        FoodTruckManager.addFoodToMenu("1", "1", "1", "1", "a");
        assert true; //if it runs this line, means no exception so test is true.
    }

    @org.junit.Test
    public void removeFoodToMenuTest() {
        UserManager.createUser("a", "a", "a", "a");
        FoodTruckManager.removeFoodFromMenu("1", "a");
        assert true; //if it runs this line, means no exception so test is true.
    }

    @org.junit.Test
    public void setTruckNameTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("a", "a", "a", "a");
        String a = UserManager.login("a", "a");
        FoodTruckManager.setTruckName("banana", "a", a);
        assert FoodTruckManager.getTruckName("a").equals("banana");
    }

    @org.junit.Test
    public void getMenuTest() throws IncorrectCredentialsException, UnknownFoodTruckException {
        UserManager.createUser("a", "a", "a", "a");
        UserManager.login("a", "a");
        assert FoodTruckManager.getMenu("a") != null;
    }

    @org.junit.Test
    public void getRatingTest() throws IncorrectCredentialsException{
        UserManager.createUser("a", "a", "a", "a");
        UserManager.login("a", "a");
        assert FoodTruckManager.getRating("a")  != -100;
    }

    @org.junit.Test
    public void isActiveTest() throws IncorrectCredentialsException, UnauthorizedAccessException {
        UserManager.createUser("a", "a", "a", "a");
        String a = UserManager.login("a", "a");
        assert !FoodTruckManager.isActive("a", a);
    }

    @org.junit.Test
    public void getActiveFoodTruckDescriptionTest() throws IncorrectCredentialsException {
        UserManager.createUser("a", "a", "a", "a");
        UserManager.login("a", "a");
        assert FoodTruckManager.getActiveFoodTruckDescription().isEmpty();
    }

    @org.junit.Test
    public void getFoodTruckDetailTest() throws UnknownFoodTruckException {
        UserManager.createUser("a", "a", "a", "a");
        assert FoodTruckManager.getFoodTruckDetail("a") != null;
    }
    @org.junit.Test
    public void getFoodNameTest() {
        try{
        UserManager.createUser("a", "a", "a", "a");
        UserManager.login("a", "a");
        FoodTruckManager.getFoodName("a", "5");}
        catch (Exception e){
            assert true;
        }
    }
}

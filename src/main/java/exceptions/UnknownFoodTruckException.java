package exceptions;

public class UnknownFoodTruckException extends Exception {
    public UnknownFoodTruckException() {
        super("Unknown FoodTruck id entered!");
    }
}

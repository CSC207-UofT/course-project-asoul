package exceptions;

public class UnknownFoodTruckException extends Exception {
    public UnknownFoodTruckException() {
        super("Unknown Entities.FoodTruck id entered!");
    }
}

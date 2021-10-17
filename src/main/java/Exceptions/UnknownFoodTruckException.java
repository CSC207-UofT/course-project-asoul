package Exceptions;

public class UnknownFoodTruckException extends Exception {
    public UnknownFoodTruckException() {
        super("Unknown Entities.FoodTruck id entered!");
    }
}

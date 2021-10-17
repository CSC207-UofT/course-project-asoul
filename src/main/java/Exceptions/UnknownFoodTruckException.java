package Exceptions;

public class UnknownFoodTruckException extends Exception{
    public UnknownFoodTruckException() {
        super("Unknown FoodTruck id entered!");
    }
}

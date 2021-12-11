package exceptions;

public class CollidedFoodException extends Exception {
    public CollidedFoodException() {
        super("Food Collided!");
    }
}

package exceptions;

public class FoodIdCollisionException extends Exception {
    public FoodIdCollisionException() {
        super("Collided food ids!");
    }
}

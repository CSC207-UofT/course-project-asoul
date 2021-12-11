package exceptions;

public class UnknownOrderException extends Exception {
    public UnknownOrderException() {
        super("This order doesn't exist!");
    }
}

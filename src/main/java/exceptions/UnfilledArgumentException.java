package exceptions;

public class UnfilledArgumentException extends Exception {
    public UnfilledArgumentException() {
        super("All arguments need to be filled.");
    }
}

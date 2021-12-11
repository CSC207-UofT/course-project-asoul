package exceptions;

public class UnknownUserException extends Exception {
    public UnknownUserException() {
        super("This user doesn't exit.");
    }
}

package Exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException() {
        super("Unknown Command Detected!");
    }
}

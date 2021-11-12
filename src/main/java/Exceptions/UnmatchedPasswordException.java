package Exceptions;

public class UnmatchedPasswordException extends Exception {
    public UnmatchedPasswordException() {
        super("Password does not match!");
    }
}

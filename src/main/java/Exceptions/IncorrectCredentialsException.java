package Exceptions;

public class IncorrectCredentialsException extends Exception {
    public IncorrectCredentialsException() {
        super("Incorrect user credentials entered!");
    }
}

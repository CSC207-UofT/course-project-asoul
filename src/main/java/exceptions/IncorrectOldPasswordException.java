package exceptions;

public class IncorrectOldPasswordException extends Exception {
    public IncorrectOldPasswordException() {
        super("Incorrect old password entered!");
    }
}

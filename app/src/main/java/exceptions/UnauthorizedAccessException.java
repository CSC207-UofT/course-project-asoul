package exceptions;

public class UnauthorizedAccessException extends Exception{
    public UnauthorizedAccessException(){
        super("Unauthorized access to the content!");
    }
}

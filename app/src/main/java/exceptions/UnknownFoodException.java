package exceptions;

public class UnknownFoodException extends Exception{
    public UnknownFoodException(){
        super("The specified food does not exist!");
    }
}

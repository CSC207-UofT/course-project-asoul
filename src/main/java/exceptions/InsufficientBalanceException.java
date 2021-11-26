package exceptions;

public class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(){
        super("Account balance is insufficient!");
    }
}

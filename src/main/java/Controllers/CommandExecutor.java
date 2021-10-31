package Controllers;

import Exceptions.IncorrectArgumentException;
import Exceptions.UnknownCommandException;
import Use_case.CustomerManager;
import Use_case.FoodTruckManager;
import Use_case.OrderManager;
import Use_case.SellerManager;

import java.util.Arrays;
import java.util.Locale;

public class CommandExecutor {
    Command customerCommand;
    Command sellerCommand;
    Command foodTruckCommand;
    Command orderCommand;

    private static class CommandComm{
        String methodName;
        Command receiver;

        CommandComm(String mn, Command r){
            methodName = mn;
            receiver = r;
        }
    }

    public CommandExecutor(){
        this.customerCommand = new Command(new CustomerManager());
        this.sellerCommand = new Command(new SellerManager());
        this.foodTruckCommand = new Command(new FoodTruckManager());
        this.orderCommand = new Command(new OrderManager());
    }

    private CommandComm commandSeparate(String command) throws UnknownCommandException {
        String[] cs = command.split(" ");
        String arg = String.join("", Arrays.copyOfRange(cs, 1, cs.length));
        String name = cs[0].toLowerCase(Locale.ROOT);
        Command cm = this.getCommand(name);
        return new CommandComm(arg, cm);
    }

    private Command getCommand(String name) throws UnknownCommandException{
        return switch (name) {
            case "customer" -> this.customerCommand;
            case "seller" -> this.sellerCommand;
            case "order" -> this.orderCommand;
            case "foodtruck" -> this.orderCommand;
            default -> throw new UnknownCommandException();
        };
    }

    public Object executeCommand(String command) throws UnknownCommandException, IncorrectArgumentException {
        CommandComm coms = this.commandSeparate(command);
        Command receiver = coms.receiver;
        String arg = coms.methodName;
        return receiver.executeCommand(arg);
    }

    public Object executeCommand(String receiver, String methodName, Object[] args) throws UnknownCommandException,
            IncorrectArgumentException {
        Command cm = this.getCommand(receiver);
        return cm.executeCommand(methodName, args);
    }
}

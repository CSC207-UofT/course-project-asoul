package Controllers;

import Exceptions.IncorrectArgumentException;
import Exceptions.UnknownCommandException;
import Use_case.CommandExecutable;
import Use_case.CustomerManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Command {
    CommandExecutable ce;
    HashMap<String, Method> commandMap;
    HashMap<String, Method> argsParserMap;

    public Command(CommandExecutable ce){
        this.ce = ce;
        this.commandMap = ce.getAvailableCommands(); //TODO: Use case manager classes need to have methods that return all of
                                            // their available public methods with corresponded keys to trigger them
        this.argsParserMap = new HashMap<>(); // TODO: Map methods with correct parsers
    }

    public Object[] parseParameters(String rawCommand){
        return new Object[1];
    }

    public Object executeCommand(String command) throws UnknownCommandException, IncorrectArgumentException {
        Object[] args = this.parseParameters(command);
        try{
            return this.commandMap.get(command).invoke(ce, args);
        }catch (NullPointerException e){
            throw new UnknownCommandException();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InvocationTargetException e){
            throw new IncorrectArgumentException();
        }
        return null;
    }
}

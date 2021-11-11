package Controllers;

import Exceptions.IncorrectCredentialsException;
import Exceptions.UnknownCommandException;

public class SystemInOut {
    private InputHandler handler;
    private OutputConstructor constructor;


    public SystemInOut(InputHandler handler, OutputConstructor contructor) {
        this.handler = handler;
        this.constructor = contructor; //todo injection.
    }

    public String processInput(String input) throws IncorrectCredentialsException, UnknownCommandException {
        //Process input
//        try {
//            handler.handlingGeneralInput(input);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String inputFeedback = handler.handlingGeneralInput(input); // we will get rid of exceptions
        //Construct output
        return constructor.outputGeneralGenerator(inputFeedback);



        // try catch

    }
}

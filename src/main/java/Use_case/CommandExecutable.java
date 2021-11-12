package Use_case;

import java.lang.reflect.Method;
import java.util.HashMap;

public interface CommandExecutable {
    HashMap<String, Method> getAvailableCommands();
}

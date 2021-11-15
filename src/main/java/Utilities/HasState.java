package Utilities;

import java.util.HashMap;

public interface HasState {
    public String getCurrentStateName();
    public HashMap<String, String> getTransitionMap();
    public void changeState(String input);
    public String getInitialStateName();
}

package Utilities;

import java.util.HashMap;

public class State {
    public String stateName;
    public HashMap<String, State> inputMap;

    public State(String name){
        stateName = name;
        inputMap = new HashMap<>();
    }

    public State(String name, HashMap<String, State> mp){
        stateName = name;
        inputMap = mp;
    }
}

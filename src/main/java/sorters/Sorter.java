package sorters;

import singleton_pattern.Singleton;

import java.util.*;

public abstract class Sorter implements Singleton {

    public ArrayList<String> sort(HashMap<String, String> sortingMap) { // Key = seller name of the truck

        // reverse the Map
        HashMap<String, HashSet<String>> reveredMap = processMap(sortingMap);
        // convert map to array
        ArrayList<String> keyArrayList = new ArrayList<>(reveredMap.keySet());
        // sort key array
        ArrayList<String> sortedArrayList = sortKeyArrayList(keyArrayList);
        return constructOutput(sortedArrayList, reveredMap);
    }


    protected HashMap<String, HashSet<String>> processMap(HashMap<String, String> userRatingMap) {
        HashMap<String, HashSet<String>> reversedMap = new HashMap<>();
        for (String key : userRatingMap.keySet()) {
            String oldValue = userRatingMap.get(key);
            if (!reversedMap.containsKey(oldValue)) {
                HashSet<String> newValueSet = new HashSet<>();
                newValueSet.add(key);
                reversedMap.put(oldValue, newValueSet);
            } else {
                HashSet<String> valueSet = reversedMap.get(oldValue);
                valueSet.add(key);
            }
        }
        return reversedMap;
    }

    abstract ArrayList<String> sortKeyArrayList(ArrayList<String> list);

    private ArrayList<String> constructOutput(ArrayList<String> list, HashMap<String, HashSet<String>> map) {
        ArrayList<String> result = new ArrayList<>();
        for (String e : list) {
            result.addAll(map.get(e));
        }
        return result;
    }
}

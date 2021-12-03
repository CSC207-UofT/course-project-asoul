package sorters;

import singleton_pattern.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Sorter implements Singleton{

    public ArrayList<String> sort(HashMap<String, String> sortingMap){ // Key = seller name of the truck
        HashMap<String, HashSet<String>> reveredMap = processMap(sortingMap); // process map

        ArrayList<String> keyArrayList = new ArrayList<>(reveredMap.keySet());
        String[] keyArray = (String[]) keyArrayList.toArray();
        ArrayList<String> result = new ArrayList<>(reveredMap.keySet());
        int i = 0;
        int max = reveredMap.keySet().size();
        int a = 0;
        while (i < max - 2) {
            a = compare(keyArray[i], keyArray[i+1]);
            updateArrayList(a, keyArray[i], keyArray[i+1], result, reveredMap);
            i = i + 1;
        }
        return result;
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


    protected void updateArrayList(int a, String former, String latter, ArrayList<String> array,
                                   HashMap<String, HashSet<String>> reveredMap){


        if (a >= 0) {
            array.addAll(reveredMap.get(former));
            array.addAll(reveredMap.get(latter));
        } else {
            array.addAll(reveredMap.get(latter));
            array.addAll(reveredMap.get(former));
        }
    }

    abstract int compare(String a, String b);

}

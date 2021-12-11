package sorters;

import java.util.ArrayList;
import java.util.Collections;

public class RatingSorter extends Sorter {
    private final static RatingSorter rs = new RatingSorter();

    private RatingSorter() {
    }

    @Override
    protected ArrayList<String> sortKeyArrayList(ArrayList<String> list) {
        ArrayList<Double> doubleList = new ArrayList<>();
        for (String e : list) {
            doubleList.add(Double.parseDouble(e));
        }
        ArrayList<String> result = new ArrayList<>();
        Collections.sort(doubleList);
        for (double e : doubleList) {
            result.add(Double.toString(e));
        }
        Collections.reverse(result);
        return result;
    }

    static RatingSorter getInstance() {
        return rs;
    }

}

package sorters;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class NameSorter extends Sorter{
    private final static NameSorter ns = new NameSorter();



    private NameSorter(){
    }
    static NameSorter getInstance() {
        return ns;
    }

    @Override
    protected ArrayList<String> sortKeyArrayList(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>(list);
        Collections.sort(result);
        return result;
    }
}
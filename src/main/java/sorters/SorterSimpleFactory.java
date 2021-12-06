package sorters;

import exceptions.UnknownSorterException;

import java.util.Arrays;

public class SorterSimpleFactory {

    public static Sorter constructSorter(String s) throws UnknownSorterException {
        switch (s) {
            case "rating":
                return RatingSorter.getInstance();
            case "name":
                return NameSorter.getInstance();
            default:
                throw new UnknownSorterException();
        }
    }

    public static boolean containsSorter(String s){
        String[] sorters = {"rating", "name"};
        return Arrays.asList(sorters).contains(s);
    }
}

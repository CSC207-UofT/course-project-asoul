package sorters;

import exceptions.UnknownSorterException;

public class SorterSimpleFactory {

    static Sorter constructSoter(String s) throws UnknownSorterException {
        switch (s) {
            case "rating":
                return RatingSorter.getInstance();
            case "name length":
                return NameLengthSorterNonIncreasing.getInstance();
            default:
                throw new UnknownSorterException();
        }
    }
}

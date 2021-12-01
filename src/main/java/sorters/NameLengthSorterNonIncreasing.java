package sorters;

import singleton_pattern.Singleton;

public class NameLengthSorterNonIncreasing extends Sorter{
    private final static NameLengthSorterNonIncreasing ns = new NameLengthSorterNonIncreasing();

    @Override
    protected int compare(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }

    private NameLengthSorterNonIncreasing(){
    }
    static NameLengthSorterNonIncreasing getInstance() {
        return ns;
    }
}

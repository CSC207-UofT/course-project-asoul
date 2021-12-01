package sorters;

import singleton_pattern.Singleton;

public class RatingSorter extends Sorter{
    private final static RatingSorter rs = new RatingSorter();
    private RatingSorter(){
    }

    @Override
    int compare(String a, String b) {
        double aRating = Double.parseDouble(a);
        double bRating = Double.parseDouble(b);
        return Double.compare(aRating, bRating);
    }

    static RatingSorter getInstance() {
        return rs;
    }
}

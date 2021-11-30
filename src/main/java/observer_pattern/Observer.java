package observer_pattern;

public interface Observer {
    default void subscribe(Observable o){
        o.addObserver(this);
    }

    void update();
}

package controllers;

import observer_pattern.Observable;
import observer_pattern.Observer;
import use_case.FoodTruckManager;
import use_case.OrderManager;
import use_case.UserManager;

import java.io.IOException;
import java.util.HashSet;

/**
 * Booter of the program.
 */
public abstract class SceneBooter implements Observable {
    private final HashSet<Observer> obs;

    public SceneBooter() {
        obs = new HashSet<>();
    }

    @Override
    public void notifyObservers() {
        for (Observer o : obs) {
            o.update();
        }
    }

    @Override
    public void addObserver(Observer o) {
        obs.add(o);
    }

    public void boot() throws IOException, ClassNotFoundException {
        FoodTruckManager.constructFoodTruckDataBase();
        UserManager.constructUserDataBase();
        OrderManager.constructOrderDataBase();
    }

    protected Scene getActiveScene() {
        return Scene.activeScene;
    }

    protected void setActiveScene(Scene scene) {
        Scene.activeScene = scene;
    }

    public abstract String outputInString();

    public void handleInputInString(String input) {
        Scene.activeScene.state.setLength(0);
    }

    public void terminate() throws IOException {
        FoodTruckManager.saveFoodTruckDataBase();
        UserManager.saveUserDataBase();
        OrderManager.saveOrderDataBase();
    }

    public boolean isRunning() {
        return !Scene.exit;
    }
}

package observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int id;

    public Subject(int id) {
        this.id = id;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update(id);
            if (observer.getResult().contains("{")) {
                observer.getSubject().notifyAllObservers();
            }
        }
    }
}

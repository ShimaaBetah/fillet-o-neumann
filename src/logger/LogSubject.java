package logger;

import logger.destinations.LogObserver;

import java.util.ArrayList;

public class LogSubject {
    ArrayList<LogObserver> observers = new ArrayList<>();

    public void attachObserver(LogObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (LogObserver observer : observers) {
            observer.log(message);
        }
    }
}

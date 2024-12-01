package com.acmeplex.api.notifier;

import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }

    public void sendNotification(String message) {
        notifyObservers(message);
    }
}

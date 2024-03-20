package com.example.couriermanager.patterns.observer;

import com.example.couriermanager.model.Courier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourierMovementObservable {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(Courier courier, String storeName) {
        for (Observer observer : observers) {
            observer.logEntry(courier.getId(), storeName);
        }
    }
}

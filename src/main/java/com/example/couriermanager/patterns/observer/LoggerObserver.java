package com.example.couriermanager.patterns.observer;

// Implementation of the Observer interface for logging
public class LoggerObserver implements Observer {
    @Override
    public void logEntry(String courierId, String storeName) {
        System.out.println("Courier " + courierId + " entered " + storeName);
    }
}
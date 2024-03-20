package com.example.couriermanager.service;

import com.example.couriermanager.model.Courier;
import com.example.couriermanager.model.Location;
import com.example.couriermanager.model.Store;
import com.example.couriermanager.patterns.observer.CourierMovementObservable;
import com.example.couriermanager.patterns.observer.LoggerObserver;
import com.example.couriermanager.util.Utils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourierService {

    private final Map<String, List<Location>> courierMovements = new HashMap<>();
    private final StoreService storeService;
    private final Map<String, Long> lastStoreEntranceTimestamps = new HashMap<>();
    private final long REENTRY_TIME_THRESHOLD = 60 * 1000;
    private static final double STORE_PROXIMITY_THRESHOLD = 100;
    private final CourierMovementObservable courierManager;

    public CourierService(StoreService storeService, CourierMovementObservable courierManager) {
        this.storeService = storeService;
        this.courierManager = courierManager;
        this.courierManager.addObserver(new LoggerObserver());
    }


    public void receiveCourierLocation(Courier courier) {
        for (Store store : storeService.getStores()) {
            double distance = Utils.calculateDistance(courier.getLat(), courier.getLng(), store.getLat(), store.getLng());
            if (distance <= STORE_PROXIMITY_THRESHOLD && distance!=-1) {
                System.out.println("4");
                if (isWithinReentryThreshold(courier.getId(), store.getName())) {
                    continue;
                }

                courierManager.notifyObservers(courier, store.getName());
                updateLastStoreEntranceTimestamp(courier.getId(), store.getName());
            }
        }

        if (!courierMovements.containsKey(courier.getId())) {
            courierMovements.put(courier.getId(), new ArrayList<>());
        }
        courierMovements.get(courier.getId()).add(new Location(courier.getLat(), courier.getLng()));
    }
    private boolean isWithinReentryThreshold(String courierId, String storeName) {
        if (!lastStoreEntranceTimestamps.containsKey(courierId + "_" + storeName)) {
            return false;
        }
        long lastEntranceTimestamp = lastStoreEntranceTimestamps.get(courierId + "_" + storeName);
        long currentTimestamp = Calendar.getInstance().getTimeInMillis();
        return (currentTimestamp - lastEntranceTimestamp) > REENTRY_TIME_THRESHOLD;
    }

    private void updateLastStoreEntranceTimestamp(String courierId, String storeName) {
        lastStoreEntranceTimestamps.put(courierId + "_" + storeName, Calendar.getInstance().getTimeInMillis());
    }

    public double getTotalTravelDistance(String courierId) {
        try {
            validateCourierExists(courierId);
            List<Location> locations = courierMovements.get(courierId);
            validateSufficientLocations(locations);
            return calculateTotalDistance(locations);
        } catch (Exception e) {
            handleException(e);
            return -1;
        }
    }
    private void validateCourierExists(String courierId) {
        if (!courierMovements.containsKey(courierId)) {
            throw new NoSuchElementException("Courier ID '" + courierId + "' not found");
        }
    }
    private void validateSufficientLocations(List<Location> locations) {
        if (locations.size() < 2) {
            throw new IllegalStateException("Insufficient locations to calculate total distance");
        }
    }
    private double calculateTotalDistance(List<Location> locations) {
        double totalDistance = 0.0;
        for (int i = 1; i < locations.size(); i++) {
            Location loc1 = locations.get(i - 1);
            Location loc2 = locations.get(i);
            double distance = Utils.calculateDistance(loc1.getLatitude(), loc1.getLongitude(), loc2.getLatitude(), loc2.getLongitude());
            if (distance != -1) {
                totalDistance += distance;
            }
        }
        return totalDistance;
    }
    private void handleException(Exception e) {
        System.err.println("Error calculating distance between locations: " + e.getMessage());
    }
}
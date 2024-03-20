package com.example.couriermanager.util;

public class Utils {
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        try {
            if (!isValidLatitude(latitude1) || !isValidLatitude(latitude2) || !isValidLongitude(longitude1) || !isValidLongitude(longitude2)) {
                throw new IllegalArgumentException("Invalid latitude or longitude values provided.");
            }

            double latitude1Radians = Math.toRadians(latitude1);
            double longitude1Radians = Math.toRadians(longitude1);
            double latitude2Radians = Math.toRadians(latitude2);
            double longitude2Radians = Math.toRadians(longitude2);

            double deltaLatitude = latitude2Radians - latitude1Radians;
            double deltaLongitude = longitude2Radians - longitude1Radians;

            double haversineA = Math.pow(Math.sin(deltaLatitude / 2), 2) +
                    Math.cos(latitude1Radians) * Math.cos(latitude2Radians) *
                            Math.pow(Math.sin(deltaLongitude / 2), 2);
            double haversineC = 2 * Math.atan2(Math.sqrt(haversineA), Math.sqrt(1 - haversineA));

            return EARTH_RADIUS_KM * haversineC;
        } catch (Exception e) {
            System.err.println("Error calculating distance: " + e.getMessage());
            return -1;
        }
    }

    private static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    private static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
}

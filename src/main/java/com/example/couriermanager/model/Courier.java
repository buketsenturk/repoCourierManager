package com.example.couriermanager.model;
import java.time.LocalDateTime;

public class Courier {
    private String id;
    private double lat;
    private double lng;
    private LocalDateTime timestamp;

    public Courier() {
    }

    public Courier(String id, double lat, double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}

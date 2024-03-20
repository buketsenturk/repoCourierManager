package com.example.couriermanager.api.controller;

import com.example.couriermanager.model.Courier;
import com.example.couriermanager.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couriers")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @PostMapping("/location")
    public ResponseEntity<String> receiveLocation(@RequestBody Courier courier) {
        courierService.receiveCourierLocation(courier);
        return ResponseEntity.ok("Location received successfully.");
    }

    @GetMapping("/{courierId}/distance")
    public ResponseEntity<Double> getTotalTravelDistance(@PathVariable String courierId) {
        Double totalDistance = courierService.getTotalTravelDistance(courierId);
        return ResponseEntity.ok(totalDistance);
    }

}
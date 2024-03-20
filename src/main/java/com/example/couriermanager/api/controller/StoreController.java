package com.example.couriermanager.api.controller;

import com.example.couriermanager.model.Store;
import com.example.couriermanager.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;


    @GetMapping()
    public ResponseEntity<List<Store>> getAllStores() {
        return ResponseEntity.ok(storeService.getStores());
    }

}
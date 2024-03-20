package com.example.couriermanager.service;

import com.example.couriermanager.model.Courier;
import com.example.couriermanager.model.Store;
import com.example.couriermanager.patterns.singleton.StoreManager;
import com.example.couriermanager.util.StoreLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreService {

    private final StoreManager storeManager;

    public StoreService(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public List<Store> getStores() {
        return storeManager.getMigrosStores();
    }
}
package com.example.couriermanager.patterns.singleton;

import com.example.couriermanager.model.Store;
import com.example.couriermanager.util.StoreLoader;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreManager {
    private static StoreManager instance;
    private final List<Store> migrosStores;

    private StoreManager(StoreLoader storeLoader) {
        this.migrosStores = storeLoader.loadStores();
    }

    public static synchronized StoreManager getInstance(StoreLoader storeLoader) {
        if (instance == null) {
            instance = new StoreManager(storeLoader);
        }
        return instance;
    }

    public List<Store> getMigrosStores() {
        return migrosStores;
    }
}

package com.example.couriermanager.util;

import com.example.couriermanager.model.Store;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class StoreLoader {
    private static final String STORES_JSON_FILE = "/static/stores.json";

    public List<Store> loadStores() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getResourceAsStream(STORES_JSON_FILE);
            if (inputStream == null) {
                throw new FileNotFoundException("File " + STORES_JSON_FILE + " not found");
            }

            return objectMapper.readValue(inputStream, new TypeReference<List<Store>>() {});
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }
}
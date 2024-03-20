package com.example.couriermanager.api.controller;

import com.example.couriermanager.model.Store;
import com.example.couriermanager.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Test
    public void testGetAllStores() throws Exception {
        // Mock data
        List<Store> stores = Arrays.asList(
                new Store("Store 1", 10.0, 20.0),
                new Store("Store 2", 30.0, 40.0)
        );

        // Mock behavior of the storeService
        when(storeService.getStores()).thenReturn(stores);

        // Perform GET request and verify response
        mockMvc.perform(get("/stores")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Store 1")) // Verify first store
                .andExpect(jsonPath("$[0].lat").value(10.0))
                .andExpect(jsonPath("$[0].lng").value(20.0))
                .andExpect(jsonPath("$[1].name").value("Store 2")) // Verify second store
                .andExpect(jsonPath("$[1].lat").value(30.0))
                .andExpect(jsonPath("$[1].lng").value(40.0));
    }
}

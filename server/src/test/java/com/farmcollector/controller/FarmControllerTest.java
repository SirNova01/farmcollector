package com.farmcollector.controller;

import com.farmcollector.model.Farm;
import com.farmcollector.service.FarmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(FarmController.class)
public class FarmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FarmService farmService;

    @Autowired
    private ObjectMapper objectMapper;

    private Farm farm;

    @BeforeEach
    public void setUp() {
        farm = new Farm(1L, "Test Farm", "Test Location", null);
    }

    @Test
    public void testGetAllFarms() throws Exception {
        when(farmService.getAllFarms()).thenReturn(Arrays.asList(farm));

        mockMvc.perform(get("/api/farms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].farmName").value("Test Farm"));
    }

    @Test
    public void testGetFarmById() throws Exception {
        when(farmService.getFarmById(1L)).thenReturn(Optional.of(farm));

        mockMvc.perform(get("/api/farms/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.farmName").value("Test Farm"));
    }

    @Test
    public void testCreateFarm() throws Exception {
        when(farmService.saveFarm(any(Farm.class))).thenReturn(farm);

        mockMvc.perform(post("/api/farms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(farm)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.farmName").value("Test Farm"));
    }

    @Test
    public void testDeleteFarm() throws Exception {
        doNothing().when(farmService).deleteFarm(1L);

        mockMvc.perform(delete("/api/farms/1"))
                .andExpect(status().isNoContent());
    }
}

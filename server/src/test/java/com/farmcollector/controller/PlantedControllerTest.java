package com.farmcollector.controller;

import com.farmcollector.model.Planted;
import com.farmcollector.service.PlantedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlantedController.class)
public class PlantedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlantedService plantedService;

    @Autowired
    private ObjectMapper objectMapper;

    private Planted planted;

    @BeforeEach
    public void setUp() {
        planted = new Planted(1L, null, null, null, 100.0, 50.0);
    }

    @Test
    public void testGetAllPlanted() throws Exception {
        when(plantedService.getAllPlanted()).thenReturn(Arrays.asList(planted));

        mockMvc.perform(get("/api/planted"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plantingArea").value(100.0));
    }

    @Test
    public void testGetPlantedById() throws Exception {
        when(plantedService.getPlantedById(1L)).thenReturn(Optional.of(planted));

        mockMvc.perform(get("/api/planted/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plantingArea").value(100.0));
    }

    @Test
    public void testCreatePlanted() throws Exception {
        when(plantedService.savePlanted(any(Planted.class))).thenReturn(planted);

        mockMvc.perform(post("/api/planted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(planted)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plantingArea").value(100.0));
    }

    @Test
    public void testDeletePlanted() throws Exception {
        doNothing().when(plantedService).deletePlanted(1L);

        mockMvc.perform(delete("/api/planted/1"))
                .andExpect(status().isNoContent());
    }
}

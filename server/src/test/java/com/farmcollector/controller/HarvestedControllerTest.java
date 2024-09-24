package com.farmcollector.controller;

import com.farmcollector.model.Harvested;
import com.farmcollector.service.HarvestedService;
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

@WebMvcTest(HarvestedController.class)
public class HarvestedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HarvestedService harvestedService;

    @Autowired
    private ObjectMapper objectMapper;

    private Harvested harvested;

    @BeforeEach
    public void setUp() {
        harvested = new Harvested(1L, null, null, null, 100.0);
    }

    @Test
    public void testGetAllHarvested() throws Exception {
        when(harvestedService.getAllHarvested()).thenReturn(Arrays.asList(harvested));

        mockMvc.perform(get("/api/harvested"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actualYield").value(100.0));
    }

    @Test
    public void testGetHarvestedById() throws Exception {
        when(harvestedService.getHarvestedById(1L)).thenReturn(Optional.of(harvested));

        mockMvc.perform(get("/api/harvested/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actualYield").value(100.0));
    }

    @Test
    public void testCreateHarvested() throws Exception {
        when(harvestedService.saveHarvested(any(Harvested.class))).thenReturn(harvested);

        mockMvc.perform(post("/api/harvested")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(harvested)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actualYield").value(100.0));
    }

    @Test
    public void testDeleteHarvested() throws Exception {
        doNothing().when(harvestedService).deleteHarvested(1L);

        mockMvc.perform(delete("/api/harvested/1"))
                .andExpect(status().isNoContent());
    }
}

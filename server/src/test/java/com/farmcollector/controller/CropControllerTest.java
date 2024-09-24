package com.farmcollector.controller;

import com.farmcollector.model.Crop;
import com.farmcollector.service.CropService;
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

@WebMvcTest(CropController.class)
public class CropControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CropService cropService;

    @Autowired
    private ObjectMapper objectMapper;

    private Crop crop;

    @BeforeEach
    public void setUp() {
        crop = new Crop(1L, "Corn");
    }

    @Test
    public void testGetAllCrops() throws Exception {
        when(cropService.getAllCrops()).thenReturn(Arrays.asList(crop));

        mockMvc.perform(get("/api/crops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cropName").value("Corn"));
    }

    @Test
    public void testGetCropById() throws Exception {
        when(cropService.getCropById(1L)).thenReturn(Optional.of(crop));

        mockMvc.perform(get("/api/crops/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cropName").value("Corn"));
    }

    @Test
    public void testCreateCrop() throws Exception {
        when(cropService.saveCrop(any(Crop.class))).thenReturn(crop);

        mockMvc.perform(post("/api/crops")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cropName").value("Corn"));
    }

    @Test
    public void testDeleteCrop() throws Exception {
        doNothing().when(cropService).deleteCrop(1L);

        mockMvc.perform(delete("/api/crops/1"))
                .andExpect(status().isNoContent());
    }
}

package com.farmcollector.controller;

import com.farmcollector.model.Season;
import com.farmcollector.service.SeasonService;
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

@WebMvcTest(SeasonController.class)
public class SeasonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SeasonService seasonService;

    @Autowired
    private ObjectMapper objectMapper;

    private Season season;

    @BeforeEach
    public void setUp() {
        season = new Season(1L, "Spring 2024", null, null);
    }

    @Test
    public void testGetAllSeasons() throws Exception {
        when(seasonService.getAllSeasons()).thenReturn(Arrays.asList(season));

        mockMvc.perform(get("/api/seasons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].seasonName").value("Spring 2024"));
    }

    @Test
    public void testGetSeasonById() throws Exception {
        when(seasonService.getSeasonById(1L)).thenReturn(Optional.of(season));

        mockMvc.perform(get("/api/seasons/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seasonName").value("Spring 2024"));
    }

    @Test
    public void testCreateSeason() throws Exception {
        when(seasonService.saveSeason(any(Season.class))).thenReturn(season);

        mockMvc.perform(post("/api/seasons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(season)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seasonName").value("Spring 2024"));
    }

    @Test
    public void testDeleteSeason() throws Exception {
        doNothing().when(seasonService).deleteSeason(1L);

        mockMvc.perform(delete("/api/seasons/1"))
                .andExpect(status().isNoContent());
    }
}

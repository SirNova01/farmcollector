package com.farmcollector.controller;

import com.farmcollector.model.Field;
import com.farmcollector.service.FieldService;
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

@WebMvcTest(FieldController.class)
public class FieldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FieldService fieldService;

    @Autowired
    private ObjectMapper objectMapper;

    private Field field;

    @BeforeEach
    public void setUp() {
        field = new Field(1L, null, "Field 1", 100.0, null, null);
    }

    @Test
    public void testGetAllFields() throws Exception {
        when(fieldService.getAllFields()).thenReturn(Arrays.asList(field));

        mockMvc.perform(get("/api/fields"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fieldName").value("Field 1"));
    }

    @Test
    public void testGetFieldById() throws Exception {
        when(fieldService.getFieldById(1L)).thenReturn(Optional.of(field));

        mockMvc.perform(get("/api/fields/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fieldName").value("Field 1"));
    }

    @Test
    public void testCreateField() throws Exception {
        when(fieldService.saveField(any(Field.class))).thenReturn(field);

        mockMvc.perform(post("/api/fields")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(field)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fieldName").value("Field 1"));
    }

    @Test
    public void testDeleteField() throws Exception {
        doNothing().when(fieldService).deleteField(1L);

        mockMvc.perform(delete("/api/fields/1"))
                .andExpect(status().isNoContent());
    }
}

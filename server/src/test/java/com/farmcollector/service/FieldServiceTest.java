package com.farmcollector.service;

import com.farmcollector.model.Field;
import com.farmcollector.repository.FieldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FieldServiceTest {

    @Mock
    private FieldRepository fieldRepository;

    @InjectMocks
    private FieldService fieldService;

    @Test
    public void testGetAllFields() {
        Field field = new Field(1L, null, "Field 1", 100.0, null, null);
        when(fieldRepository.findAll()).thenReturn(List.of(field));

        List<Field> fields = fieldService.getAllFields();
        assertEquals(1, fields.size());
        assertEquals("Field 1", fields.get(0).getFieldName());
    }

    @Test
    public void testGetFieldById() {
        Field field = new Field(1L, null, "Field 1", 100.0, null, null);
        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));

        Optional<Field> foundField = fieldService.getFieldById(1L);
        assertTrue(foundField.isPresent());
        assertEquals("Field 1", foundField.get().getFieldName());
    }

    @Test
    public void testSaveField() {
        Field field = new Field(1L, null, "Field 1", 100.0, null, null);
        when(fieldRepository.save(field)).thenReturn(field);

        Field savedField = fieldService.saveField(field);
        assertEquals("Field 1", savedField.getFieldName());
    }

    @Test
    public void testDeleteField() {
        fieldService.deleteField(1L);
        verify(fieldRepository, times(1)).deleteById(1L);
    }
}

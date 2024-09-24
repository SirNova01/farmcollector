package com.farmcollector.service;

import com.farmcollector.model.Planted;
import com.farmcollector.repository.PlantedRepository;
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
public class PlantedServiceTest {

    @Mock
    private PlantedRepository plantedRepository;

    @InjectMocks
    private PlantedService plantedService;

    @Test
    public void testGetAllPlanted() {
        Planted planted = new Planted(1L, null, null, null, 100.0, 50.0);
        when(plantedRepository.findAll()).thenReturn(List.of(planted));

        List<Planted> plantedList = plantedService.getAllPlanted();
        assertEquals(1, plantedList.size());
        assertEquals(100.0, plantedList.get(0).getPlantingArea());
    }

    @Test
    public void testGetPlantedById() {
        Planted planted = new Planted(1L, null, null, null, 100.0, 50.0);
        when(plantedRepository.findById(1L)).thenReturn(Optional.of(planted));

        Optional<Planted> foundPlanted = plantedService.getPlantedById(1L);
        assertTrue(foundPlanted.isPresent());
        assertEquals(100.0, foundPlanted.get().getPlantingArea());
    }

    @Test
    public void testSavePlanted() {
        Planted planted = new Planted(1L, null, null, null, 100.0, 50.0);
        when(plantedRepository.save(planted)).thenReturn(planted);

        Planted savedPlanted = plantedService.savePlanted(planted);
        assertEquals(100.0, savedPlanted.getPlantingArea());
    }

    @Test
    public void testDeletePlanted() {
        plantedService.deletePlanted(1L);
        verify(plantedRepository, times(1)).deleteById(1L);
    }
}

package com.farmcollector.service;

import com.farmcollector.model.Farm;
import com.farmcollector.repository.FarmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FarmServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FarmService farmService;

    @Test
    public void testGetAllFarms() {
        Farm farm = new Farm(1L, "Test Farm", "Test Location", null);  // Ensure this constructor exists
        when(farmRepository.findAll()).thenReturn(Arrays.asList(farm));

        List<Farm> farms = farmService.getAllFarms();
        assertEquals(1, farms.size());
        assertEquals("Test Farm", farms.get(0).getFarmName());
    }

    @Test
    public void testGetFarmById() {
        Farm farm = new Farm(1L, "Test Farm", "Test Location", null);  // Ensure this constructor exists
        when(farmRepository.findById(1L)).thenReturn(Optional.of(farm));

        Optional<Farm> foundFarm = farmService.getFarmById(1L);
        assertTrue(foundFarm.isPresent());
        assertEquals("Test Farm", foundFarm.get().getFarmName());
    }

    @Test
    public void testSaveFarm() {
        Farm farm = new Farm(1L, "Test Farm", "Test Location", null);  // Ensure this constructor exists
        when(farmRepository.save(farm)).thenReturn(farm);

        Farm savedFarm = farmService.saveFarm(farm);
        assertEquals("Test Farm", savedFarm.getFarmName());
    }

    @Test
    public void testDeleteFarm() {
        farmService.deleteFarm(1L);
        verify(farmRepository, times(1)).deleteById(1L);
    }
}

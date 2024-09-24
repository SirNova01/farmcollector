package com.farmcollector.service;

import com.farmcollector.model.Harvested;
import com.farmcollector.repository.HarvestedRepository;
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
public class HarvestedServiceTest {

    @Mock
    private HarvestedRepository harvestedRepository;

    @InjectMocks
    private HarvestedService harvestedService;

    @Test
    public void testGetAllHarvested() {
        Harvested harvested = new Harvested(1L, null, null, null, 100.0);
        when(harvestedRepository.findAll()).thenReturn(List.of(harvested));

        List<Harvested> harvestedList = harvestedService.getAllHarvested();
        assertEquals(1, harvestedList.size());
        assertEquals(100.0, harvestedList.get(0).getActualYield());
    }

    @Test
    public void testGetHarvestedById() {
        Harvested harvested = new Harvested(1L, null, null, null, 100.0);
        when(harvestedRepository.findById(1L)).thenReturn(Optional.of(harvested));

        Optional<Harvested> foundHarvested = harvestedService.getHarvestedById(1L);
        assertTrue(foundHarvested.isPresent());
        assertEquals(100.0, foundHarvested.get().getActualYield());
    }

    @Test
    public void testSaveHarvested() {
        Harvested harvested = new Harvested(1L, null, null, null, 100.0);
        when(harvestedRepository.save(harvested)).thenReturn(harvested);

        Harvested savedHarvested = harvestedService.saveHarvested(harvested);
        assertEquals(100.0, savedHarvested.getActualYield());
    }

    @Test
    public void testDeleteHarvested() {
        harvestedService.deleteHarvested(1L);
        verify(harvestedRepository, times(1)).deleteById(1L);
    }
}

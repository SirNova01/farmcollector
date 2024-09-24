package com.farmcollector.service;

import com.farmcollector.model.Crop;
import com.farmcollector.repository.CropRepository;
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
public class CropServiceTest {

    @Mock
    private CropRepository cropRepository;

    @InjectMocks
    private CropService cropService;

    @Test
    public void testGetAllCrops() {
        Crop crop = new Crop(1L, "Corn");
        when(cropRepository.findAll()).thenReturn(List.of(crop));

        List<Crop> crops = cropService.getAllCrops();
        assertEquals(1, crops.size());
        assertEquals("Corn", crops.get(0).getCropName());
    }

    @Test
    public void testGetCropById() {
        Crop crop = new Crop(1L, "Corn");
        when(cropRepository.findById(1L)).thenReturn(Optional.of(crop));

        Optional<Crop> foundCrop = cropService.getCropById(1L);
        assertTrue(foundCrop.isPresent());
        assertEquals("Corn", foundCrop.get().getCropName());
    }

    @Test
    public void testSaveCrop() {
        Crop crop = new Crop(1L, "Corn");
        when(cropRepository.save(crop)).thenReturn(crop);

        Crop savedCrop = cropService.saveCrop(crop);
        assertEquals("Corn", savedCrop.getCropName());
    }

    @Test
    public void testDeleteCrop() {
        cropService.deleteCrop(1L);
        verify(cropRepository, times(1)).deleteById(1L);
    }
}

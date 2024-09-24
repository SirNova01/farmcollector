package com.farmcollector.controller;

import com.farmcollector.model.Crop;
import com.farmcollector.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    @Autowired
    private CropService cropService;

    @GetMapping
    public List<Crop> getAllCrops() {
        return cropService.getAllCrops();
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long cropId) {
        Optional<Crop> crop = cropService.getCropById(cropId);
        return crop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Crop createCrop(@RequestBody Crop crop) {
        return cropService.saveCrop(crop);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long cropId) {
        cropService.deleteCrop(cropId);
        return ResponseEntity.noContent().build();
    }
}

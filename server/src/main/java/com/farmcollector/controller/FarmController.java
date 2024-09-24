package com.farmcollector.controller;

import com.farmcollector.model.Farm;
import com.farmcollector.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    private FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarms() {
        return farmService.getAllFarms();
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<Farm> getFarmById(@PathVariable Long farmId) {
        Optional<Farm> farm = farmService.getFarmById(farmId);
        return farm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Farm createFarm(@RequestBody Farm farm) {
        return farmService.saveFarm(farm);
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<Void> deleteFarm(@PathVariable Long farmId) {
        farmService.deleteFarm(farmId);
        return ResponseEntity.noContent().build();
    }
}

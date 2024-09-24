package com.farmcollector.controller;

import com.farmcollector.model.Planted;
import com.farmcollector.service.PlantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planted")
public class PlantedController {

    @Autowired
    private PlantedService plantedService;

    @GetMapping
    public List<Planted> getAllPlanted() {
        return plantedService.getAllPlanted();
    }

    @GetMapping("/{plantedId}")
    public ResponseEntity<Planted> getPlantedById(@PathVariable Long plantedId) {
        Optional<Planted> planted = plantedService.getPlantedById(plantedId);
        return planted.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/season/{seasonId}")
    public List<Planted> getPlantedBySeason(@PathVariable Long seasonId) {
        return plantedService.getPlantedBySeason(seasonId);
    }

    @GetMapping("/crop/{cropId}")
    public List<Planted> getPlantedByCrop(@PathVariable Long cropId) {
        return plantedService.getPlantedByCrop(cropId);
    }

    @GetMapping("/farm/{farmId}")
    public List<Planted> getPlantedByFarm(@PathVariable Long farmId) {
        return plantedService.getPlantedByFarm(farmId);
    }

    @PostMapping
    public Planted createPlanted(@RequestBody Planted planted) {
        return plantedService.savePlanted(planted);
    }

    @DeleteMapping("/{plantedId}")
    public ResponseEntity<Void> deletePlanted(@PathVariable Long plantedId) {
        plantedService.deletePlanted(plantedId);
        return ResponseEntity.noContent().build();
    }
}

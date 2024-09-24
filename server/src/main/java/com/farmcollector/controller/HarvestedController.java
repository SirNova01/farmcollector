package com.farmcollector.controller;

import com.farmcollector.model.Harvested;
import com.farmcollector.service.HarvestedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/harvested")
public class HarvestedController {

    @Autowired
    private HarvestedService harvestedService;

    @GetMapping
    public List<Harvested> getAllHarvested() {
        return harvestedService.getAllHarvested();
    }

    @GetMapping("/{harvestedId}")
    public ResponseEntity<Harvested> getHarvestedById(@PathVariable Long harvestedId) {
        Optional<Harvested> harvested = harvestedService.getHarvestedById(harvestedId);
        return harvested.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/season/{seasonId}")
    public List<Harvested> getHarvestedBySeason(@PathVariable Long seasonId) {
        return harvestedService.getHarvestedBySeason(seasonId);
    }

    @GetMapping("/crop/{cropId}")
    public List<Harvested> getHarvestedByCrop(@PathVariable Long cropId) {
        return harvestedService.getHarvestedByCrop(cropId);
    }

    @GetMapping("/farm/{farmId}")
    public List<Harvested> getHarvestedByFarm(@PathVariable Long farmId) {
        return harvestedService.getHarvestedByFarm(farmId);
    }

    @PostMapping
    public Harvested createHarvested(@RequestBody Harvested harvested) {
        return harvestedService.saveHarvested(harvested);
    }

    @DeleteMapping("/{harvestedId}")
    public ResponseEntity<Void> deleteHarvested(@PathVariable Long harvestedId) {
        harvestedService.deleteHarvested(harvestedId);
        return ResponseEntity.noContent().build();
    }
}

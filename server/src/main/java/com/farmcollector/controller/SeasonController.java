package com.farmcollector.controller;

import com.farmcollector.model.Season;
import com.farmcollector.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seasons")
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping
    public List<Season> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{seasonId}")
    public ResponseEntity<Season> getSeasonById(@PathVariable Long seasonId) {
        Optional<Season> season = seasonService.getSeasonById(seasonId);
        return season.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Season createSeason(@RequestBody Season season) {
        return seasonService.saveSeason(season);
    }

    @DeleteMapping("/{seasonId}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long seasonId) {
        seasonService.deleteSeason(seasonId);
        return ResponseEntity.noContent().build();
    }
}

package com.farmcollector.service;

import com.farmcollector.model.Season;
import com.farmcollector.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }

    public Optional<Season> getSeasonById(Long seasonId) {
        return seasonRepository.findById(seasonId);
    }

    public Season saveSeason(Season season) {
        return seasonRepository.save(season);
    }

    public void deleteSeason(Long seasonId) {
        seasonRepository.deleteById(seasonId);
    }
}

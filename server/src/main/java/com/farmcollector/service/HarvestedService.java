package com.farmcollector.service;

import com.farmcollector.model.Harvested;
import com.farmcollector.repository.HarvestedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HarvestedService {

    @Autowired
    private HarvestedRepository harvestedRepository;

    public List<Harvested> getAllHarvested() {
        return harvestedRepository.findAll();
    }

    public Optional<Harvested> getHarvestedById(Long harvestId) {
        return harvestedRepository.findById(harvestId);
    }

    public List<Harvested> getHarvestedBySeason(Long seasonId) {
        return harvestedRepository.findBySeason_SeasonId(seasonId);
    }

    public List<Harvested> getHarvestedByCrop(Long cropId) {
        return harvestedRepository.findByCrop_CropId(cropId);
    }

    public List<Harvested> getHarvestedByFarm(Long farmId) {
        return harvestedRepository.findByField_Farm_FarmId(farmId);
    }

    public Harvested saveHarvested(Harvested harvested) {
        return harvestedRepository.save(harvested);
    }

    public void deleteHarvested(Long harvestId) {
        harvestedRepository.deleteById(harvestId);
    }
}

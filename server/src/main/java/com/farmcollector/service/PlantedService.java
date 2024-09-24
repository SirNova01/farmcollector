package com.farmcollector.service;

import com.farmcollector.model.Planted;
import com.farmcollector.repository.PlantedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantedService {

    @Autowired
    private PlantedRepository plantedRepository;

    public List<Planted> getAllPlanted() {
        return plantedRepository.findAll();
    }

    public Optional<Planted> getPlantedById(Long plantedId) {
        return plantedRepository.findById(plantedId);
    }

    public List<Planted> getPlantedBySeason(Long seasonId) {
        return plantedRepository.findBySeason_SeasonId(seasonId);
    }

    public List<Planted> getPlantedByCrop(Long cropId) {
        return plantedRepository.findByCrop_CropId(cropId);
    }

    public List<Planted> getPlantedByFarm(Long farmId) {
        return plantedRepository.findByField_Farm_FarmId(farmId);
    }

    public Planted savePlanted(Planted planted) {
        return plantedRepository.save(planted);
    }

    public void deletePlanted(Long plantedId) {
        plantedRepository.deleteById(plantedId);
    }
}

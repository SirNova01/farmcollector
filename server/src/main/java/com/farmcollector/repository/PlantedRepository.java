package com.farmcollector.repository;

import com.farmcollector.model.Planted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantedRepository extends JpaRepository<Planted, Long> {
    List<Planted> findBySeason_SeasonId(Long seasonId);
    List<Planted> findByCrop_CropId(Long cropId);
    List<Planted> findByField_Farm_FarmId(Long farmId);
    // Custom queries to fetch planted data by season, crop, or farm
}

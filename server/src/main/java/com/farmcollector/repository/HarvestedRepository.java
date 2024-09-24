package com.farmcollector.repository;

import com.farmcollector.model.Harvested;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestedRepository extends JpaRepository<Harvested, Long> {
    List<Harvested> findBySeason_SeasonId(Long seasonId);
    List<Harvested> findByCrop_CropId(Long cropId);
    List<Harvested> findByField_Farm_FarmId(Long farmId);
    // Custom queries to fetch harvested data by season, crop, or farm
}

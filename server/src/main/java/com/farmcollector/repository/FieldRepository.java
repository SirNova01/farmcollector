package com.farmcollector.repository;

import com.farmcollector.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFarm_FarmId(Long farmId);
    // This method will return fields associated with a specific farm
}

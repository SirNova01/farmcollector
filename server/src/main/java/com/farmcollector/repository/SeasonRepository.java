package com.farmcollector.repository;

import com.farmcollector.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    // You can add custom query methods if needed
}

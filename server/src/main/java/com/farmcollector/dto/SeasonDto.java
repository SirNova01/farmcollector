package com.farmcollector.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SeasonDto {
    private Long seasonId;
    private String seasonName;
    private LocalDate startDate;
    private LocalDate endDate;
}

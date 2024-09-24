package com.farmcollector.dto;

import lombok.Data;

@Data
public class HarvestedDto {
    private Long harvestId;
    private Long fieldId;  // Foreign key to Field
    private Long cropId;   // Foreign key to Crop
    private Long seasonId; // Foreign key to Season
    private Double actualYield;
}

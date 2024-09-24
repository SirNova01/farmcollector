package com.farmcollector.dto;

import lombok.Data;

@Data
public class PlantedDto {
    private Long plantedId;
    private Long fieldId;  // Foreign key to Field
    private Long cropId;   // Foreign key to Crop
    private Long seasonId; // Foreign key to Season
    private Double plantingArea;
    private Double expectedYield;
}

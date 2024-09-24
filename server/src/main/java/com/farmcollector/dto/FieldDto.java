package com.farmcollector.dto;

import lombok.Data;

@Data
public class FieldDto {
    private Long fieldId;
    private String fieldName;
    private Double areaAcres;
    private Long farmId;  // Foreign key to Farm
}

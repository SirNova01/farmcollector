package com.farmcollector.dto;

import lombok.Data;

@Data
public class ReportDto {
    private String farmName;
    private String cropType;
    private Double expectedProduct;
    private Double actualProduct;
}

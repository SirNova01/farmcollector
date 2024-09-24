package com.farmcollector.dto;

import lombok.Data;
import java.util.List;

@Data
public class FarmDto {
    private Long farmId;
    private String farmName;
    private String location;
    private List<FieldDto> fields;  // Include fields associated with the farm (optional)
}

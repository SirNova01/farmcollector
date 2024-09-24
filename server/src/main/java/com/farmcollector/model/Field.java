package com.farmcollector.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    private String fieldName;

    @Column(nullable = false)
    private Double areaAcres;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Planted> plantedList;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Harvested> harvestedList;
}

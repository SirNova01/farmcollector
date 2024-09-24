package com.farmcollector.controller;

import com.farmcollector.model.Field;
import com.farmcollector.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping
    public List<Field> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long fieldId) {
        Optional<Field> field = fieldService.getFieldById(fieldId);
        return field.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farm/{farmId}")
    public List<Field> getFieldsByFarm(@PathVariable Long farmId) {
        return fieldService.getFieldsByFarmId(farmId);
    }

    @PostMapping
    public Field createField(@RequestBody Field field) {
        return fieldService.saveField(field);
    }

    @DeleteMapping("/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable Long fieldId) {
        fieldService.deleteField(fieldId);
        return ResponseEntity.noContent().build();
    }
}

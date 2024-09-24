package com.farmcollector.service;

import com.farmcollector.model.Field;
import com.farmcollector.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Optional<Field> getFieldById(Long fieldId) {
        return fieldRepository.findById(fieldId);
    }

    public List<Field> getFieldsByFarmId(Long farmId) {
        return fieldRepository.findByFarm_FarmId(farmId);
    }

    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public void deleteField(Long fieldId) {
        fieldRepository.deleteById(fieldId);
    }
}

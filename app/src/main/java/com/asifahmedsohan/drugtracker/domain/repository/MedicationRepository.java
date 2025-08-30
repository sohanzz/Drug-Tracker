package com.asifahmedsohan.drugtracker.domain.repository;

import androidx.lifecycle.LiveData;

import com.asifahmedsohan.drugtracker.domain.entity.Medication;

import java.util.List;

public interface MedicationRepository {
    LiveData<List<Medication>> getAllMedications();
    void addMedication(Medication medication);
    void deleteMedication(Medication medication);
}



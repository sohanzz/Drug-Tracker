package com.asifahmedsohan.drugtracker.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asifahmedsohan.drugtracker.domain.entity.Medication;
import com.asifahmedsohan.drugtracker.domain.repository.MedicationRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MedicationViewModel extends ViewModel {

    private final MedicationRepository repository;
    private LiveData<List<Medication>> medicationsLiveData = new MutableLiveData<>();

    @Inject
    public MedicationViewModel(MedicationRepository repository) {
        this.repository = repository;
        loadMedications();
    }

    public LiveData<List<Medication>> getMedications() {
        return medicationsLiveData;
    }

    public void loadMedications() {
        this.medicationsLiveData = repository.getAllMedications();
    }

    public void deleteMedication(Medication medication) {
        repository.deleteMedication(medication);
        loadMedications();
    }

    public void addMedication(Medication medication) {
        repository.addMedication(medication);
        loadMedications();
    }
}

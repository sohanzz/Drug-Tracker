package com.asifahmedsohan.drugtracker.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asifahmedsohan.drugtracker.domain.entity.Drug;
import com.asifahmedsohan.drugtracker.domain.entity.Medication;
import com.asifahmedsohan.drugtracker.domain.repository.DrugRepository;
import com.asifahmedsohan.drugtracker.domain.repository.MedicationRepository;
import com.asifahmedsohan.drugtracker.utils.Result;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DrugViewModel extends ViewModel {

    private final DrugRepository drugRepository;
    private final MedicationRepository medicationRepository;
    private final MutableLiveData<Result<List<Drug>>> drugs = new MutableLiveData<>();

    @Inject
    public DrugViewModel(DrugRepository drugRepository, MedicationRepository medicationRepository) {
        this.drugRepository = drugRepository;
        this.medicationRepository = medicationRepository;
    }

    public LiveData<Result<List<Drug>>> getDrugs(String name) {
        return drugRepository.fetchDrugs(name);
    }

    public void saveMedication(Drug drug) {
        medicationRepository.addMedication(new Medication(drug.getRxcui(), drug.getName(), false));
    }
}


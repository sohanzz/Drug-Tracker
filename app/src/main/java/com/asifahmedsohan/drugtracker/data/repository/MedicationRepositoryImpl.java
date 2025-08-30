package com.asifahmedsohan.drugtracker.data.repository;


import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.asifahmedsohan.drugtracker.data.local.AppDatabase;
import com.asifahmedsohan.drugtracker.domain.entity.Medication;
import com.asifahmedsohan.drugtracker.domain.repository.MedicationRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MedicationRepositoryImpl implements MedicationRepository {

    private final AppDatabase db;
    private final MutableLiveData<List<Medication>> medicationsLiveData = new MutableLiveData<>();

    @Inject
    public MedicationRepositoryImpl(Context context) {
        db = androidx.room.Room.databaseBuilder(context, AppDatabase.class, "medication_db")
                .allowMainThreadQueries()
                .build();
        loadMedications();
    }

    private void loadMedications() {
        medicationsLiveData.setValue(db.medicationDao().getAllMedications());
    }

    @Override
    public LiveData<List<Medication>> getAllMedications() {
        loadMedications();
        return medicationsLiveData;
    }

    @Override
    public void addMedication(Medication medication) {
        db.medicationDao().insert(medication);
        loadMedications();
    }

    @Override
    public void deleteMedication(Medication medication) {
        db.medicationDao().delete(medication);
        loadMedications();
    }
}

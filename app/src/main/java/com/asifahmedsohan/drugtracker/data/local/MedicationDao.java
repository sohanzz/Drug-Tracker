package com.asifahmedsohan.drugtracker.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.asifahmedsohan.drugtracker.domain.entity.Medication;

import java.util.List;

@Dao
public interface MedicationDao {

    @Query("SELECT * FROM medications ORDER BY isCreated DESC")
    List<Medication> getAllMedications();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Medication medication);

    @Delete
    void delete(Medication medication);
}

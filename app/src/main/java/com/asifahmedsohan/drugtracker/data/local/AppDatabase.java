package com.asifahmedsohan.drugtracker.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.asifahmedsohan.drugtracker.domain.entity.Medication;

@Database(entities = {Medication.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MedicationDao medicationDao();
}



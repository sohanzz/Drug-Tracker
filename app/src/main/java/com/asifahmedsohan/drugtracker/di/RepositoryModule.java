package com.asifahmedsohan.drugtracker.di;

import android.content.Context;

import com.asifahmedsohan.drugtracker.data.remote.DrugApiService;
import com.asifahmedsohan.drugtracker.data.repository.DrugRepositoryImpl;
import com.asifahmedsohan.drugtracker.data.repository.MedicationRepositoryImpl;
import com.asifahmedsohan.drugtracker.domain.repository.DrugRepository;
import com.asifahmedsohan.drugtracker.domain.repository.MedicationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public static DrugRepository provideDrugRepository(DrugApiService drugApiService) {
        return new DrugRepositoryImpl(drugApiService);
    }

    @Provides
    @Singleton
    public static MedicationRepository provideMedicationRepository(Context context) {
        return new MedicationRepositoryImpl(context);
    }

    @Provides
    @Singleton
    public static Context provideContext(android.app.Application application) {
        return application.getApplicationContext();
    }

}

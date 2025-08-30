package com.asifahmedsohan.drugtracker.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.asifahmedsohan.drugtracker.data.remote.DrugApiService;
import com.asifahmedsohan.drugtracker.data.remote.DrugResponse;
import com.asifahmedsohan.drugtracker.domain.entity.Drug;
import com.asifahmedsohan.drugtracker.domain.repository.DrugRepository;
import com.asifahmedsohan.drugtracker.utils.NetworkController;
import com.asifahmedsohan.drugtracker.utils.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

@Singleton
public class DrugRepositoryImpl implements DrugRepository {

    private final DrugApiService api;

    @Inject
    public DrugRepositoryImpl(DrugApiService api) {
        this.api = api;
    }

    @Override
    public LiveData<Result<List<Drug>>> fetchDrugs(String name) {
        MutableLiveData<Result<List<Drug>>> liveData = new MutableLiveData<>();
        Call<DrugResponse> call = api.getDrugs(name);

        NetworkController.makeRequest(call, result -> {
            if (result.isSuccess() && result.getData() != null) {
                DrugResponse response = result.getData();
                List<Drug> filteredDrugs = new ArrayList<>();

                if (response.getDrugGroup() != null && response.getDrugGroup().getConceptGroup() != null) {
                    int count = 0;
                    for (DrugResponse.ConceptGroup group : response.getDrugGroup().getConceptGroup()) {
                        if (group.getConceptProperties() != null) {
                            for (DrugResponse.ConceptProperty drug : group.getConceptProperties()) {
                                if ("SBD".equals(drug.getTty()) && count < 10) {
                                    filteredDrugs.add(new Drug(drug.getRxcui(), drug.getName()));
                                    count++;
                                }
                            }
                        }
                    }
                }

                liveData.postValue(Result.success(filteredDrugs));
            } else {
                liveData.postValue(Result.error(result.getError()));
            }
        });

        return liveData;
    }
}

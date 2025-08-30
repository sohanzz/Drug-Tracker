package com.asifahmedsohan.drugtracker.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DrugApiService {

    @GET("drugs.json")
    Call<DrugResponse> getDrugs(@Query("name") String name);
}

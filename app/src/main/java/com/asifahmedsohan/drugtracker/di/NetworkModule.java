package com.asifahmedsohan.drugtracker.di;

import com.asifahmedsohan.drugtracker.data.remote.DrugApiService;
import com.asifahmedsohan.drugtracker.utils.NetworkController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public abstract class NetworkModule {

    private static final String BASE_URL = "https://rxnav.nlm.nih.gov/REST/";

    @Provides
    @Singleton
    public static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    @Provides
    @Singleton
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static DrugApiService provideDrugApi(Retrofit retrofit) {
        return retrofit.create(DrugApiService.class);
    }

    @Provides
    @Singleton
    public static NetworkController provideNetworkController() {
        return new NetworkController();
    }

}

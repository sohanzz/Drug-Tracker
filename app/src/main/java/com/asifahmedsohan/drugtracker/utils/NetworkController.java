package com.asifahmedsohan.drugtracker.utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkController {

    public static <T> void makeRequest(Call<T> call, NetworkCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onComplete(Result.success(response.body()));
                } else {
                    callback.onComplete(Result.error("Server error"));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.onComplete(Result.error(t.getMessage()));
            }
        });
    }

    public interface NetworkCallback<T> {
        void onComplete(Result<T> result);
    }
}


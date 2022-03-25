package com.armados.app.epraxeis;

import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class RetrofitSyncCall<T> {

    private final String TAG = getClass().getSimpleName();

    private final Call<T> call;

    public RetrofitSyncCall(final Call<T> call) {
        this.call = call;
    }

    public T execute() {

        try {
            Call<T> callClone = call.clone();

            Log.d(TAG, "Api Call URL: " + callClone.request().url());

            Response<T> response = callClone.execute();

            if (response.isSuccessful()) {
                return (T) response.body();
            } else {
                Log.d(TAG, "Error response HTTP code " + response.code());
                Log.d(TAG, "errorBody " + response.errorBody().string());
            }

        } catch (IOException ex) {
            Log.e(TAG, "Network failure?");
        }

        return null;
    }
}

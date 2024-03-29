package com.example.projectzeus.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.interfaces.ApiServices;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.response.LedCTResponse;
import com.example.projectzeus.models.response.LedPTResponse;
import com.example.projectzeus.models.response.ReporteResponse;
import com.example.projectzeus.models.response.UbicacionResponse;
import com.example.projectzeus.singleton.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegistroRepository {

    private ApiServices apiService;

    public RegistroRepository() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiServices.class);
    }

    public LiveData<UbicacionResponse> getUbicacion(Context context, Long cocheId) {
        MutableLiveData<UbicacionResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("UserRepository", "validateUser: " + accessToken + " " + cocheId);

        if (accessToken == null || cocheId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.getUbicacion(cocheId, authorizationHeader).enqueue(new Callback<UbicacionResponse>() {
            @Override
            public void onResponse(Call<UbicacionResponse> call, Response<UbicacionResponse> response) {
                if (response.isSuccessful()) {
                    UbicacionResponse ubicacion = response.body();
                    resultLiveData.setValue(ubicacion);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UbicacionResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<LedCTResponse> getLed(Context context, Long cocheId) {
        MutableLiveData<LedCTResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("UserRepository", "validateUser: " + accessToken + " " + cocheId);

        if (accessToken == null || cocheId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.getLed(cocheId, authorizationHeader).enqueue(new Callback<LedCTResponse>() {
            @Override
            public void onResponse(Call<LedCTResponse> call, Response<LedCTResponse> response) {
                if (response.isSuccessful()) {
                    LedCTResponse ledcontrol = response.body();
                    resultLiveData.setValue(ledcontrol);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LedCTResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<LedPTResponse> setLed(Context context, Long cocheId, LedRequest ledre) {
        MutableLiveData<LedPTResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("UserRepository", "validateUser: " + accessToken + " " + cocheId);

        if (accessToken == null || ledre == null || cocheId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.setLed(cocheId, authorizationHeader, ledre).enqueue(new Callback<LedPTResponse>() {
            @Override
            public void onResponse(Call<LedPTResponse> call, Response<LedPTResponse> response) {
                if (response.isSuccessful()) {
                    LedPTResponse ledcontrol = response.body();
                    resultLiveData.setValue(ledcontrol);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LedPTResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<ReporteResponse> getReporte(Context context, Long cocheId) {
        MutableLiveData<ReporteResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("UserRepository", "validateUser: " + accessToken + " " + cocheId);

        if (accessToken == null || cocheId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.getReporte(cocheId, authorizationHeader).enqueue(new Callback<ReporteResponse>() {
            @Override
            public void onResponse(Call<ReporteResponse> call, Response<ReporteResponse> response) {
                if (response.isSuccessful()) {
                    ReporteResponse reporte = response.body();
                    resultLiveData.setValue(reporte);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ReporteResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }
}

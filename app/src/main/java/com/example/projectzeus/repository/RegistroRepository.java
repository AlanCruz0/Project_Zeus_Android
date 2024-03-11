package com.example.projectzeus.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.interfaces.ApiServices;
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
}

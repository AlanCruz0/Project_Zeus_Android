package com.example.projectzeus.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.interfaces.ApiServices;
import com.example.projectzeus.models.request.AgregarRequest;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.response.AgregarResponse;
import com.example.projectzeus.models.response.CocheSensorsResponse;
import com.example.projectzeus.models.response.LedPTResponse;
import com.example.projectzeus.singleton.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CocheRepository {
    private ApiServices apiService;

    public CocheRepository() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiServices.class);
    }

    public LiveData<AgregarResponse> addCoche(Context context, AgregarRequest addre) {
        MutableLiveData<AgregarResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("CocheRepository", "validateUser: " + accessToken);

        if (accessToken == null || addre == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.addCoche(authorizationHeader, addre).enqueue(new Callback<AgregarResponse>() {
            @Override
            public void onResponse(Call<AgregarResponse> call, Response<AgregarResponse> response) {
                AgregarResponse agregar = response.body();
                if (agregar != null) {
                    if (response.isSuccessful()) {
                        resultLiveData.setValue(agregar);
                    } else {
                        resultLiveData.setValue(null);
                    }
                } else {
                    resultLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<AgregarResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });
        Log.d("CocheRepository", "addCoche: " + resultLiveData.getValue());
        return resultLiveData;
    }

    public LiveData<CocheSensorsResponse> getSensors(Context context, Long cocheId) {
        MutableLiveData<CocheSensorsResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Log.d("CocheRepository", "validateUser: " + accessToken);

        if (accessToken == null || cocheId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;

        apiService.getSensors(cocheId, authorizationHeader).enqueue(new Callback<CocheSensorsResponse>() {
            @Override
            public void onResponse(Call<CocheSensorsResponse> call, Response<CocheSensorsResponse> response) {
                CocheSensorsResponse sensors = response.body();
                if (sensors != null) {
                    if (response.isSuccessful()) {
                        resultLiveData.setValue(sensors);
                    } else {
                        resultLiveData.setValue(null);
                    }
                } else {
                    resultLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<CocheSensorsResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });
        return resultLiveData;
    }
}

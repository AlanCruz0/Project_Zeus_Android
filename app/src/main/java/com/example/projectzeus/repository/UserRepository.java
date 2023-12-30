package com.example.projectzeus.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzeus.SharedPrefHelper;
import com.example.projectzeus.interfaces.ApiServices;
import com.example.projectzeus.models.request.RegisterRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.response.ValidateResponse;
import com.example.projectzeus.singleton.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private ApiServices apiService;

    public UserRepository() {
        Retrofit retrofit = RetrofitClient.getInstance();
        apiService = retrofit.create(ApiServices.class);
    }

    public LiveData<UserResponse> loginUser(UserRequest user) {
        MutableLiveData<UserResponse> resultLiveData = new MutableLiveData<>();

        apiService.loginUser(user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse token = response.body();
                    resultLiveData.setValue(token);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<UserResponse> registerUser(RegisterRequest user) {
        MutableLiveData<UserResponse> resultLiveData = new MutableLiveData<>();

        apiService.registerUser(user).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse token = response.body();
                    resultLiveData.setValue(token);
                } else {
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<LogoutResponse> logoutUser(Context context) {
        MutableLiveData<LogoutResponse> resultLiveData = new MutableLiveData<>();
        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();

        String authorizationHeader = "Bearer " + accessToken;
        apiService.logoutUser(authorizationHeader).enqueue(new Callback<LogoutResponse>() {
            @Override
            public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                if (response.isSuccessful()) {
                    LogoutResponse logresp = response.body();
                    resultLiveData.setValue(logresp);
                } else {
                    resultLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<LogoutResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }

    public LiveData<ValidateResponse> validateUser(Context context) {
        MutableLiveData<ValidateResponse> resultLiveData = new MutableLiveData<>();

        SharedPrefHelper sharedPreferencesHelper = new SharedPrefHelper(context);
        String accessToken = sharedPreferencesHelper.getToken();
        Long userId = sharedPreferencesHelper.getUserId();
        Log.d("UserRepository", "validateUser: " + accessToken + " " + userId);

        if (accessToken == null || userId == null) {
            resultLiveData.setValue(null);
            return resultLiveData;
        }

        String authorizationHeader = "Bearer " + accessToken;
        apiService.validateUser(userId, authorizationHeader).enqueue(new Callback<ValidateResponse>() {
            @Override
            public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                if (response.isSuccessful()) {
                    ValidateResponse valresp = response.body();
                    resultLiveData.setValue(valresp);
                } else {
                    resultLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<ValidateResponse> call, Throwable t) {
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }
}

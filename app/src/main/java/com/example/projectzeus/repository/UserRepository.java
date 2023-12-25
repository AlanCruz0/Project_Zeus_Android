package com.example.projectzeus.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.projectzeus.interfaces.ApiServices;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.user;
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
                    // Manejar error
                    resultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Manejar error
                resultLiveData.setValue(null);
            }
        });

        return resultLiveData;
    }
}

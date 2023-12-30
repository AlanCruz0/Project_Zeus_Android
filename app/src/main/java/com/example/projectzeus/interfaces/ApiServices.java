package com.example.projectzeus.interfaces;

import com.example.projectzeus.models.request.RegisterRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.response.ValidateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiServices {
    @POST("login")
    Call<UserResponse> loginUser(@Body UserRequest user);

    @POST("register")
    Call<UserResponse> registerUser(@Body RegisterRequest user);

    @POST("logout")
    Call<LogoutResponse> logoutUser(@Header("Authorization") String authorizationHeader);

    @POST("validar/{user_id}")
    Call<ValidateResponse> validateUser(@Path("user_id") Long userId, @Header("Authorization") String authorizationHeader);
}

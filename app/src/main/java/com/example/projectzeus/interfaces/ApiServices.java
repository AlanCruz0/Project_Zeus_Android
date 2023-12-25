package com.example.projectzeus.interfaces;

import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("login")
    Call<UserResponse> loginUser(@Body UserRequest user);
}

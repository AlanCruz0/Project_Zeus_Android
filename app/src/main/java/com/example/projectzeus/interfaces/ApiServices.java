package com.example.projectzeus.interfaces;

import com.example.projectzeus.models.request.AgregarRequest;
import com.example.projectzeus.models.request.LedRequest;
import com.example.projectzeus.models.request.RegisterRequest;
import com.example.projectzeus.models.request.UserRequest;
import com.example.projectzeus.models.response.AgregarResponse;
import com.example.projectzeus.models.response.CocheSensorsResponse;
import com.example.projectzeus.models.response.CochesResponse;
import com.example.projectzeus.models.response.LedCTResponse;
import com.example.projectzeus.models.response.LedPTResponse;
import com.example.projectzeus.models.response.LogoutResponse;
import com.example.projectzeus.models.response.ReporteResponse;
import com.example.projectzeus.models.response.UbicacionResponse;
import com.example.projectzeus.models.response.UserResponse;
import com.example.projectzeus.models.response.ValidateResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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

    @POST("coche/nuevo")
    Call<AgregarResponse> addCoche(@Header("Authorization") String authorizationHeader, @Body AgregarRequest agregarRequest);

    @GET("coches/{user_id}")
    Call<CochesResponse> getCoches(@Path("user_id") Long userId, @Header("Authorization") String authorizationHeader);

    @GET("coche/sensors/{coche_id}")
    Call<CocheSensorsResponse> getSensors(@Path("coche_id") Long cocheId, @Header("Authorization") String authorizationHeader);

    @GET("ubicacion/{coche_id}")
    Call<UbicacionResponse> getUbicacion(@Path("coche_id") Long cocheId, @Header("Authorization") String authorizationHeader);

    @GET("ledcontrol/{coche_id}")
    Call<LedCTResponse> getLed(@Path("coche_id") Long cocheId, @Header("Authorization") String authorizationHeader);

    @POST("ledcontrol/{coche_id}")
    Call<LedPTResponse> setLed(@Path("coche_id") Long cocheId, @Header("Authorization") String authorizationHeader, @Body LedRequest ledRequest);

    @GET("reportedis/{coche_id}")
    Call<ReporteResponse> getReporte(@Path("coche_id") Long cocheId, @Header("Authorization") String authorizationHeader);
}

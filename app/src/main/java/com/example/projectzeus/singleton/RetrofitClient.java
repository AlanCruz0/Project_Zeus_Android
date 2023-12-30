package com.example.projectzeus.singleton;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://3.147.6.3/api/";
    private static Retrofit retrofit;
    public static Retrofit getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okhttp3.OkHttpClient.Builder httpClient = new okhttp3.OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }
}

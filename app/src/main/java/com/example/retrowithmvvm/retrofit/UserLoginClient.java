package com.example.retrowithmvvm.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginClient {

    private static final String BASE_URL = "https://reqres.in/api/";
    private static UserLoginClient userLoginClient;
    private final Retrofit retrofit;
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    UserLoginClient(){
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized UserLoginClient getInstance(){
        if(userLoginClient==null)
            userLoginClient = new UserLoginClient();
        return userLoginClient;
    }

    public UserLoginInterface getApi() {
        return retrofit.create(UserLoginInterface.class);
    }
}

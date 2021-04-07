package com.example.retrowithmvvm.retrofit;

import com.example.retrowithmvvm.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserLoginInterface {

    @FormUrlEncoded
    @POST("login")
    Call<User> getUserLoginInfo(@Field("email") String email, @Field("password") String password);
}

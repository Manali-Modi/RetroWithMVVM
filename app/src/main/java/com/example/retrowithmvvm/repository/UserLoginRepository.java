package com.example.retrowithmvvm.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.retrowithmvvm.interfaces.UserLoginResponseInterface;
import com.example.retrowithmvvm.model.User;
import com.example.retrowithmvvm.retrofit.UserLoginClient;

import java.io.IOException;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginRepository {

    @SuppressLint("StaticFieldLeak")
    public void getLoginStatus(UserLoginResponseInterface responseInterface, String email, String pwd){
        Call<User> userCall = UserLoginClient.getInstance().getApi().getUserLoginInfo(email,pwd);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                Log.d("code",String.valueOf(response.code()));
                if(response.code()==200){
                    responseInterface.onApiResponse("Login Successfully");
                }
                else {
                    try {
                        assert response.errorBody() != null;
                        responseInterface.onApiResponse(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });

    }
}

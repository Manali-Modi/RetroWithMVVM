package com.example.retrowithmvvm.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.retrowithmvvm.interfaces.UserLoginResponseInterface;
import com.example.retrowithmvvm.model.User;
import com.example.retrowithmvvm.repository.UserLoginRepository;
import com.example.retrowithmvvm.retrofit.UserLoginClient;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginViewModel extends ViewModel {

    UserLoginRepository loginRepository = new UserLoginRepository();
    public void getUserLoginStatus(UserLoginResponseInterface responseInterface, String email, String pwd){
        loginRepository.getLoginStatus(responseInterface, email, pwd);
    }
}

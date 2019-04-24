package com.example.kishan.recyclerrrr.retrofit;


import com.example.kishan.recyclerrrr.Models.login.LoginResponse;
import com.example.kishan.recyclerrrr.Models.registration.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

    //Login
    public static void loginUser(String companyName, String userId, String password, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().login(companyName, userId, password).enqueue(callback);
    }

    //Registration
    public static void registerUser(Integer agentId, String email, String firstName, String lastName, Number latitude, Number longitude, String message, Callback<RegisterResponse> callback) {
        RetrofitClient.getClient().register(agentId, email, firstName, lastName, latitude, longitude, message).enqueue(callback);
    }


}



package com.example.kishan.recyclerrrr.retrofit;


import com.example.kishan.recyclerrrr.Models.login.LoginResponse;
import com.example.kishan.recyclerrrr.Models.registration.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

    //Login
    public static void loginUser(RequestBody u_email, RequestBody password, RequestBody company_name, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().login(u_email, password, company_name).enqueue(callback);
    }

    //Registration
    public static void registerUser(RequestBody email, RequestBody firstName, RequestBody lastName, RequestBody latitude, RequestBody longitude, RequestBody message, Callback<RegisterResponse> callback) {
        RetrofitClient.getClient().register(email, firstName, lastName, latitude, longitude, message).enqueue(callback);
    }


}



package com.example.kishan.recyclerrrr.retrofit;


import com.example.kishan.recyclerrrr.Models.login.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

    //Login
    public static void loginUser(RequestBody u_email, RequestBody password, RequestBody company_name, Callback<LoginResponse>callback ){
        RetrofitClient.getClient().login(u_email,password,company_name).enqueue(callback);
    }

////    //Registration
//   public static void registerUser(RequestBody u_name , RequestBody u_email , RequestBody u_mobile, RequestBody u_country , RequestBody u_password, Callback<RegistrationResponse> callback){
//        RetrofitClient.getClient().register(u_name,u_email,u_mobile,u_country,u_password).enqueue(callback);
//   }
//
//    //Category                             //imp
//    public static void getCourses(Callback <List<CatagoryResponse>> callback) {
//        RetrofitClient.getClient().getCourse().enqueue(callback);
//    }


    //Subcatagory
   // public static void getCourses(Callback<CatagoryResponse> callback) {
       // RetrofitClient.getClient().getCourse().enqueue(callback);
    //}
}




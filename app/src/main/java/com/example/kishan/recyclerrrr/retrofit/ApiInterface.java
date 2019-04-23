package com.example.kishan.recyclerrrr.retrofit;



import com.example.kishan.recyclerrrr.Models.login.LoginResponse;
import com.example.kishan.recyclerrrr.Models.registration.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    //login
    @Multipart
    @POST("test_api?action=login")
    //@Headers({"test_api?action=login"})
    Call<LoginResponse>  login(@Part("email_id") RequestBody email_id,
                               @Part("password") RequestBody password,
                               @Part("company_name") RequestBody company_name);

    //Registration
  @Multipart
    @POST("test_api?action=reg")
   //@Headers({"Content-Type: application/json", "Accept: application/json"})
    Call<RegisterResponse> register(@Part("email") RequestBody email,
                                    @Part("firstName") RequestBody firstName,
                                    @Part("lastName") RequestBody lastName,
                                    @Part("latitude") RequestBody latitude,
                                    @Part("longitude") RequestBody longitude,
                                    @Part("message") RequestBody message);



}

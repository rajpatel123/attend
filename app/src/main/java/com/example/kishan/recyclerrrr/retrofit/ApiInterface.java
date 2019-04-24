package com.example.kishan.recyclerrrr.retrofit;



import com.example.kishan.recyclerrrr.Models.login.LoginResponse;
import com.example.kishan.recyclerrrr.Models.registration.RegisterResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("user/authenticate/{companyName}/{userId}/{password}")
    Call<LoginResponse> login(@Path("companyName") String companyName,
                              @Path("userId") String userId,
                              @Path("password") String password);

    //Registration

    @POST("agent/saveDealer")

    Call<RegisterResponse> register(@Path("agentId")int agentId,
                                    @Path("email") String email,
                                    @Path("firstName") String firstName,
                                    @Path("lastName") String lastName,
                                    @Path("latitude")Number latitude,
                                    @Path("longitude") Number longitude,
                                    @Path("message") String message);



}

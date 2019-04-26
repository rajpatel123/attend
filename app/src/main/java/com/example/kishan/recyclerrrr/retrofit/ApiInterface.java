package com.example.kishan.recyclerrrr.retrofit;


import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerRequest;
import com.example.kishan.recyclerrrr.modelClass.addagentbyid.GetDealerByIdResponse;
import com.example.kishan.recyclerrrr.modelClass.login.LoginResponse;
import com.example.kishan.recyclerrrr.modelClass.markAttendanceResponse.MarkAttendanceResponse;
import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //login
    @GET("user/authenticate/{companyName}/{userId}/{password}")
    Call<LoginResponse> login(@Path("companyName") String companyName,
                             @Path("userId") String userId,
                             @Path("password") String password);

    //Registration
    @POST("agent/saveDealer")
    Call<AddDealerResponse> addDealer(@Body AddDealerRequest addDealerRequest);


    //addAgent
    @GET("agent/getDealerByAgentId/{agentId}")
    Call<List<GetDealerByIdResponse>> getAgentById(@Path("agentId") Integer agentId);

    //markAttendance
    @GET("agent/punch/{companyId}/{agentId}/{latitude}/{longitude}/{punchValue}")
    Call<MarkAttendanceResponse> markAttendence(@Path("companyId") Number companyId,
                                                @Path("agentId") Number agentId,
                                                @Path("latitude") String latitude,
                                                @Path("longitude") String longitude,
                                                @Path("punchValue") Number userId );




}

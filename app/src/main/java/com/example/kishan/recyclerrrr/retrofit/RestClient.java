package com.example.kishan.recyclerrrr.retrofit;


import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerRequest;
import com.example.kishan.recyclerrrr.modelClass.addagentbyid.GetDealerByIdResponse;
import com.example.kishan.recyclerrrr.modelClass.login.LoginResponse;
import com.example.kishan.recyclerrrr.modelClass.markAttendanceResponse.MarkAttendanceResponse;
import com.example.kishan.recyclerrrr.modelClass.addDealer.AddDealerResponse;

import java.util.List;

import retrofit2.Callback;

public class RestClient {
    private static final String TAG = "RestClient";

    //Login
    public static void loginUser(String companyName, String userId, String password, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().login(companyName, userId, password).enqueue(callback);
    }

    //Registration
    public static void addNewDealer(AddDealerRequest addDealerRequest, Callback <AddDealerResponse> callback) {
        RetrofitClient.getClient().addDealer(addDealerRequest).enqueue(callback);
    }

    //getAgentById
    public static void getAgentById(Integer agentId, Callback<List<GetDealerByIdResponse>> callback) {
        RetrofitClient.getClient().getAgentById(agentId).enqueue(callback);
    }

    //markAttendence
    public static void markDealerAttendence(Number companyId,Number agentId,String latitude,String longitude,Number punchValue, Callback<MarkAttendanceResponse> callback) {
        RetrofitClient.getClient().markAttendence(companyId,agentId,latitude,longitude,punchValue).enqueue(callback);
    }
}


package com.example.kishan.recyclerrrr.retrofit;



import com.example.kishan.recyclerrrr.Models.login.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Headers;
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

//    //Registration
//    @Multipart
//    @POST("jpr_admin/user_api/test_api.php?action=register")
//   //@Headers({"Content-Type: application/json", "Accept: application/json"})
//    Call<RegistrationResponse> register(@Part("u_name") RequestBody name,
//                                        @Part("u_email") RequestBody email,
//                                        @Part("u_mobile") RequestBody mobile,
//                                        @Part("u_country") RequestBody country,
//                                        @Part("u_password") RequestBody password);


//    //Catagory
//
//    @POST("cat_api/test_api.php?action=fetch_all")
//    Call <List<CatagoryResponse>> getCourse();


   // SubCatagory
   // @Multipart
   // @POST("cat_api/test_api.php?action=fetchsub_all")
    //Call<SubcategoryResponse>getCourse();

}

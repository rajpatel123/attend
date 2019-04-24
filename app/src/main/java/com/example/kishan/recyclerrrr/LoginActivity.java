package com.example.kishan.recyclerrrr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kishan.recyclerrrr.Models.login.LoginResponse;
import com.example.kishan.recyclerrrr.retrofit.RestClient;
import com.example.kishan.recyclerrrr.utils.Utils;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email, password, com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_BTN);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        com = findViewById(R.id.pwd_TV1);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String gmaill = email.getText().toString().trim();
                String passworddd = password.getText().toString().trim();
                String comm = com.getText().toString().trim();

                boolean check = validateInputs(gmaill, passworddd);


                if (check) {
                    LoginResponse loginRequestModel = new LoginResponse();
                    loginRequestModel.setEmail(gmaill);
                    loginRequestModel.setPassword(passworddd);
                    loginRequestModel.setCompanyName(comm);
                    Utils.showProgressDialog(this);

                    RestClient.loginUser(loginRequestModel, new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Utils.dismissProgressDialog();
                            if (response.body() != null) {
                                if (response.body().getIsActive().equalsIgnoreCase("yes")) {
                                    Utils.displayToast(getApplicationContext(), "Successfuly Login");
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Utils.dismissProgressDialog();
                            Utils.displayToast(LoginActivity.this, "Unable to login, please try again later");

                        }
                    });
                }
            }


            private boolean validateInputs(String gmaill, String passworddd) {
                boolean check = true;

                if (!Patterns.EMAIL_ADDRESS.matcher(gmaill).matches()) {
                    email.setError("Field is empty");
                    check = false;
                }


                if (passworddd.length() < 4) {
                    password.setError("enter more than 6 charater");
                    check = false;
                }
                return check;
            }

        });
    }
}
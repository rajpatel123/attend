package com.example.kishan.recyclerrrr.activitiy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kishan.recyclerrrr.modelClass.login.LoginResponse;
import com.example.kishan.recyclerrrr.R;
import com.example.kishan.recyclerrrr.retrofit.RestClient;
import com.example.kishan.recyclerrrr.utils.AttandancePrefs;
import com.example.kishan.recyclerrrr.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText email, password, companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_BTN);
        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        companyName = findViewById(R.id.edit_companyName);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailId = "" + email.getText().toString().trim();
                String passwordId = "" + password.getText().toString().trim();
                String companyId = "" + companyName.getText().toString().trim();

                boolean check = validateInputs(emailId, passwordId, companyId);


                if (check) {

                    Utils.showProgressDialog(LoginActivity.this);
                    RestClient.loginUser(companyId, emailId, passwordId, new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                            if (response.body() != null) {
                                if (response.body().getLoginFlag().equals("2")) {
                                    Utils.displayToast(getApplicationContext(), "Password is invalid");

                                }

                                if (response.body().getLoginFlag().equals("3")) {
                                    Utils.displayToast(getApplicationContext(), "user is deactivate");

                                }
                                if (response.body().getLoginFlag().equals("4")) {
                                    Utils.displayToast(getApplicationContext(), "company is deactivate");

                                }

                                if (response.body().getLoginFlag().equals("5")) {
                                    Utils.displayToast(getApplicationContext(), "something went wrong");

                                }
                                if (response.body().getLoginFlag().equals(" ")) {
                                    Utils.displayToast(getApplicationContext(), "something went wrong");

                                }

                                if (response.body().getLoginFlag().equals("1")) {

                                    if (Integer.parseInt(response.body().getUserId()) > 0) {

                                        Intent intent = new Intent(LoginActivity.this, ShowAllDealerActivity.class);
                                        Utils.dismissProgressDialog();
                                        Utils.displayToast(getApplicationContext(), "login succesfull");
                                        AttandancePrefs.putInt(LoginActivity.this, "agentId", Integer.parseInt(response.body().getAgentId()));
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                                    }
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


            private boolean validateInputs(String emailId, String passwordId, String companyId) {
                boolean check = true;

                if (emailId.length() < 4) {
                    email.setError("enter more than 4 charater ");
                    check = false;
                }
                if (passwordId.length() < 4) {
                    password.setError("enter more than 4 charater");
                    check = false;
                }
                if (companyId.length() < 4) {
                    password.setError("enter more than 4 charater");
                    check = false;
                }
                return check;
            }

        });
    }
}
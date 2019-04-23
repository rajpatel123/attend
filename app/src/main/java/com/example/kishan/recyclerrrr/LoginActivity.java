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

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText email,password,com;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login_BTN);
        email=(EditText) findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);
        com=findViewById(R.id.pwd_TV1);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String gmaill = email.getText().toString();
                String passworddd = password.getText().toString();
                String comm = com.getText().toString();

                boolean check = validateInputs(gmaill, passworddd);


                if (check) {
                    if (Utils.isInternetConnected(LoginActivity.this)) {
                        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), gmaill);
                        RequestBody pwd = RequestBody.create(MediaType.parse("text/plain"), passworddd);
                        RequestBody com= RequestBody.create(MediaType.parse("text/plain"), comm);

                        //TODO  display progress dialog

                        Utils.showProgressDialog(LoginActivity.this);

                        RestClient.loginUser(email, pwd,com, new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                                switch (response.code()) {
                                    case 200:
                                        Utils.dismissProgressDialog();

                                        LoginResponse loginResponse = response.body();
                                        if (loginResponse.getStatus()) {
                                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                            intent.putExtra("USERNAME", "gmail");
                                            intent.putExtra("PASSWORD", "password");
                                            intent.putExtra("COMPANY", "COMPANY");

                                            startActivity(intent);
                                            finish();

                                        } else {
                                            Toast.makeText(LoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                                        }
                                        break;
                                    case 500:

                                        break;
                                    default:
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("Fail", call.toString());
                                Utils.dismissProgressDialog();
                            }
                        });


                    } else {
                        Toast.makeText(LoginActivity.this, "no Internet connection", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

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

}

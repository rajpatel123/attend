package com.example.kishan.recyclerrrr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText ids,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login_BTN);
        ids=(EditText) findViewById(R.id.username1);
        pwd=findViewById(R.id.pwd_TV);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check=true;

                String gmaill=ids.getText().toString();
                String passworddd=pwd.getText().toString();




                if(!Patterns.EMAIL_ADDRESS.matcher(gmaill).matches())
                {
                    ids.setError("Field is empty");
                    check=false;
                }


                if(passworddd.length()<10)
                {
                    pwd.setError("enter more than 10 charater");
                    check=false;
                }
                if (check==true)
                {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);

                    intent.putExtra("USERNAME","gmail");
                    intent.putExtra("PASSWORD","password");
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}

package com.example.kishan.recyclerrrr.activitiy;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kishan.recyclerrrr.R;
import com.example.kishan.recyclerrrr.utils.AttandancePrefs;

import java.util.Calendar;
import java.util.Date;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int mm = calendar.get(Calendar.MONTH);

                if (mm<5){

                }else{
                  throw new RuntimeException(new Throwable());
                }
                // This method will be executed once the timer is over
                if (AttandancePrefs.getInt(SplashActivity.this, "agentId", 0) > 0) {
                    Intent i = new Intent(SplashActivity.this, ShowAllDealerActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 5000);

    }
}

package com.agatevure.medicationmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.agatevure.medicationmanager.SignUpActivity;
import com.agatevure.medicationmanager.sqlite.SqliteHelper;


public class SplashActivity extends AppCompatActivity {
    SqliteHelper mySqliteHelper;
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* // Create Sqlite DB
        mySqliteHelper = new SqliteHelper(this);
        // Go to SignUp Activity
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();*/

        new Handler().postDelayed(new Runnable() {
            public void run() {
               SplashActivity.this.startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
                SplashActivity.this.finish();

            }
        }, (long) SPLASH_TIME_OUT);

    }
}

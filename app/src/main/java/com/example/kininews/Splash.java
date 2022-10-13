package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        MEMUNCULKAN SPLASH PAGE SELAMA 1500 ms, LANJUT LOGIN INTENT
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(Splash.this, MainActivity.class);
                startActivity(login);
            }
        },1500);
    }
}
package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

public class Home extends AppCompatActivity {
    Toolbar toolbar;
    ImageButton btnAccount, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
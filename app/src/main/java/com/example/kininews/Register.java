package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    Toolbar toolbar;
    TextView btnLogin;
    Button btnRegister,btnClear;
    EditText editFullName, editUserName, editEmail, editPassword, editRepeatPassword;
    ImageButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar=findViewById(R.id.toolbar);
        btnCancel=findViewById(R.id.btnCancel);
        editUserName=findViewById(R.id.editUserName);
        editFullName=findViewById(R.id.editFullName);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        editRepeatPassword=findViewById(R.id.editRepeatPassword);
        btnRegister=findViewById(R.id.btnRegister);
        btnClear=findViewById(R.id.btnClear);
        btnLogin=findViewById(R.id.btnLogin);

        setSupportActionBar(toolbar);

//        LOGIN REGISTER DAN MASUK KE LOGIN MAIN ACTIVITY
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(Register.this, MainActivity.class);
                startActivity(login);
            }
        });

//        BERALIH KE REGISTER ACTIVITY
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gologin = new Intent(Register.this, MainActivity.class);
                startActivity(gologin);
            }
        });

//        KELUAR ACTIVITY APLIKASI
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUserName.setText("");
                editFullName.setText("");
                editEmail.setText("");
                editPassword.setText("");
                editRepeatPassword.setText("");
            }
        });
    }
}
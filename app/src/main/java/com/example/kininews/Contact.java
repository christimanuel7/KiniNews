package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Contact extends AppCompatActivity {
    private Button btnWA,btnGmail;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btnBack=findViewById(R.id.btnBack);
        btnWA=findViewById(R.id.btnWA);
        btnGmail=findViewById(R.id.btnGmail);

        btnWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waURL="https://wa.me/+6285155105738?text=Halo, Apakah customer service sedang sibuk?";
                Intent whatsapp=new Intent(Intent.ACTION_VIEW);
                whatsapp.setData(Uri.parse(waURL));
                startActivity(whatsapp);
            }
        });

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email=new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:christ.imanuel7@gmail.com"));
                email.putExtra(Intent.EXTRA_EMAIL,new String[]{"abc@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"Subject here");
                email.putExtra(Intent.EXTRA_TEXT,"My Email Message");
                startActivity(email);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Contact.this, Home.class);
                startActivity(home);
            }
        });
    }
}
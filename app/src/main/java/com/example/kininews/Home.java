package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnLogout,btnWA,btnEmail;
    private ImageButton btnAccount, btnContact;
    private FirebaseUser firebaseUser;
    private TextView textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar);
        btnLogout=findViewById(R.id.btnLogout);
        btnWA=findViewById(R.id.btnWA);
        btnEmail=findViewById(R.id.btnEmail);
        btnAccount=findViewById(R.id.btnAccount);
        btnContact=findViewById(R.id.btnContact);
        textName=findViewById(R.id.textName);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        setSupportActionBar(toolbar);

        if(firebaseUser!=null){
            textName.setText(firebaseUser.getDisplayName());
        }else{
            textName.setText("Login Gagal");
        }

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account = new Intent(Home.this, profile.class);
                startActivity(account);
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact = new Intent(Home.this, contact.class);
                startActivity(contact);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(Home.this, MainActivity.class);
                startActivity(logout);
            }
        });

        btnWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waURL="https://wa.me/+6285155105738?text=Halo, Apakah customer service sedang sibuk?";
                Intent whatsapp=new Intent(Intent.ACTION_VIEW);
                whatsapp.setData(Uri.parse(waURL));
                startActivity(whatsapp);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waURL="https://wa.me/+6285155105738?text=Halo, Apakah customer service sedang sibuk?";
                Intent whatsapp=new Intent(Intent.ACTION_VIEW);
                whatsapp.setData(Uri.parse(waURL));
                startActivity(whatsapp);
            }
        });
    }
}
package com.example.kininews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    //MEMBUAT OBJEK DATABASE REFERENCE UNTUK MENGAKSES FIREBASE REALTIME DB
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://uts-kelompok-2-3e365-default-rtdb.firebaseio.com/");

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
                //GET DATA DARI EDITTEXT KE STRING VARIABLES
                String usernameTxt=editUserName.getText().toString();
                String fullNameTxt=editFullName.getText().toString();
                String emailTxt=editEmail.getText().toString();
                String passwordTxt=editPassword.getText().toString();
                String repeatPasswordTxt=editRepeatPassword.getText().toString();

                //MENGECEK PENGISIAN SUDAH DIISI SEMUA SEBELUM MASUK FIREBASE
                if(fullNameTxt.isEmpty() || emailTxt.isEmpty()|| usernameTxt.isEmpty()|| passwordTxt.isEmpty()|| repeatPasswordTxt.isEmpty()){
                    Toast.makeText(Register.this,"Mohon dilengkapi isi semua kolom register",Toast.LENGTH_SHORT).show();
                }

                //MENGECEK PASSWORD SAMA DENGAN REPEAT PASSWORD
                if(!passwordTxt.equals(repeatPasswordTxt)){
                    Toast.makeText(Register.this,"Password tidak sesuai dengan repeat password",Toast.LENGTH_SHORT).show();
                }

                else{
                    databaseReference.child("tbUser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //MENGECEK JIKA USERNAME TIDAK TERDAFTAR SEBELUMNYA
                            if(snapshot.hasChild(usernameTxt)){
                                Toast.makeText(Register.this,"Username sudah didaftarkan",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //MENGIRIM DATA YANG DIISI KE FIREBASE KE DB REALTIME FIREBASE
                                //USERNAME UNIQUE VALUE
                                databaseReference.child("tbUser").child(usernameTxt) .child("fullName").setValue(fullNameTxt);
                                databaseReference.child("tbUser").child(usernameTxt) .child("email").setValue(emailTxt);
                                databaseReference.child("tbUser").child(usernameTxt) .child("password").setValue(passwordTxt);

                                Intent login = new Intent(Register.this, MainActivity.class);
//                                login.putExtra("Username", usernameTxt);
                                startActivity(login);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
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
                editEmail.requestFocus(1);
            }
        });
    }
}
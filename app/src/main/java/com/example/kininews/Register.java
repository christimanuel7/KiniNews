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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {
    //MEMBUAT OBJEK DATABASE REFERENCE UNTUK MENGAKSES FIREBASE REALTIME DB

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private TextView btnLogin;
    private Button btnRegister,btnClear;
    private EditText  editEmail, editFullName, editPassword, editRepeatPassword;
    private ImageButton btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar=findViewById(R.id.toolbar);
        btnCancel=findViewById(R.id.btnCancel);
        editEmail=findViewById(R.id.editEmail);
        editFullName=findViewById(R.id.editFullName);
        editPassword=findViewById(R.id.editPassword);
        editRepeatPassword=findViewById(R.id.editRepeatPassword);
        btnRegister=findViewById(R.id.btnRegister);
        btnClear=findViewById(R.id.btnClear);
        btnLogin=findViewById(R.id.btnLogin);

        setSupportActionBar(toolbar);

        mAuth=FirebaseAuth.getInstance();

//        LOGIN REGISTER DAN MASUK KE LOGIN MAIN ACTIVITY
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET DATA DARI EDITTEXT KE STRING VARIABLES
                String fullNameTxt=editFullName.getText().toString();
                String emailTxt=editEmail.getText().toString();
                String passwordTxt=editPassword.getText().toString();
                String repeatPasswordTxt=editRepeatPassword.getText().toString();

                //MENGECEK PENGISIAN SUDAH DIISI SEMUA SEBELUM MASUK FIREBASE
                if(fullNameTxt.isEmpty() || emailTxt.isEmpty()||  passwordTxt.isEmpty()|| repeatPasswordTxt.isEmpty()){
                    Toast.makeText(Register.this,"Mohon dilengkapi isi semua kolom register",Toast.LENGTH_SHORT).show();
                }

                //MENGECEK PASSWORD SAMA DENGAN REPEAT PASSWORD
                if(!passwordTxt.equals(repeatPasswordTxt)){
                    Toast.makeText(Register.this,"Password tidak sesuai dengan ulang password",Toast.LENGTH_SHORT).show();
                }

                else{
                    register(emailTxt,fullNameTxt,passwordTxt);
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
                editEmail.setText("");
                editPassword.setText("");
                editRepeatPassword.setText("");
                editEmail.requestFocus(1);
            }
        });
    }

    private void register(String email, String fullName,  String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult()!=null){
                    FirebaseUser firebaseUser=task.getResult().getUser();
                    if(firebaseUser!=null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(fullName)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Registrasi Gagal",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload() {
        Intent regist = new Intent(Register.this, MainActivity.class);
        startActivity(regist);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}
package com.example.kininews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //MEMBUAT OBJEK DATABASE REFERENCE UNTUK MENGAKSES FIREBASE REALTIME DB
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://uts-kelompok-2-3e365-default-rtdb.firebaseio.com/");

    Toolbar toolbar;
    TextView btnRegister;
    Button btnLogin,btnClear;
    EditText editEmail, editPassword;
    ImageButton btnCancel;
    CheckBox showPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);
        btnCancel=findViewById(R.id.btnCancel);
        editEmail=findViewById(R.id.editEmail);
        editPassword=findViewById(R.id.editPassword);
        showPassword=findViewById(R.id.showPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnClear=findViewById(R.id.btnClear);
        btnRegister=findViewById(R.id.btnRegister);

        setSupportActionBar(toolbar);

        ImageSlider carousel=findViewById(R.id.carousel);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cepat,"1", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.akurat,"2", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.kredibel,"3", ScaleTypes.FIT));
        carousel.setImageList(slideModels);

//        LOGIN AKUN DAN MASUK KE HOME ACTIVITY
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt=editEmail.getText().toString();
                String passwordTxt=editPassword.getText().toString();

                if(emailTxt.isEmpty()||passwordTxt.isEmpty()){
                    Toast.makeText(MainActivity.this,"Mohon masukkan kembali username dan password anda",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("tbUser").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //MENGECEK USERNAME ADA DIDATABASE ATAU TIDAK
                            if(snapshot.hasChild(emailTxt)){
                                //MENGECEK KECOCOKAN USERNAME DAN PASSWORD DI DB
                                String getUsername=snapshot.child(emailTxt).child("username").getValue(String.class);
                                String getPassword=snapshot.child(emailTxt).child("password").getValue(String.class);

                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(MainActivity.this,"Login Berhasil",Toast.LENGTH_SHORT).show();

                                    //MEMBUKA ACTIVITY HOME
                                    Intent home = new Intent(MainActivity.this, Home.class);
                                    String name = getUsername;
                                    home.putExtra("fullName", name);
                                    startActivity(home);
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Mohon masukkan username dan password yang benar",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Mohon masukkan username dan password yang benar",Toast.LENGTH_SHORT).show();
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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });

//        KELUAR ACTIVITY APLIKASI
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showPassword.isChecked()){
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //Jika tidak, maka password akan di sembuyikan
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEmail.setText("");
                editPassword.setText("");
            }
        });
    }
}
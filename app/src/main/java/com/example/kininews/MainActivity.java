package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
                Intent home = new Intent(MainActivity.this, Home.class);
                startActivity(home);
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
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private TextView btnRegister;
    private Button btnLogin,btnClear;
    private EditText editEmail, editPassword;
    private ImageButton btnCancel;
    private CheckBox showPassword;

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


        mAuth=FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);

        ImageSlider carousel=findViewById(R.id.carousel);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.cepat,"Menyajikan berita terkini secara cepat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.akurat,"Menyajikan informasi berita yang akurat", ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.kredibel,"Menyajikan informasi dari sumber yang kredibel", ScaleTypes.FIT));
        carousel.setImageList(slideModels);

//        LOGIN AKUN DAN MASUK KE HOME ACTIVITY
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt=editEmail.getText().toString();
                String passwordTxt=editPassword.getText().toString();

                if(emailTxt.isEmpty()||passwordTxt.isEmpty()){
                    Toast.makeText(MainActivity.this,"Email dan Password yang anda masukkan kosong",Toast.LENGTH_SHORT).show();
                }
                else{
                    login(emailTxt,passwordTxt);
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
                editEmail.requestFocus(1);
            }
        });
    }

    private void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult()!=null){
                    reload();
                }else{
                    Toast.makeText(getApplicationContext(),"Mohon periksa kembali email dan password anda",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload() {
        Intent login = new Intent(MainActivity.this, Home.class);
        startActivity(login);
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
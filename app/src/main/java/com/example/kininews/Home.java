package com.example.kininews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageButton btnAccount, btnContact;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar=findViewById(R.id.toolbar);
        btnAccount=findViewById(R.id.btnAccount);
        btnContact=findViewById(R.id.btnContact);

        setSupportActionBar(toolbar);

        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account = new Intent(Home.this, Profile.class);
                startActivity(account);
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact = new Intent(Home.this, Contact.class);
                startActivity(contact);
            }
        });

        //RECYCLE VIEW
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        //set layout as linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //send quary to firebase db
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("tbBerita");
    }

    //load data ke recycle view

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter=
//         new FirebaseRecyclerAdapter<Model, ViewHolder>(
//                 Model.class,
//                 R.layout.row,
//                 ViewHolder.class,
//                 databaseReference
//         ) {
//             @Override
//             protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
//                ViewHolder.setDetails(getApplicationContext(),model.getJudul(),model.getGambar(),model.getHeadline());
//             }
//
//             @NonNull
//             @Override
//             public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                 return null;
//             }
//         };
//    }
}
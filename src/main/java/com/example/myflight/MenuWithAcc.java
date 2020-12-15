package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuWithAcc extends AppCompatActivity {
    private TextView tvName;
    private ImageView ivAcc,ivFlight,ivBooking;
    private Button btnLogout;
    private ProgressBar progressBar;
    String uid;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    DatabaseReference reff;
    User User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_with_acc);

        tvName = findViewById(R.id.tvName);
        ivAcc = findViewById(R.id.ivAcc);
        btnLogout = findViewById(R.id.btnLogout);
        progressBar = findViewById(R.id.progressbar);
        ivFlight = findViewById(R.id.ivFlight);
        ivBooking = findViewById(R.id.ivBooking);

        //final String email = getIntent().getStringExtra("Email").trim();

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uid = mUser.getUid();


        //User=new User();
        reff = FirebaseDatabase.getInstance().getReference("User");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child(uid).child("email").getValue(String.class);
                String name = dataSnapshot.child(uid).child("name").getValue(String.class);

                tvName.setText(""+name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ivFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atvtFlight = new Intent(MenuWithAcc.this,SetBooking.class);
                atvtFlight.putExtra("uid", uid);
                startActivity(atvtFlight);
            }
        });

        ivBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent atvtBooking = new Intent(MenuWithAcc.this,BOOKING.class);
                atvtBooking.putExtra("uid", uid);
                startActivity(atvtBooking);*/
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MenuWithAcc.this,MainActivity.class));
            }
        });
    }
}

package com.example.myflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivFlight, ivBooking;
    private TextView tvLogin;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivFlight = findViewById(R.id.ivFlight);
        ivBooking = findViewById(R.id.ivBooking);
        btnLogin = findViewById(R.id.btnLogin);
        tvLogin = findViewById(R.id.tvLogin);

        ivFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentLogin = new Intent(MainActivity.this,Login.class);
                //startActivity(intentLogin);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intentLogin = new Intent(MainActivity.this,Login.class);
                //startActivity(intentLogin);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentLogin = new Intent(MainActivity.this,Login.class);
                //startActivity(intentLogin);
            }
        });

    }
}

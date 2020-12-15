package com.example.myflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentSuccess extends AppCompatActivity {
    Button toMainMenu;
    Intent intent = getIntent();
    String uid = intent.getStringExtra("uid");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        toMainMenu = findViewById(R.id.button);

        toMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainIntent = new Intent(PaymentSuccess.this, MainActivity.class);
                toMainIntent.putExtra("user_id", uid);
                startActivity(toMainIntent);
            }
        });
    }
}

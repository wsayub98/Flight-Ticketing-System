package com.example.myflight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fingerprint extends AppCompatActivity {
    private ImageButton btn_fingerprint;
    //String price, booking_id, card_number;
    DatabaseReference paymentdetailDB;
    PaymentDetail paymentdetail;
    Intent intent = getIntent();
    String booking_id = intent.getStringExtra("booking_id");
    String price = intent.getStringExtra("price");
    String uid = intent.getStringExtra("uid");
    String card_number = intent.getStringExtra("card_number");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);

        btn_fingerprint = findViewById(R.id.image_fingerprint);

        btn_fingerprint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                paymentdetail = new PaymentDetail();
                paymentdetailDB = FirebaseDatabase.getInstance().getReference().child("Payment Detail");
                addPaymentDetail();
                Toast.makeText(Fingerprint.this, "Verified!", Toast.LENGTH_LONG).show();
                Intent IntentFingerprint = new Intent(Fingerprint.this, PaymentSuccess.class);
                IntentFingerprint.putExtra("user_id", uid);
                startActivity(IntentFingerprint);
                return false;
            }
        });
    }

    private void addPaymentDetail() {

        Intent intent = getIntent();
        String booking_id = intent.getStringExtra("booking_id");
        String price = intent.getStringExtra("price");
        String card_number = intent.getStringExtra("card_number");
        Toast.makeText(Fingerprint.this, card_number, Toast.LENGTH_LONG).show();

        String id = paymentdetailDB.push().getKey();
        PaymentDetail paymentdetail = new PaymentDetail(id, booking_id, "1", card_number, "1", price);
        paymentdetailDB.child(id).setValue(paymentdetail);
    }
}

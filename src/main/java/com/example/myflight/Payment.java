package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    private Button sendtoDebit, voucher;
    private EditText etVoucher;
    private TextView tvprice, tvdiscount, tvtotal;
    DatabaseReference bookingDB;
    Intent intent = getIntent();
    String booking_id = intent.getStringExtra("booking_id");
    String uid = intent.getStringExtra("uid");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sendtoDebit = findViewById(R.id.sendToDebit);
        voucher = findViewById(R.id.voucherbtn);
        etVoucher = findViewById(R.id.etvoucher);
        tvprice = findViewById(R.id.tv_price);
        tvdiscount = findViewById(R.id.tv_discount);
        tvtotal = findViewById(R.id.tv_total);

        DatabaseReference r1 = FirebaseDatabase.getInstance().getReference("Booking").child(booking_id);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                float price = dataSnapshot.child("totalprice").getValue(Float.class);
                tvprice.setText(String.format("%.2f",price));
                float discount = 0;
                tvdiscount.setText(String.format("%.2f",discount));
                float total = price * ((100-discount) / 100);
                tvtotal.setText(String.format("%.2f",total));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        r1.addValueEventListener(postListener);

        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etVoucher.getText().toString().matches("")) {
                    String vouchercode = etVoucher.getText().toString();
                    if (vouchercode.equals("F10")) {
                        final String test = tvprice.getText().toString();
                        float price = Float.valueOf(test);
                        float discount = (float) (price*0.1);
                        tvdiscount.setText(String.format("%.2f",discount));
                        float total = (price - discount);
                        tvtotal.setText(String.format("%.2f",total));
                        Toast.makeText(Payment.this, "Success!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Payment.this, "Wrong code", Toast.LENGTH_LONG).show();
                        etVoucher.setText("");
                    }
                }
                else {
                    Toast.makeText(Payment.this, "Please insert the voucher code!", Toast.LENGTH_LONG).show();
                }
            }
        });

        sendtoDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passTotal = tvtotal.getText().toString();
                Intent IntentDebit = new Intent(Payment.this, SelectDebitCard.class);
                IntentDebit.putExtra("user_id", uid);
                IntentDebit.putExtra("price", passTotal);
                IntentDebit.putExtra("booking_id", booking_id);
                startActivity(IntentDebit);
            }
        });
    }
}

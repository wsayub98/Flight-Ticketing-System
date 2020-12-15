package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookingDetail extends AppCompatActivity {

    private TextView date_depart, date_return, destination_depart, destination_return;
    private TextView depart_info, return_info;
    private TextView price_depart, price_return, totalprice;

    private Button SendToPayment;
    private TextView tv_totalprice;
    private DatabaseReference bookingDB;
    private Booking booking;
    ArrayList<Booking> bookingList;
    Intent intent = getIntent();
    String booking_id = intent.getStringExtra("booking_id");
    //String uid = intent.getStringExtra("uid");
    String uid = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        bookingDB = FirebaseDatabase.getInstance().getReference("Booking");
        booking = new Booking();
        bookingList = new ArrayList<Booking>();

        SendToPayment = findViewById(R.id.BookDtlBtn);
        date_depart = findViewById(R.id.tv_date_depart);
        date_return = findViewById(R.id.tv_date_return);
        destination_depart = findViewById(R.id.tv_destination_depart);
        destination_return = findViewById(R.id.tv_destination_return);
        depart_info = findViewById(R.id.tv_info_depart);
        return_info = findViewById(R.id.tv_info_return);
        price_depart = findViewById(R.id.tv_price_depart);
        price_return = findViewById(R.id.tv_price_return);
        totalprice = findViewById(R.id.tv_totalprice);

        DatabaseReference r1 = FirebaseDatabase.getInstance().getReference("Booking").child(booking_id);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Ddepart_date = dataSnapshot.child("date_depart").getValue(String.class);
                String Dreturn_date = dataSnapshot.child("date_return").getValue(String.class);
                String Ddepart_destination = dataSnapshot.child("destination_depart").getValue(String.class);
                String Dreturn_destination = dataSnapshot.child("destination_return").getValue(String.class);
                String Ddepart_time = dataSnapshot.child("time_depart").getValue(String.class);
                String Dreturn_time = dataSnapshot.child("time_return").getValue(String.class);
                String Ddepart_flight = dataSnapshot.child("flight_depart").getValue(String.class);
                String Dreturn_flight = dataSnapshot.child("flight_return").getValue(String.class);
                Float Ddepart_price = dataSnapshot.child("price_depart").getValue(Float.class);
                Float Dreturn_price = dataSnapshot.child("price_return").getValue(Float.class);
                Float Dtotalprice = dataSnapshot.child("totalprice").getValue(Float.class);

                date_depart.setText(Ddepart_date);
                date_return.setText(Dreturn_date);
                destination_depart.setText(Ddepart_destination);
                destination_return.setText(Dreturn_destination);
                price_depart.setText(String.format("RM%.2f",Ddepart_price));
                price_return.setText(String.format("RM%.2f",Dreturn_price));
                depart_info.setText(Ddepart_time + " || " + Ddepart_flight);
                return_info.setText(Dreturn_time  + " || " + Dreturn_flight);
                totalprice.setText(String.format("RM%.2f",Dtotalprice));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        r1.addValueEventListener(postListener);

        //addBoooking();
        SendToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent IntentPayment = new Intent(BookingDetail.this, Payment.class);
                IntentPayment.putExtra("user_id", uid);
                IntentPayment.putExtra("booking_id", booking_id);
                startActivity(IntentPayment);
            }
        });
    }
}

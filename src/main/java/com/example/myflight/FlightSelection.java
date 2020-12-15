package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FlightSelection extends AppCompatActivity {
    ListView lv;
    Button next;
    TextView date, returnD;
    Flight flight;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    private DatabaseReference flightDB;
    private DatabaseReference bookingDB;
    private Booking booking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_selection);
        //a List of type hero for holding list items

        flight = new Flight();
        flightDB = FirebaseDatabase.getInstance().getReference("FlightInfo");
        booking = new Booking();
        bookingDB = FirebaseDatabase.getInstance().getReference("booking");
        date = findViewById(R.id.displayDate);
        returnD = findViewById(R.id.displayDate2);
        lv = (ListView) findViewById(R.id.listView);
        next = findViewById(R.id.nextButton);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.flightinfo,R.id.flightinfo,arrayList);

        bookingDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    booking = ds.getValue(Booking.class);
                    date.setText("\tDeparture Date:"+ booking.getDate_depart());
                    returnD.setText("\tReturn Date:"+ booking.getDate_return());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        flightDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    flight = ds.getValue(Flight.class);
                    arrayList.add("\tDeparture\n" + "Flight: " + flight.getFlight_depart()+ "\n" + "Time: " +flight.getTime_depart()+ "\nPrice:RM "+flight.getPrice_depart()+ "\n" + "\n\tReturn\n" + "Flight: " + flight.getFlight_return() + "\nPrice: RM" +  flight.getPrice_return()+"\nTime: " +  flight.getTime_return()
                            + "\n\nTotal Ticket Price: RM " + flight.getTotalprice());
                }
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGuest=new Intent(FlightSelection.this, GuestDetails.class);
                startActivity(intentGuest);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ){
                    Toast.makeText(FlightSelection.this, "Flight selected ", Toast.LENGTH_SHORT).show();
                    Intent intentGuest=new Intent(FlightSelection.this, GuestDetails.class);
                    startActivity(intentGuest);
                }
                else
                    Toast.makeText(FlightSelection.this, "Flight selected ", Toast.LENGTH_SHORT).show();
                Intent intentGuest=new Intent(FlightSelection.this, GuestDetails.class);
                startActivity(intentGuest);
            }
        });




    }
}

package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;
import java.util.Locale;

public class SetBooking extends AppCompatActivity {

    EditText departure, destination, adultQ, kidQ;
    Button next, location;
    private DatabaseReference bookingDB;
    private Booking booking;
    long maxid;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_booking);

        key = "booking1";
        bookingDB = FirebaseDatabase.getInstance().getReference("Booking").child(key);
        booking = new Booking();

        departure = findViewById(R.id.departLocation);
        destination = findViewById(R.id.destinyLocation);
        adultQ = findViewById(R.id.adultQuantity);
        kidQ = findViewById(R.id.kidQuantity);
        next = findViewById(R.id.nextButton);
        location = findViewById(R.id.locationBtn);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                } else {
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String city = herelocation(location.getLatitude(), location.getLongitude());
                        departure.setText(city);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SetBooking.this, "Not found!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        /*submit= (Button) findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String id = bookingDB.push().getKey();

            }
        });*/
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking.setBooking_id(key);
                booking.setDestination_depart(departure.getText().toString());
                booking.setDestination_return(destination.getText().toString());
                booking.setAdultQuantity(Integer.parseInt(adultQ.getText().toString()));
                booking.setKidQuantity(Integer.parseInt(kidQ.getText().toString()));
                booking.setDate_return("");
                booking.setDate_depart("");
                // bookingDB.child("maxid").setValue(booking);
                bookingDB.push().child(key).setValue(booking);
                Toast.makeText(SetBooking.this, "Data succesfully inserted", Toast.LENGTH_LONG).show();
                Intent intentDate = new Intent(SetBooking.this, DateSelection.class);
                intentDate.putExtra("bookingid", key);
                startActivity(intentDate);
            }

        });
    }

    public void onRequestPermissionResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResullts) {
        //super.onRequestPermissionsResult(requestCode,permission,grantResullts);
        switch (requestCode) {
            case 1000: {
                if (grantResullts[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String city = herelocation(location.getLatitude(), location.getLongitude());
                        departure.setText(city);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(SetBooking.this, "Not found!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    private String herelocation(double lat, double lon){
        String cityName = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try{
            addresses = geocoder.getFromLocation(lat, lon, 10);
            if (addresses.size() > 0) {
                for (Address adr:addresses){
                    if(adr.getLocality() != null && adr.getLocality().length() > 0){
                        cityName = adr.getLocality();
                        break;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return cityName;
    }
}

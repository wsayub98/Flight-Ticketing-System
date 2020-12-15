package com.example.myflight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DateSelection extends AppCompatActivity {

    EditText departDate, returnDate;
    DatePickerDialog datePickerDialog;
    Button next;
    String key;
    RadioButton oneway, returntrip;


    private DatabaseReference bookingDB;
    private Booking booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);
        departDate = (EditText) findViewById(R.id.dateDepart);
        returnDate = findViewById(R.id.dateReturn);
        oneway = findViewById(R.id.tripType1);
        returntrip = findViewById(R.id.tripType2);
        next = findViewById(R.id.nextButton);
        Intent intent = getIntent();
        key = intent.getStringExtra("bookingid");
        bookingDB = FirebaseDatabase.getInstance().getReference("booking");
        booking = new Booking();
        Toast.makeText(DateSelection.this,key, Toast.LENGTH_LONG).show();
        // perform click event on edit text

        /*if (returntrip.isChecked())
        {
            returnDate.setEnabled(false);
        }
        else if (returntrip.isChecked())
        {
            //departDate.setEnabled(true);
        }*/

        departDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(DateSelection.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                departDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(DateSelection.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                returnDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                booking.setBooking_id(key);
                booking.setDate_depart(departDate.getText().toString());
                if (oneway.isChecked())
                {
                    booking.setType(oneway.getText().toString());
                }
                else if (returntrip.isChecked())
                {
                    booking.setType(returntrip.getText().toString());
                }
                booking.setDate_return(returnDate.getText().toString());
                bookingDB.child(key).setValue(booking);
                Toast.makeText(DateSelection.this, "Data succesfully inserted", Toast.LENGTH_LONG).show();
                Intent intentFlight=new Intent(DateSelection.this, FlightSelection.class);
                startActivity(intentFlight);
            }
        });
    }
}

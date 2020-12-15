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

public class GuestDetails extends AppCompatActivity {

    Button next, submit;
    EditText name, ic, dob, contact;
    RadioButton male, female;
    RadioGroup gender;
    private DatabaseReference userDB;
    private User2 user;
    String key;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_details);
        key = "user1";
        userDB = FirebaseDatabase.getInstance().getReference("User").child(key);
        user = new User2();

        name = findViewById(R.id.editName);
        ic = findViewById(R.id.editIC);
        dob = findViewById(R.id.editDob);
        male =  findViewById(R.id.gender1);
        female = findViewById(R.id.gender2);
        contact = findViewById(R.id.editContact);
        next = findViewById(R.id.nextButton);
        //submit = findViewById(R.id.submitBtn);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(GuestDetails.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dob.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setId(key);
                user.setName(name.getText().toString());
                user.setIcnum(ic.getText().toString());
                user.setDob(dob.getText().toString());
                if (male.isChecked())
                {
                    user.setGender(male.getText().toString());
                }
                else if (female.isChecked())
                {
                    user.setGender(female.getText().toString());
                }
                user.setContactNum(contact.getText().toString());

                userDB.child(key).setValue(user);
                Toast.makeText(GuestDetails.this, "Data succesfully inserted", Toast.LENGTH_LONG).show();
                Intent intentDate=new Intent(GuestDetails.this, BookingDetail.class);
                startActivity(intentDate);
            }
        });



    }
}

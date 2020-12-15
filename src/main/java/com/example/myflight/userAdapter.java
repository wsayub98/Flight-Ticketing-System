package com.example.myflight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class userAdapter extends ArrayAdapter<String> {
    private DatabaseReference bookingDB;
    private Booking booking;
    Context context;
    String name[], ic[], dob[],gender[],contact[];

    userAdapter(@NonNull Context c, String name[], String ic[], String dob[], String gender[], String contact[]) {
        super(c, R.layout.userinfo, R.id.listView, name);
        this.context = c;
        this.name = name;
        this.ic = ic;
        this.dob = dob;
        this.gender = gender;
        this.contact = contact;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View userinfo = layoutInflater.inflate(R.layout.userinfo, null, false);
        TextView nameT = userinfo.findViewById(R.id.name);
        TextView icT = userinfo.findViewById(R.id.ic);
        TextView dobT = userinfo.findViewById(R.id.dob);
        TextView genderT = userinfo.findViewById(R.id.genderT);
        TextView contactT = userinfo.findViewById(R.id.contact);
        EditText editName = userinfo.findViewById(R.id.editName);
        EditText editIC = userinfo.findViewById(R.id.editIC);
        EditText editDOB = userinfo.findViewById(R.id.editDOB);
        RadioGroup genderR = userinfo.findViewById(R.id.gender);
        RadioButton male = userinfo.findViewById(R.id.genderButton1);
        RadioButton female = userinfo.findViewById(R.id.genderButton1);
        EditText editContact = userinfo.findViewById(R.id.editContact);

        nameT.setText(name[position]);
        icT.setText(ic[position]);
        dobT.setText(dob[position]);
        genderT.setText(gender[position]);
        contactT.setText(contact[position]);

        return  userinfo;



    }


}

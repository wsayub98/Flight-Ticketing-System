package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelectDebitCard extends AppCompatActivity {

    ListView listview;
    Button new_card;
    DatabaseReference database_debitcard;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;
    DebitCard debitcard;
    Intent intent = getIntent();
    String booking_id = intent.getStringExtra("booking_id");
    String price = intent.getStringExtra("price");
    String uid = intent.getStringExtra("uid");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_debit_card);

        database_debitcard = FirebaseDatabase.getInstance().getReference("Debit Card");

        debitcard = new DebitCard();
        listview = findViewById(R.id.listviewDebitCard);
        new_card = findViewById(R.id.newCardbtn);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.layout_debitcard_list, R.id.card_info,arrayList);

        database_debitcard.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    debitcard = ds.getValue(DebitCard.class);
                    arrayList.add(/*debitcard.getCard_company()+ " -"+ */ debitcard.getCard_number() /* + " ( "+debitcard.getCard_month()+ "/"+debitcard.getCard_year()+" )"*/);
                }
                listview.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference r1 = FirebaseDatabase.getInstance().getReference("Debit Card");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference cardRef = rootRef.child("DebitCard");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String key = ds.getKey();
                    Log.d("TAG", key);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        cardRef.addListenerForSingleValueEvent(eventListener);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Debit Card");
                String card_number=(String)adapterView.getItemAtPosition(i);
                //Toast.makeText(SelectDebitCard.this, card_number, Toast.LENGTH_LONG).show();
                
                Intent goToFingerprint = new Intent(SelectDebitCard.this, Fingerprint.class);
                goToFingerprint.putExtra("user_id", uid);
                goToFingerprint.putExtra("card_number", card_number);
                goToFingerprint.putExtra("price", price);
                goToFingerprint.putExtra("booking_id", booking_id);
                startActivity(goToFingerprint);
            }
        });

        new_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newCardIntent = new Intent(SelectDebitCard.this, NewDebitCard.class);
                newCardIntent.putExtra("user_id", uid);
                startActivity(newCardIntent);
            }
        });
    }

}

package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewDebitCard extends AppCompatActivity {

    private EditText card_number, card_month, card_year, card_ccv;
    private Button payment;
    private DatabaseReference debitDB;
    private DebitCard debitcard;
    RadioButton radioVisa, radioMasterCard;
    RadioGroup radioGroup;
    Intent intent = getIntent();
    String uid = intent.getStringExtra("uid");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_debit_card);

        card_number = findViewById(R.id.etCardNumber);
        card_month = findViewById(R.id.etCardMonth);
        card_year = findViewById(R.id.etCardYear);
        card_ccv = findViewById(R.id.etCardCCV);
        payment = findViewById(R.id.btnPayment);
        radioVisa = findViewById(R.id.radioVisa);
        radioMasterCard = findViewById(R.id.radioMasterCard);
        radioGroup = findViewById(R.id.radioGroup);

        debitcard=new DebitCard();
        debitDB = FirebaseDatabase.getInstance().getReference().child("Debit Card");

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addDebitCard();
                Intent selectdebitIntent = new Intent(NewDebitCard.this, SelectDebitCard.class);
                startActivity(selectdebitIntent);
            }
        });
    }

    private void addDebitCard() {
        String debitcard_number = card_number.getText().toString().trim();
        String debitcard_month = card_month.getText().toString().trim();
        String debitcard_year = card_year.getText().toString().trim();
        String debitcard_ccv = card_ccv.getText().toString().trim();
        String debitcard_company = null;

        if (radioVisa.isChecked()) {
            debitcard_company = "Visa";
        } else if (radioMasterCard.isChecked()) {
            debitcard_company = "MasterCard";
        }

        int length_number = card_number.getText().length();
        int length_month = Integer.parseInt(card_month.getText().toString());
        int length_year = Integer.parseInt(card_year.getText().toString());
        int length_ccv = card_ccv.getText().length();

        if (! (TextUtils.isEmpty(debitcard_number) || TextUtils.isEmpty(debitcard_month) ||
                TextUtils.isEmpty(debitcard_year) || TextUtils.isEmpty(debitcard_ccv))) {

            if (length_number == 12 && length_month>=1 && length_month<=12 && length_year>=20
                    && length_year<=30 && length_ccv==3) {

                String id = debitDB.push().getKey();

                DebitCard debitcard = new DebitCard(id, debitcard_number, debitcard_month, debitcard_year, debitcard_ccv, debitcard_company, uid);

                debitDB.child(debitcard_number).setValue(debitcard);
                Toast.makeText(NewDebitCard.this, "Card successfully added!", Toast.LENGTH_LONG).show();
                card_number.setText("");
                card_month.setText("");
                card_year.setText("");
                card_ccv.setText("");
            }
            else {
                Toast.makeText(NewDebitCard.this, "Wrong info!", Toast.LENGTH_LONG).show();
                card_number.setText("");
                card_month.setText("");
                card_year.setText("");
                card_ccv.setText("");
            }
        }
        else {
            Toast.makeText(NewDebitCard.this, "Please insert debit card info", Toast.LENGTH_LONG).show();
        }
    }
}

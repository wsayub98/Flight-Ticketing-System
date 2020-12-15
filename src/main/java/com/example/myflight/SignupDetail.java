package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;

public class SignupDetail extends AppCompatActivity {
    private EditText etName, etAge;
    private RadioButton rbtnMale, rbtnFemale;
    private RadioGroup rgGender;
    private Button btnSignup;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;
    //DatabaseReference reff;
    User User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_detail);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSignup = findViewById(R.id.btnSignup);
        rbtnMale = findViewById(R.id.rbtnMale);
        rbtnFemale = findViewById(R.id.rbtnFemale);
        progressBar = findViewById(R.id.progressbar);



        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = getIntent().getStringExtra("Email").trim();
                final String password = getIntent().getStringExtra("Password").trim();

                final String name = etName.getText().toString().trim();
                String ageText = etAge.getText().toString();
                final int age = Integer.parseInt(ageText);
                //int age = Integer.parseInt(etAge.getText().toString().trim());

                String gender = null;
                if(rbtnMale.isChecked()){
                    gender = "Male".trim();
                }
                else if(rbtnFemale.isChecked()){
                    gender = "Female".trim();
                }



                final String finalGender = gender;
                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                        else{
                            User User = new User(email,password,name, finalGender,age);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"Account Registration Completed.", Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(getApplicationContext(),"ERROR.", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });


                        }
                    }
                });

                Intent intentLogin = new Intent(SignupDetail.this,MainActivity.class);
                startActivity(intentLogin);

            }


        });
    }
}

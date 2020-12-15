package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import android.os.Bundle;

public class SignUp extends AppCompatActivity {
    private EditText etEmail, etPass;
    private Button btnCon;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btnCon = findViewById(R.id.btnCon);
        progressBar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                if(email.isEmpty()){
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                }else if(password.isEmpty()){
                    etPass.setError("Password is required!");
                    etPass.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(), "This email already has been registered.", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }else{
                                Intent intentCon = new Intent(SignUp.this,SignupDetail.class);
                                intentCon.putExtra("Email", email);
                                intentCon.putExtra("Password", password);
                                startActivity(intentCon);
                            }
                        }
                    });

                }


            }
        });
    }
}

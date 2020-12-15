package com.example.myflight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText etEmail, etPass;
    private TextView tvSignup;
    private Button btnLogin;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        tvSignup = findViewById(R.id.tvSignup);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressbar);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSU = new Intent(Login.this,SignUp.class);
                startActivity(intentSU);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String email = etEmail.getText().toString();
                String password = etPass.getText().toString();


                if(email.isEmpty()){
                    etEmail.setError("Email is required!");
                    etEmail.requestFocus();
                }else if(password.isEmpty()){
                    etPass.setError("Password is required!");
                    etPass.requestFocus();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent aftLogin = new Intent(Login.this,MenuWithAcc.class);
                                //aftLogin.putExtra("Email", email);
                                //aftLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(aftLogin);
                            }else{
                                //Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Wrong Email and Password.", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });



                }

            }
        });
    }
}

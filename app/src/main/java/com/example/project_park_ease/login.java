package com.example.project_park_ease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class login extends AppCompatActivity {
    EditText editTextEmail,editTextPassword;
    Button btnlogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editEmail);
        editTextPassword=findViewById(R.id.editPassword);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        btnlogin = findViewById(R.id.btnlogin);
        register =findViewById(R.id.Register);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= editTextEmail.getText().toString().trim();
                String password=editTextPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    editTextEmail.setError("Email is required");
                    Toast.makeText(login.this,"Enter Email.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    editTextPassword.setError("Password is required");
                    Toast.makeText(login.this,"Enter Password.",Toast.LENGTH_SHORT).show();

                    return;
                }
                if(password.length()<6){
                    editTextPassword.setError("Password must be >= 6 Characters");
                    Toast.makeText(login.this,"Enter Password.",Toast.LENGTH_SHORT).show();

                }

                progressBar.setVisibility(view.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //if account is create succesfully

                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),dashboard.class));
                        }else {
                            Toast.makeText(login.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.GONE);

                        }

                    }
                });


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), register.class));
            }
        });

    }
}
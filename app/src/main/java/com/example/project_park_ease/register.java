package com.example.project_park_ease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText rfullname,remail,rpassword,rphone;
    Button registerbtn;
    TextView loginbtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    FirebaseFirestore fstore;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rfullname = findViewById(R.id.editFullName);
        remail = findViewById(R.id.editEmail);
        rpassword = findViewById(R.id.editPassword);
        rphone = findViewById(R.id.editPhoneNumber);
        registerbtn = findViewById(R.id.btnRegister);
        loginbtn = findViewById(R.id.Loginnow);
        progressBar = findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        //if already login in

//        if(fAuth.getCurrentUser() !=null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= remail.getText().toString().trim();
                String password=rpassword.getText().toString().trim();
                String fullname=rfullname.getText().toString();
                String phone=rphone.getText().toString();

                if(TextUtils.isEmpty(fullname)){
                    remail.setError("Name is required");
                    Toast.makeText(register.this,"Enter name.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    remail.setError("Email is required");
                    Toast.makeText(register.this,"Enter Email.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    remail.setError("Phone is required");
                    Toast.makeText(register.this,"Enter Phone.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    remail.setError("Password is required");
                    Toast.makeText(register.this,"Enter Password.",Toast.LENGTH_SHORT).show();

                    return;
                }
                if(password.length()<6){
                    rpassword.setError("Password must be >= 6 Characters");
                    Toast.makeText(register.this,"Enter Password strong password.",Toast.LENGTH_SHORT).show();
                    return;
                }


                progressBar.setVisibility(view.VISIBLE);

                //register by firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //if account is create succesfully

                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "User Created", Toast.LENGTH_SHORT).show();
                            UserID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference= fstore.collection("users").document(UserID);

                            //hashmap to create data
                            Map<String,Object> user=new HashMap<>();
                            user.put("Fname",fullname);
                            user.put("Email",email);
                            user.put("Phone",phone);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: user profile is created for "+ UserID);
                                }
                            });


                            startActivity(new Intent(getApplicationContext(),dashboard.class));
                        }else {
                            Toast.makeText(register.this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(view.GONE);


                        }

                    }
                });


            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        });
    }
}
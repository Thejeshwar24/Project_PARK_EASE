package com.example.project_park_ease;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

public class profile extends AppCompatActivity {

    TextView fullname,email,phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        fullname = findViewById(R.id.textviewName);
        email = findViewById(R.id.textviewemail);
        phone = findViewById(R.id.textviewphone);

        fAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("users").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {


                Map<String, Object> data = value.getData();

                Intent intent=getIntent();
                String Username= (String) data.get("Fname");
                String Userphone=(String) data.get("Phone");
                String Useremail=(String) data.get("Email");


                fullname.setText(Username);
                email.setText(Useremail);
                phone.setText(Userphone);

            }
        });
    }

    public void back2(View view) {
        startActivity(new Intent(getApplicationContext(),dashboard.class));
        finish();
    }
}
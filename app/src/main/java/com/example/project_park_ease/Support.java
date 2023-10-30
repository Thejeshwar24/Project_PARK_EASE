package com.example.project_park_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Support extends AppCompatActivity {


    DatabaseReference reference;


    EditText etname,etcontact,etdetails;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        etname = findViewById(R.id.etName);
        etcontact = findViewById(R.id.etPhoneNumber);
        etdetails = findViewById(R.id.etIssueDescription);
        submit = findViewById(R.id.btnSubmit);

        reference = FirebaseDatabase.getInstance().getReference().child("Support");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etname.getText().toString();
                String phoneNumber = etcontact.getText().toString();
                String issueDescription = etdetails.getText().toString();

                // Validate name
                if (name.isEmpty()) {
                    etname.setError("Name is required");
                    etname.requestFocus();
                    return;
                }

                // Validate phone number
                if (phoneNumber.isEmpty()) {
                    etcontact.setError("Phone Number is required");
                    etcontact.requestFocus();
                    return;
                } else if (!phoneNumber.matches("\\d{10}")) {
                    etdetails.setError("Invalid Phone Number (10 digits required)");
                    etdetails.requestFocus();
                    return;
                }

                int wordCount = issueDescription.trim().split("\\s+").length;
                if (issueDescription.isEmpty()){
                    etdetails.setError("Required");
                    etdetails.requestFocus();
                    return;
                } else if (wordCount > 100) {
                    etdetails.setError("Issue Description exceeds 100 words");
                    etdetails.requestFocus();
                    return;
                    
                }

                etname.setText("");
                etcontact.setText("");
                etdetails.setText("");


                String message = "Name: " + name + "\nPhone Number: " + phoneNumber + "\nIssue Description: " + issueDescription;
                Toast.makeText(Support.this, message, Toast.LENGTH_LONG).show();

                insertData();
            }

            private void insertData() {
                String name,contact,issue;

                name=String.valueOf(etname.getText());
                contact=String.valueOf(etcontact.getText());
                issue=String.valueOf(etdetails.getText());

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(Support.this,"Name is required.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contact)){
                    Toast.makeText(Support.this,"Contact is required.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(issue)){
                    Toast.makeText(Support.this,"Please enter your issue.",Toast.LENGTH_SHORT).show();
                    return;
                }

                CustomerSupport cS=new CustomerSupport(name,contact,issue);
                reference.push().setValue(cS);
                Toast.makeText(Support.this,"Your query has been recorded.",Toast.LENGTH_SHORT).show();



            }
        });

    }



    public void back4(View view) {
        startActivity(new Intent(getApplicationContext(), dashboard.class));
        finish();
    }
}
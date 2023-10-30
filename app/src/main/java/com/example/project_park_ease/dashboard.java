package com.example.project_park_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void Home(View view) {
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }
    public void location(View view){

        startActivity(new Intent(getApplicationContext(),Location.class));
        finish();
    }
    public void logout(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }

    public void setting(View view) {
        startActivity(new Intent(getApplicationContext(),settings.class));
        finish();
    }

    public void book(View view) {
        startActivity(new Intent(getApplicationContext(),Booking.class));
        finish();
    }

    public void support(View view) {
        startActivity(new Intent(getApplicationContext(),Support.class));
        finish();

    }
}
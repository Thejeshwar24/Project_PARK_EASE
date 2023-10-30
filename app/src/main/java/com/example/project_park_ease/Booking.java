package com.example.project_park_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
    }
    public void back_settings(View view) {
        startActivity(new Intent(getApplicationContext(),dashboard.class));
        finish();
    }

    public void booking_slot(View view) {
        startActivity(new Intent(getApplicationContext(),Booking_slots.class));
        finish();
    }
}
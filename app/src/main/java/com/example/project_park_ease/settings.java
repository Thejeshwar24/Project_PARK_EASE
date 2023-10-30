package com.example.project_park_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(),dashboard.class));
        finish();
    }

    public void profile(View view) {
        startActivity(new Intent(getApplicationContext(),profile.class));
        finish();
    }
}
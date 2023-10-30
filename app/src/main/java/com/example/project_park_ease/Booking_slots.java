package com.example.project_park_ease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
//import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Booking_slots extends AppCompatActivity {

    DatabaseReference reference;

    Calendar myCalendar = Calendar.getInstance();
    private EditText etname,etcontact, datePicker,startTime,endTime;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_slots);

        etname = findViewById(R.id.nameEditText);
        etcontact =findViewById(R.id.contactEditText);
        datePicker =  findViewById(R.id.datePickerEditText);
        startTime =  findViewById(R.id.startTimeEditText);
        endTime =  findViewById(R.id.endTimeEditText);
        saveBtn =findViewById(R.id.saveButton);

        reference = FirebaseDatabase.getInstance().getReference().child("Bookings");


        selectDate();

        startTime.setOnClickListener(view -> selectTime());
        endTime.setOnClickListener(view -> selectendTime());
//        datePicker.setOnClickListener(view -> updateDate());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                insertData();

            }
        });



    }

    private void insertData() {
        String name,contact,Date,startingTime,endingTime;

        name=String.valueOf(etname.getText());
        contact=String.valueOf(etcontact.getText());
        Date=String.valueOf(datePicker.getText());
        startingTime=String.valueOf(startTime.getText());
        endingTime=String.valueOf(endTime.getText());





        if (TextUtils.isEmpty(name)){
            etname.setError("Required");
            etname.requestFocus();
            Toast.makeText(Booking_slots.this,"Enter a valid Name.",Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(contact)){
            Toast.makeText(Booking_slots.this,"Enter a valid contact number.",Toast.LENGTH_SHORT).show();
            etcontact.setError("Required");
            etcontact.requestFocus();
            return;
        } else if (!contact.matches("\\d{10}")) {
            etcontact.setError("Invalid Phone Number (10 digits required)");
            etcontact.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Date)){
            Toast.makeText(Booking_slots.this,"Select a valid Date.",Toast.LENGTH_SHORT).show();
//            datePicker.setError("Required");
//            datePicker.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(startingTime)){
            Toast.makeText(Booking_slots.this,"Select a valid start time.",Toast.LENGTH_SHORT).show();
//            startTime.setError("Required");
//            startTime.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(endingTime)){
            Toast.makeText(Booking_slots.this,"Select a valid end time.",Toast.LENGTH_SHORT).show();
//            endTime.setError("Required");
//            endTime.requestFocus();
            return;
        }

        etname.setText("");
        etcontact.setText("");
        datePicker.setText("");
        startTime.setText("");
        endTime.setText("");


        Booking_Helper booking_helper = new Booking_Helper(name,contact,Date,startingTime,endingTime);
        reference.push().setValue(booking_helper);
        Toast.makeText(Booking_slots.this,"Your slot has Been registered. ",Toast.LENGTH_SHORT).show();




    }

    private void selectDate(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);

                updateDate();

            }
        };
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Booking_slots.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private void updateDate(){
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dataFormat = new SimpleDateFormat(myFormat, Locale.US);
        datePicker.setText(dataFormat.format(myCalendar.getTime()));
//        return dataFormat.format(myCalendar.getTime());
    }

    private void selectTime(){
        Calendar currentTime = Calendar.getInstance();
        int hour= currentTime.get(Calendar.HOUR_OF_DAY);
        int min = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(Booking_slots.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int min) {
                currentTime.set(Calendar.HOUR_OF_DAY,hour);
                currentTime.set(Calendar.MINUTE,min);

                String myFormat = "HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat,Locale.US);
                startTime.setText(dateFormat.format(currentTime.getTime()));

            }
        },hour,min,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    private void selectendTime(){
        Calendar currentTime = Calendar.getInstance();
        int hour= currentTime.get(Calendar.HOUR_OF_DAY);
        int min = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(Booking_slots.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int min) {
                currentTime.set(Calendar.HOUR_OF_DAY,hour);
                currentTime.set(Calendar.MINUTE,min);

                String myFormat = "HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat,Locale.US);
                endTime.setText(dateFormat.format(currentTime.getTime()));
            }
        },hour,min,true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }




    public void back3(View view) {
        startActivity(new Intent(getApplicationContext(),Booking.class));
        finish();
    }
}
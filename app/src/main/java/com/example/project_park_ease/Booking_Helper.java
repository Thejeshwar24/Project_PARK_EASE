package com.example.project_park_ease;

public class Booking_Helper {
    String name,contact,Date,startingTime,endingTime;

    public Booking_Helper(String name, String contact, String date, String startingTime, String endingTime) {
        this.name = name;
        this.contact = contact;
        Date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getDate() {
        return Date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }
}

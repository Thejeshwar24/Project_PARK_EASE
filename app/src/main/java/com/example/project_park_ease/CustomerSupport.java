package com.example.project_park_ease;

public class CustomerSupport {
    String name,contact,issue;

    public CustomerSupport(String name, String contact, String issue) {
        this.name = name;
        this.contact = contact;
        this.issue = issue;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getIssue() {
        return issue;
    }
}

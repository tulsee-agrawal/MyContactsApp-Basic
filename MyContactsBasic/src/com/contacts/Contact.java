package com.contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Contact {
    private String id; // Unique ID
    private String name;
    private List<String> phoneNumbers = new ArrayList<>(); // Composition
    private List<String> emails = new ArrayList<>();

    public Contact(String name) {
        this.id = UUID.randomUUID().toString(); // UUID for unique identification
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getPhoneNumbers() { return phoneNumbers; }
    public List<String> getEmails() { return emails; }

    // Logic to add multiple entries
    public void addPhone(String phone) { this.phoneNumbers.add(phone); }
    public void addEmail(String email) { this.emails.add(email); }

    public abstract String getContactType(); // Abstraction
    
 // UC-05: toString() override for display formatting
    @Override
    public String toString() {
        return String.format(
            "ID: %s | Type: %s | Name: %s\nPhones: %s\nEmails: %s",
            id, getContactType(), name, phoneNumbers, emails
        ); 
    }
}
package com.user;

import com.contacts.*;
import com.search.*;
import com.filter.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.*;


public class UserController {
    private static UserController instance;
    private Map<String, User> userDatabase = new HashMap<>();

    private UserController() {}

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public Map<String, User> getUserDatabase() {
        return userDatabase;
    }

    public String register(String name, String email, String password, String phone, UserType type) {
        // Final logic-level validation
        if (userDatabase.containsKey(email.toLowerCase())) {
            return "Error: User already exists with this email.";
        }

        String hashed = hashPassword(password);
        User newUser = new User(name, email.toLowerCase(), hashed, phone, type);
        userDatabase.put(email.toLowerCase(), newUser);

        return "Success: Account created for " + name;
    }
    public User login(String email, String password) {
        User user = userDatabase.get(email.toLowerCase());
        if (user != null && user.getHashPwd().equals(hashPassword(password))) return user;
        return null;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return password;
        }
    }
    public String updateProfile(User user, String newName, String newPhone) {
        if (newName.isEmpty() || !newPhone.matches("\\d{10}")) {
            return "Error: Invalid update data.";
        }
        user.setName(newName);
        user.setPhoneNumber(newPhone);
        return "Profile updated successfully!";
    }

    public String changePassword(User user, String oldPass, String newPass) {
        // Verify old password first
        if (!user.getHashPwd().equals(hashPassword(oldPass))) {
            return "Error: Old password does not match.";
        }
        if (newPass.length() < 6) {
            return "Error: New password too short.";
        }
        
        user.updatePassword(hashPassword(newPass));
        return "Password changed successfully!";
    }
    public void createContact(User currentUser, String name, String phone, String email, int type) {
        Contact newContact;
        if (type == 2) {
            newContact = new Organization(name); // Inheritance
        } else {
            newContact = new Person(name);
        }
        
        newContact.addPhone(phone); // Composition logic
        newContact.addEmail(email);
        
        currentUser.addContact(newContact);
    }
    public void listContacts(User user) {
        if (user.getMyContacts().isEmpty()) {
            System.out.println("Your contact list is empty.");
            return;
        }
        System.out.println("\n--- YOUR CONTACTS ---");
        for (Contact c : user.getMyContacts()) {
            System.out.println(c.toString()); // Polymorphism in action
            System.out.println("----------------------");
        }
    }
    
    public void viewContactDetails(User user, String contactId) {
        Optional<Contact> found = user.getMyContacts().stream()
            .filter(c -> c.getId().equalsIgnoreCase(contactId))
            .findFirst();

        if (found.isPresent()) {
            System.out.println("\n--- CONTACT DETAILS ---");
            System.out.println(found.get());
        } else {
            System.out.println("Error: Contact with ID " + contactId + " not found.");
        }
    }
    // Edit Contact
    public String editContact(User user, String contactId, String newName, String newPhone, String newEmail) {
        Optional<Contact> found = user.getMyContacts().stream()
            .filter(c -> c.getId().equalsIgnoreCase(contactId))
            .findFirst();

        if (found.isPresent()) {
            Contact contact = found.get();
           
            contact.setName(newName);
            
            contact.getPhoneNumbers().clear();
            contact.addPhone(newPhone);
            
            contact.getEmails().clear();
            contact.addEmail(newEmail);
            
            return "Contact '" + contactId + "' updated successfully!";
        } else {
            return "Error: Contact ID not found.";
        }
    }
    public boolean deleteContact(User user, String contactId) {
        // Lifecycle management: Removing the object from the managed list
        // Returns true if an element was removed
        boolean removed = user.getMyContacts().removeIf(
            c -> c.getId().equalsIgnoreCase(contactId)
        );
        
        if (removed) {
            // In a full implementation, you'd trigger the Observer Pattern here
            return true;
        }
        return false;
    }
    public int bulkDeleteByNames(User user, List<String> namesToDelete) {
        int count = 0;
        Iterator<Contact> it = user.getMyContacts().iterator(); // Iterating through objects

        while (it.hasNext()) {
            Contact contact = it.next();
            // Check if current contact name is in our "target" list
            for (String targetName : namesToDelete) {
                if (contact.getName().equalsIgnoreCase(targetName.trim())) {
                    it.remove(); // Basic object manipulation (removal)
                    count++;
                    break; // Move to next contact once deleted
                }
            }
        }
        return count;
    }
    public void performSearch(User user, SearchCriteria criteria, String query) {
        List<Contact> results = criteria.search(user.getMyContacts(), query);
        if (results.isEmpty()) {
            System.out.println("No contacts found matching: " + query);
        } else {
            System.out.println("--- Search Results ---");
            for (Contact c : results) {
                System.out.println(c);
            }
        }
    }
    public void filterContacts(User user, ContactFilter filter) {
        if (user.getMyContacts().isEmpty()) {
            System.out.println("No contacts to filter.");
            return;
        }
        
        // Apply the chosen filter logic
        filter.applyFilter(user.getMyContacts());
        System.out.println("Filter applied successfully.");
        
        // Display the newly sorted list
        listContacts(user); 
    }
}


package com.search;
import com.contacts.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneSearch implements SearchCriteria {
    @Override
    public List<Contact> search(List<Contact> contacts, String query) {
        List<Contact> results = new ArrayList<>();
        for (Contact c : contacts) {
            for (String phone : c.getPhoneNumbers()) {
                if (phone.contains(query)) { // Simple contains check
                    results.add(c);
                    break;
                }
            }
        }
        return results;
    }
}
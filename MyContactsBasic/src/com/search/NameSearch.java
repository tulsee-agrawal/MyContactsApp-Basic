package com.search;
import com.contacts.*;
import java.util.ArrayList;
import java.util.List;

public class NameSearch implements SearchCriteria {
    @Override
    public List<Contact> search(List<Contact> contacts, String query) {
        List<Contact> results = new ArrayList<>();
        // Loops for searching
        for (Contact c : contacts) {
            // Case-insensitive comparison
            if (c.getName().toLowerCase().contains(query.toLowerCase())) {
                results.add(c);
            }
        }
        return results;
    }
}

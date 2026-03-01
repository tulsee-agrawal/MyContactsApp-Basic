package com.filter;

import com.contacts.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DateSortFilter implements ContactFilter {
    @Override
    public void applyFilter(List<Contact> contacts) {
        // Java Concept: Collections.sort()
        // Since UUID contains a timestamp-like sequence or order of creation
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getId().compareTo(c2.getId());
            }
        });
    }
}
package com.filter;

import com.contacts.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NameSortFilter implements ContactFilter {
    @Override
    public void applyFilter(List<Contact> contacts) {
        // Java Concept: Comparator for sorting alphabetically
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });
    }
}
package com.filter;

import com.contacts.*;
import java.util.List;

public interface ContactFilter {
    // Simple filter interface
    void applyFilter(List<Contact> contacts);
}
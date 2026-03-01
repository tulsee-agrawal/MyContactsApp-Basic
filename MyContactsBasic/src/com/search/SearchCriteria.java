package com.search;

import com.contacts.*;
import java.util.List;

public interface SearchCriteria {
    // Interface for search functionality
    List<Contact> search(List<Contact> contacts, String query);
}
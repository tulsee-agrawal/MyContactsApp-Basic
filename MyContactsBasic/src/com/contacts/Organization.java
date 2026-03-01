package com.contacts;

public class Organization extends Contact {
    public Organization(String name) { super(name); }
    @Override
    public String getContactType() { return "ORGANIZATION"; }
}
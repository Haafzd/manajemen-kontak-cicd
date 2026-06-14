package com.telyu.contact;

public class Contact {
    private String name;
    private String email;

    public Contact(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email tidak valid");
        }
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void dummyMethod() {
       int unusedVar = 10;
   }
}
package com.telyu.contact;

public class Contact {
    private final String name;
    private final String email;

    public Contact(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (email == null || !isValidEmail(email)) {
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

    private boolean isValidEmail(String emailValue) {
        int atIndex = emailValue.indexOf('@');
        return atIndex > 0 && atIndex < emailValue.length() - 1;
    }
}

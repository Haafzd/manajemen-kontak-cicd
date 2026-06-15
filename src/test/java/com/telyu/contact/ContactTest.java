package com.telyu.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    public void testValidContact() {
        Contact contact = new Contact("Gilang", "gilang@telyu.com");
        assertEquals("Gilang", contact.getName());
        assertEquals("gilang@telyu.com", contact.getEmail());
    }

    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("", "gilang@telyu.com");
        });
    }

    @Test
    public void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("Gilang", "invalid-email");
        });
    }

    @Test
    public void testEmailTanpaDomain() {
       assertThrows(IllegalArgumentException.class, () -> {
           new Contact("Khansa", "khansa@");
       });
   }
}
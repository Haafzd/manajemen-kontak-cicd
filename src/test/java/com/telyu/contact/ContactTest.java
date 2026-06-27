package com.telyu.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("Gilang", "gilang@telyu.ac.id", "081234567890", "MAHASISWA");
    }

    // ── Constructor & Validasi ──────────────────────────

    @Test
    public void testValidContactTwoParam() {
        Contact c = new Contact("Habib", "habib@telyu.ac.id");
        assertEquals("Habib", c.getName());
        assertEquals("habib@telyu.ac.id", c.getEmail());
        assertEquals("GENERAL", c.getCategory());
        assertTrue(c.isActive());
    }

    @Test
    public void testValidContactFourParam() {
        assertEquals("Gilang", contact.getName());
        assertEquals("gilang@telyu.ac.id", contact.getEmail());
        assertEquals("081234567890", contact.getPhone());
        assertEquals("MAHASISWA", contact.getCategory());
    }

    @Test
    public void testNameTrimmed() {
        Contact c = new Contact("  Khansa  ", "khansa@telyu.ac.id");
        assertEquals("Khansa", c.getName());
    }

    @Test
    public void testEmailLowercased() {
        Contact c = new Contact("Riza", "RIZA@Telyu.AC.ID");
        assertEquals("riza@telyu.ac.id", c.getEmail());
    }

    @Test
    public void testNameTooShort() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("A", "a@b.com"));
    }

    @Test
    public void testNameEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("", "a@b.com"));
    }

    @Test
    public void testEmailNoAt() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Habib", "habib-invalid-email"));
    }

    @Test
    public void testEmailNoDomain() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Habib", "habib@"));
    }

    @Test
    public void testEmailNoDot() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Habib", "habib@telyu"));
    }

    @Test
    public void testPhoneInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("Habib", "h@b.com", "abc", "UMUM"));
    }

    // ── Deactivate / Reactivate ─────────────────────────

    @Test
    public void testDeactivate() {
        contact.deactivate();
        assertFalse(contact.isActive());
    }

    @Test
    public void testReactivate() {
        contact.deactivate();
        contact.reactivate();
        assertTrue(contact.isActive());
    }

    // ── Email Utilities ─────────────────────────────────

    @Test
    public void testGetEmailUsername() {
        assertEquals("gilang", contact.getEmailUsername());
    }

    @Test
    public void testGetEmailDomain() {
        assertEquals("telyu.ac.id", contact.getEmailDomain());
    }

    @Test
    public void testIsSameDomainTrue() {
        assertTrue(contact.isSameDomain("telyu.ac.id"));
    }

    @Test
    public void testIsSameDomainFalse() {
        assertFalse(contact.isSameDomain("gmail.com"));
    }

    @Test
    public void testIsSameDomainNull() {
        assertFalse(contact.isSameDomain(null));
    }

    // ── nameContains ─────────────────────────────────────

    @Test
    public void testNameContainsFound() {
        assertTrue(contact.nameContains("gil"));
    }

    @Test
    public void testNameContainsCaseInsensitive() {
        assertTrue(contact.nameContains("GIL"));
    }

    @Test
    public void testNameContainsNotFound() {
        assertFalse(contact.nameContains("Budi"));
    }

    // ── Update Methods ──────────────────────────────────

    @Test
    public void testUpdateEmail() {
        contact.updateEmail("gilang.new@gmail.com");
        assertEquals("gilang.new@gmail.com", contact.getEmail());
    }

    @Test
    public void testUpdateEmailInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> contact.updateEmail("tidak-valid"));
    }

    @Test
    public void testUpdateName() {
        contact.updateName("Gilang Tirta");
        assertEquals("Gilang Tirta", contact.getName());
    }

    @Test
    public void testUpdateNameInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> contact.updateName(""));
    }

    @Test
    public void testSetPhone() {
        contact.setPhone("+6281299990000");
        assertEquals("+6281299990000", contact.getPhone());
    }

    @Test
    public void testSetCategory() {
        contact.setCategory("dosen");
        assertEquals("DOSEN", contact.getCategory());
    }

    // ── Display & Serialisasi ───────────────────────────

    @Test
    public void testToDisplayStringActive() {
        String display = contact.toDisplayString();
        assertTrue(display.contains("Gilang"));
        assertTrue(display.contains("gilang@telyu.ac.id"));
        assertTrue(display.contains("MAHASISWA"));
        assertFalse(display.contains("NONAKTIF"));
    }

    @Test
    public void testToDisplayStringInactive() {
        contact.deactivate();
        assertTrue(contact.toDisplayString().contains("NONAKTIF"));
    }

    @Test
    public void testToCsvString() {
        String csv = contact.toCsvString();
        assertTrue(csv.startsWith("Gilang,"));
        assertTrue(csv.contains("gilang@telyu.ac.id"));
        assertTrue(csv.contains("true"));
    }

    @Test
    public void testToString() {
        String str = contact.toString();
        assertTrue(str.contains("Contact{"));
        assertTrue(str.contains("Gilang"));
    }

    // ── Equals & HashCode ───────────────────────────────

    @Test
    public void testEqualsSameEmail() {
        Contact c1 = new Contact("Gilang", "gilang@telyu.ac.id");
        Contact c2 = new Contact("Gilang Tirta", "GILANG@TELYU.AC.ID");
        assertEquals(c1, c2);
    }

    @Test
    public void testNotEqualsDifferentEmail() {
        Contact c1 = new Contact("Gilang", "gilang@telyu.ac.id");
        Contact c2 = new Contact("Gilang", "gilang@gmail.com");
        assertNotEquals(c1, c2);
    }

    @Test
    public void testHashCodeConsistency() {
        Contact c1 = new Contact("Gilang", "gilang@telyu.ac.id");
        Contact c2 = new Contact("Nama Lain", "GILANG@TELYU.AC.ID");
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
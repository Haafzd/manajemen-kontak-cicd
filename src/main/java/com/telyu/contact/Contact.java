package com.telyu.contact;

/**
 * Representasi sebuah kontak dengan validasi dan utilitas lengkap.
 * Mendukung operasi format, perbandingan, update, dan serialisasi kontak.
 */
public class Contact {

    private String name;
    private String email;
    private String phone;
    private String category;
    private boolean active;

    // ──────────────────────────────────────────────
    // Constructor
    // ──────────────────────────────────────────────

    public Contact(String name, String email) {
        this(name, email, null, "GENERAL");
    }

    public Contact(String name, String email, String phone, String category) {
        validateName(name);
        validateEmail(email);
        if (phone != null) validatePhone(phone);
        validateCategory(category);
        this.name     = name.trim();
        this.email    = email.trim().toLowerCase();
        this.phone    = phone != null ? phone.trim() : null;
        this.category = category.toUpperCase();
        this.active   = true;
    }

    // ──────────────────────────────────────────────
    // Validasi Statis
    // ──────────────────────────────────────────────

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (name.trim().length() < 2) {
            throw new IllegalArgumentException("Nama minimal 2 karakter");
        }
        if (name.trim().length() > 100) {
            throw new IllegalArgumentException("Nama maksimal 100 karakter");
        }
    }

    private static void validateEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email tidak boleh null");
        }
        int atIndex = email.indexOf('@');
        if (atIndex < 1 || atIndex >= email.length() - 1) {
            throw new IllegalArgumentException("Email tidak valid: " + email);
        }
        String domain = email.substring(atIndex + 1);
        if (!domain.contains(".")) {
            throw new IllegalArgumentException("Domain email tidak valid: " + domain);
        }
    }

    private static void validatePhone(String phone) {
        String digits = phone.replaceAll("[\\s\\-+()]", "");
        if (digits.isEmpty()) {
            throw new IllegalArgumentException("Nomor telepon tidak boleh kosong");
        }
        if (!digits.matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Nomor telepon tidak valid: " + phone);
        }
    }

    private static void validateCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Kategori tidak boleh kosong");
        }
    }

    // ──────────────────────────────────────────────
    // Getter / Setter
    // ──────────────────────────────────────────────

    public String getName()     { return name; }
    public String getEmail()    { return email; }
    public String getPhone()    { return phone; }
    public String getCategory() { return category; }
    public boolean isActive()   { return active; }

    public void setPhone(String phone) {
        if (phone != null) validatePhone(phone);
        this.phone = phone != null ? phone.trim() : null;
    }

    public void setCategory(String category) {
        validateCategory(category);
        this.category = category.toUpperCase();
    }

    // ──────────────────────────────────────────────
    // Utilitas Kontak
    // ──────────────────────────────────────────────

    /** Menonaktifkan kontak (soft-delete). */
    public void deactivate() {
        this.active = false;
    }

    /** Mengaktifkan kembali kontak. */
    public void reactivate() {
        this.active = true;
    }

    /**
     * Mengekstrak username dari alamat email.
     * Contoh: "habib@telyu.ac.id" → "habib"
     */
    public String getEmailUsername() {
        return email.substring(0, email.indexOf('@'));
    }

    /**
     * Mengekstrak domain dari alamat email.
     * Contoh: "habib@telyu.ac.id" → "telyu.ac.id"
     */
    public String getEmailDomain() {
        return email.substring(email.indexOf('@') + 1);
    }

    /**
     * Mengembalikan format tampilan lengkap:
     * "Nama (email) [category]"
     */
    public String toDisplayString() {
        String phoneInfo = (phone != null) ? " | Tel: " + phone : "";
        String statusInfo = active ? "" : " [NONAKTIF]";
        return name + " (" + email + ")" + phoneInfo + " [" + category + "]" + statusInfo;
    }

    /**
     * Mengembalikan representasi CSV: name,email,phone,category,active
     */
    public String toCsvString() {
        return String.join(",",
                escapeCsv(name),
                escapeCsv(email),
                escapeCsv(phone != null ? phone : ""),
                escapeCsv(category),
                String.valueOf(active));
    }

    private String escapeCsv(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    /**
     * Memeriksa apakah kontak ini menggunakan domain email tertentu.
     * Contoh: isSameDomain("telyu.ac.id") → true jika email berakhiran @telyu.ac.id
     */
    public boolean isSameDomain(String domain) {
        if (domain == null || domain.isEmpty()) return false;
        return getEmailDomain().equalsIgnoreCase(domain.trim());
    }

    /**
     * Memeriksa apakah nama kontak mengandung kata kunci tertentu (case-insensitive).
     */
    public boolean nameContains(String keyword) {
        if (keyword == null || keyword.isEmpty()) return false;
        return name.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Memperbarui data email kontak dengan validasi.
     */
    public void updateEmail(String newEmail) {
        validateEmail(newEmail);
        this.email = newEmail.trim().toLowerCase();
    }

    /**
     * Memperbarui nama kontak dengan validasi.
     */
    public void updateName(String newName) {
        validateName(newName);
        this.name = newName.trim();
    }

    @Override
    public String toString() {
        return "Contact{name='" + name + "', email='" + email
                + "', phone='" + phone + "', category='" + category
                + "', active=" + active + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact other = (Contact) o;
        return email.equalsIgnoreCase(other.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }
}

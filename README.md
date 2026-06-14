# Manajemen Kontak CI/CD

Project Java Maven sederhana untuk Tugas Besar Manajemen Konfigurasi dan Evolusi Perangkat Lunak.

## Fitur
- Membuat objek kontak dengan nama dan email.
- Validasi nama tidak boleh kosong.
- Validasi email harus mengandung karakter `@`.

## Komponen CI/CD
- Continuous Integration: compile project dengan Maven.
- Continuous Testing: menjalankan unit test JUnit 5.
- Continuous Inspection: static analysis menggunakan PMD.
- Continuous Delivery/Deployment: publish JAR ke GitHub Packages saat GitHub Release dibuat.

## Perintah Lokal

```bash
mvn clean compile
mvn test
mvn pmd:check
mvn clean verify
mvn package
```

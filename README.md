# Tugas Praktikum 8 - Platform Specific Features Notes App

## Identitas

Nama : Jana Rohman Wasiso  
NIM : 123140046  
Kelas : PAM RB  

## Deskripsi

Notes App adalah aplikasi pencatatan sederhana yang dikembangkan untuk memenuhi tugas praktikum minggu ke-8. Pada praktikum ini, aplikasi Notes App dari praktikum sebelumnya di-upgrade dengan fitur platform-specific, yaitu penggunaan Dependency Injection menggunakan Koin, implementasi informasi perangkat, serta pemantauan status koneksi internet.

Aplikasi ini tetap mendukung fitur utama Notes App seperti CRUD catatan, pencarian catatan, pengaturan tema, pengaturan urutan catatan, serta penyimpanan data lokal. Selain itu, aplikasi juga dapat menampilkan informasi perangkat pada halaman Settings dan menampilkan indikator ketika perangkat tidak terhubung ke internet.

## Fitur Utama

- Menyimpan data catatan secara lokal menggunakan SQLDelight
- Menampilkan daftar catatan pada halaman utama
- Menambahkan catatan baru
- Melihat isi catatan yang sudah tersimpan
- Mengubah catatan yang telah dibuat
- Menghapus catatan dari database lokal
- Mencari catatan berdasarkan judul atau isi
- Mengatur tema aplikasi: Light, Dark, dan System
- Mengatur urutan catatan: Newest First dan Oldest First
- Menggunakan Dependency Injection dengan Koin
- Menampilkan informasi perangkat pada halaman Settings
- Menampilkan nama device
- Menampilkan versi sistem operasi Android
- Menampilkan versi aplikasi
- Memantau status koneksi internet
- Menampilkan indikator ketika perangkat offline
- Tetap dapat digunakan tanpa koneksi internet

## Architecture diagram
<img width="1783" height="916" alt="diagram" src="https://github.com/user-attachments/assets/9f4117b6-3b9b-4f3b-8abb-67e028ca7279" />


## Platform Specific Features

Pada praktikum ini, aplikasi menerapkan fitur platform-specific yang berjalan pada Android.

### Device Info

Fitur Device Info digunakan untuk menampilkan informasi perangkat pada halaman Settings.

Informasi yang ditampilkan:

- Device Name
- OS Version
- App Version

Implementasi dilakukan melalui class `DeviceInfo`.

### Network Monitor

Fitur Network Monitor digunakan untuk mengecek status koneksi internet pada perangkat.

Jika perangkat tidak memiliki koneksi internet, aplikasi akan menampilkan indikator:

```text
No Internet Connection
```

## Cara Menjalankan Aplikasi

1. Pilih Repository praktikum8: https://github.com/10-046-JanaRohman/TugasPraktikum8_PAM_123140046.git 
2. Clone atau download repository ini.
3. Buka folder project menggunakan Android Studio.
4. Tunggu proses Gradle Sync sampai selesai.
5. Pilih emulator atau device Android.
6. Jalankan aplikasi dengan menekan tombol Run.
7. Aplikasi akan terbuka pada emulator atau device yang dipilih.

## Screenshoot Aplikasi

- Device info
<img width="415" height="846" alt="Screenshot 2026-04-26 221101" src="https://github.com/user-attachments/assets/2739b4ac-b0f0-4158-9353-7eddfbe98715" />

- Network Online
<img width="432" height="850" alt="Screenshot 2026-04-26 221047" src="https://github.com/user-attachments/assets/310e1ae0-2217-41dd-99c9-29ee2c3ae764" />

- Network Offline
<img width="432" height="850" alt="Screenshot 2026-04-26 221027" src="https://github.com/user-attachments/assets/48a4236d-257f-4b1e-a413-3d5c30b16283" />

## Link Video

https://drive.google.com/file/d/1KNmgu-PTEewi7r6_Gy60qHgofiKGsW7B/view?usp=sharing 

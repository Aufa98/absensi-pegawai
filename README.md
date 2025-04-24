### Pegawai

| Method | Endpoint           | Deskripsi                         |
|--------|--------------------|-----------------------------------|
| GET    | /pegawai           | Ambil semua pegawai               |
| POST   | /pegawai           | Tambah pegawai baru               |
| PUT    | /pegawai/{id}      | Update data pegawai berdasarkan ID |
| DELETE | /pegawai/{id}      | Hapus data pegawai berdasarkan ID |

### Absensi

| Method | Endpoint           | Deskripsi                          |
|--------|--------------------|------------------------------------|
| GET    | /absensi           | Ambil semua data absensi           |
| POST   | /absensi           | Tambah data absensi baru           |
| PUT    | /absensi/{id}      | Update data absensi berdasarkan ID |
| DELETE | /absensi/{id}      | Hapus data absensi berdasarkan ID  |

### Rekap

| Method | Endpoint           | Deskripsi                            |
|--------|--------------------|--------------------------------------|
| GET    | /rekap/{pegawaiId} | Ambil rekap absensi pegawai tertentu |

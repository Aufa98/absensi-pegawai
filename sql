-- Buat database
CREATE DATABASE absensi_db;

-- Buat tabel pegawai
CREATE TABLE pegawai (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    jabatan VARCHAR(100) NOT NULL
);

-- Buat tabel absensi
CREATE TABLE absensi (
    id SERIAL PRIMARY KEY,
    pegawai_id INT NOT NULL REFERENCES pegawai(id) ON DELETE CASCADE,
    tanggal DATE NOT NULL,
    hadir BOOLEAN NOT NULL
);

-- Tambah data dummy pegawai
INSERT INTO pegawai(nama, jabatan) VALUES
('Jack Doe', 'HRD'),
('John Doe', 'Programmer'),
('Jane Doe', 'Admin');

-- Tambah data dummy absensi
INSERT INTO absensi(pegawai_id, tanggal, hadir) VALUES
(1, '2025-04-20', TRUE),
(2, '2025-04-20', TRUE),
(3, '2025-04-20', FALSE),
(1, '2025-04-21', TRUE),
(2, '2025-04-21', TRUE),
(3, '2025-04-21', TRUE);

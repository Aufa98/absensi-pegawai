package com.example.absensi;

import java.time.LocalDate;

public class Absensi {
    private int id;
    private int pegawaiId;
    private LocalDate tanggal;
    private boolean hadir;

    public Absensi() {}

    public Absensi(int id, int pegawaiId, LocalDate tanggal, boolean hadir) {
        this.id = id;
        this.pegawaiId = pegawaiId;
        this.tanggal = tanggal;
        this.hadir = hadir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPegawaiId() {
        return pegawaiId;
    }

    public void setPegawaiId(int pegawaiId) {
        this.pegawaiId = pegawaiId;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public boolean isHadir() {
        return hadir;
    }

    public void setHadir(boolean hadir) {
        this.hadir = hadir;
    }

    
}

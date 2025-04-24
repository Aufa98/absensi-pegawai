package com.example.absensi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private AppRepository repo;

    //pegawai
    @GetMapping("/pegawai")
    public ResponseEntity<?> getAllPegawai() {
        try {
            List<Pegawai> listPegawai = repo.getAllPegawai();
            if (listPegawai != null && !listPegawai.isEmpty()) {
                return ResponseEntity.ok(listPegawai);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Data pegawai tidak ditemukan"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Terjadi kesalahan saat mengambil data pegawai",
                    "error", e.getMessage()
                ));
        }
    }

    @PostMapping("/pegawai")
    public ResponseEntity<String> addPegawai(@RequestBody Pegawai pegawai) {
        try {
            int result = repo.savePegawai(pegawai);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Pegawai berhasil ditambahkan");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gagal menambahkan pegawai");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error saat menambahkan pegawai: " + e.getMessage());
        }
    }

    @PutMapping("/pegawai/{id}")
    public ResponseEntity<String> updatePegawai(@PathVariable int id, @RequestBody Pegawai pegawai) {
        try {
            int result = repo.updatePegawai(id, pegawai);
            if (result > 0) {
                return ResponseEntity.ok("Pegawai telah diupdate");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pegawai tidak ditemukan");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error saat memperbarui pegawai: " + e.getMessage());
        }
    }

    @DeleteMapping("/pegawai/{id}")
    public ResponseEntity<String> deletePegawai(@PathVariable int id) {
        try {
            int result = repo.deletePegawai(id);
            if (result > 0) {
                return ResponseEntity.ok("Pegawai dihapus");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pegawai tidak ditemukan");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error saat menghapus pegawai: " + e.getMessage());
        }
    }

    //absensi
    @GetMapping("/absensi")
    public ResponseEntity<?> getAllAbsensi() {
        try {
            List<Absensi> listAbsensi = repo.getAllAbsensi();
            if (listAbsensi != null && !listAbsensi.isEmpty()) {
                return ResponseEntity.ok(listAbsensi);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Data absensi tidak ditemukan"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Terjadi kesalahan saat mengambil data absensi",
                    "error", e.getMessage()
                ));
        }
    }

    @PostMapping("/absensi")
    public ResponseEntity<String> addAbsensi(@RequestBody Absensi absensi) {
        try {
            int result = repo.saveAbsensi(absensi);
            if (result > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Absensi telah dicatat");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gagal mencatat absensi");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error saat mencatat absensi: " + e.getMessage());
        }
    }

    @PutMapping("/absensi/{id}")
    public ResponseEntity<String> updateAbsensi(@PathVariable int id, @RequestBody Absensi absensi) {
        try {
            int result = repo.updateAbsensi(id, absensi);
            if (result > 0) {
                return ResponseEntity.ok("Absensi telah diupdate");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Absensi tidak ditemukan");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error saat memperbarui absensi: " + e.getMessage());
        }
    }

    @DeleteMapping("/absensi/{id}")
    public ResponseEntity<String> deleteAbsensi(@PathVariable int id) {
        try {
            int result = repo.deleteAbsensi(id);
            if (result > 0) {
                return ResponseEntity.ok("Absensi telah dihapus");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Absensi tidak ditemukan");
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error saat menghapus absensi: " + e.getMessage());
        }
    }

    @GetMapping("/rekap/{pegawaiId}")
    public ResponseEntity<Map<String, Object>> getRekapAbsensi(@PathVariable int pegawaiId) {
        try {
            Map<String, Object> rekap = repo.getRekapAbsensi(pegawaiId);
            if (rekap != null && !rekap.isEmpty()) {
                return ResponseEntity.ok(rekap);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Rekap tidak ditemukan untuk pegawai dengan ID " + pegawaiId));
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", "Terjadi kesalahan saat mengambil rekap absensi", "error", e.getMessage()));
        }
        
    }
}

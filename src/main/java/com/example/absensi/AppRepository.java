package com.example.absensi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AppRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class pegawaiMapper implements RowMapper<Pegawai> {
        @Override
        public Pegawai mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pegawai pegawai  = new Pegawai();
            pegawai.setId(rs.getInt("id"));
            pegawai.setNama(rs.getString("nama") != null ? rs.getString("nama") : null);
            pegawai.setJabatan(rs.getString("jabatan") != null ? rs.getString("jabatan") : null);
            return pegawai;
        }
    }
    
    public List<Pegawai> getAllPegawai() {
        String sql = "SELECT * FROM pegawai";
        try {
            return jdbcTemplate.query(sql, new pegawaiMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal mengambil data pegawai: " + e.getMessage());
        }       
    }

    public int savePegawai(Pegawai pegawai) {
        String sql = "INSERT INTO pegawai(nama, jabatan) VALUES (?, ?)";
        try {
            return jdbcTemplate.update(sql,pegawai.getNama(), pegawai.getJabatan());
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal menyimpan data pegawai: " + e.getMessage());
        } 
    }

    public int updatePegawai(int id, Pegawai pegawai) {
        String sql = "UPDATE pegawai SET nama = ?, jabatan = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, pegawai.getNama(), pegawai.getJabatan(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal update data pegawai: " + e.getMessage());        }
    }

    public int deletePegawai(int id) {
        String sql = "DELETE FROM pegawai WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal menghapus data pegawai: " + e.getMessage());
        }
    }

    private static class absensiMapper implements RowMapper<Absensi> {
        @Override
        public Absensi mapRow(ResultSet rs, int rowNum) throws SQLException {
            Absensi absensi = new Absensi();
            absensi.setId(rs.getInt("id"));
            absensi.setHadir(rs.getBoolean("hadir"));
            absensi.setPegawaiId(rs.getInt("pegawai_id"));
            absensi.setTanggal(rs.getDate("tanggal").toLocalDate() != null ? rs.getDate("tanggal").toLocalDate() : null);
            return absensi;
        }
    }

    public List<Absensi> getAllAbsensi() {
        String sql = "SELECT * FROM absensi";
        try {
            return jdbcTemplate.query(sql, new absensiMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal mengambil data absensi: " + e.getMessage());
        }
    }

    public int saveAbsensi(Absensi absensi) {
        LocalDate hariIni = LocalDate.now();
        String sql = "INSERT INTO absensi(pegawai_id, tanggal, hadir) VALUES (?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, absensi.getPegawaiId(), 
        hariIni, absensi.isHadir());
        } catch (Exception e) {
            throw new RuntimeException("Gagal menyimpan data absensi: " + e.getMessage());
        } 
    }

    public int updateAbsensi(int id, Absensi absensi) {
        String sql = "UPDATE absensi SET pegawai_id = ?, tanggal = ?, hadir = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, absensi.getPegawaiId(), 
            absensi.getTanggal(), absensi.isHadir(), id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal memperbarui data absensi: " + e.getMessage());
        }    
    }

    public int deleteAbsensi(int id) {
        String sql = "DELETE FROM absensi WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal menghapus data absensi: " + e.getMessage());
        }       
    }

    public Map<String, Object> getRekapAbsensi(int pegawaiId) {
        String sql = "SELECT * FROM absensi WHERE pegawai_id = ?";
        try {
            List<Absensi> absensiList = jdbcTemplate.query(sql, new absensiMapper(), pegawaiId);

            long total = absensiList.size();
            long hadir = absensiList.stream().filter(Absensi::isHadir).count();
            long tidakHadir = absensiList.stream().filter(a -> !a.isHadir()).count();
            Map<String, Object> result = new HashMap<>();

            if (total != 0) {
                result.put("pegawai_id", pegawaiId);
                result.put("total_absensi", total);
                result.put("hadir", hadir);
                result.put("tidak_hadir", tidakHadir);
            } 
            return result;
        } catch (DataAccessException e) {
            throw new RuntimeException("Gagal mengambil rekap absensi: " + e.getMessage());
        }
    }
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.nhanvien.NhanVien;
@Repository
public interface NhanVienRepository extends CrudRepository<NhanVien, String> {
@Query(value = "SELECT * FROM nhanvien u WHERE u.TenTK = ?1 AND u.MatKhau= ?2", nativeQuery = true)
NhanVien finNhanVienByAccount(String name, String pass);
@Query(value = "SELECT * FROM nhanvien u WHERE u.mabp = ?1", nativeQuery = true)
List<NhanVien> findNhanVienByBoPhanId(String id);
@Query(value = "SELECT * FROM ( SELECT * FROM nhanvien ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
NhanVien findTheLastNV();
@Query(value = "SELECT COUNT(*) FROM `nhanvien` WHERE mabp = ?1", nativeQuery = true)
int countByBoPhanId(String id);

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.sanpham.SanPham;

@Repository
public interface SanPhamRepository extends CrudRepository<SanPham, String> {
	@Query(value = "SELECT * FROM sanpham u WHERE u.manhom = ?1", nativeQuery = true)
	List<SanPham> getByNhomHangId(String id);
	@Query(value = "SELECT * FROM ( SELECT * FROM sanpham ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	SanPham getTheLast();
	@Query(value = "SELECT COUNT(*) FROM `sanpham` WHERE manhom = ?1", nativeQuery = true)
	int countByNhomHangId(String id);
	@Query(value = "SELECT sp FROM SanPham sp WHERE sp.isMenu = 1 AND sp.nhomHang.id = ?1")
	List<SanPham> getAllThucDonByNhomHangId(String id);
	@Query(value = "SELECT sp FROM SanPham sp WHERE sp.isMenu = 1")
	List<SanPham> getAllThucDon();

}

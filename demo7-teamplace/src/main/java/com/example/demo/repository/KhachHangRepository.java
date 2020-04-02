package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.khachhang.KhachHang;
@Repository
public interface KhachHangRepository extends CrudRepository<KhachHang, String> {
	@Query("SELECT u FROM KhachHang u WHERE u.nhomKhach.id = ?1")
	public List<KhachHang> findByNhomKhachId(String id);
	@Query(value = "SELECT * FROM (SELECT * FROM khachhang ORDER BY id DESC LIMIT 1)Var1 ORDER BY id ASC", nativeQuery = true)
	KhachHang findTheLastKh();
	@Query(value = "SELECT COUNT(u) FROM KhachHang u  WHERE u.nhomKhach.id = ?1")
	int countByNhomKhachId(String id);

}

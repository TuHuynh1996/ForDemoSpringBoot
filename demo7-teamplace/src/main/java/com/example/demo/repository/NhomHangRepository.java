package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.sanpham.NhomHang;
@Repository
public interface NhomHangRepository extends CrudRepository<NhomHang, String>{
	@Query(value = "SELECT * FROM ( SELECT * FROM nhomhang ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	NhomHang getTheLast();
	@Query(value = "SELECT COUNT(*) FROM `nhomhang` WHERE macha = ?1", nativeQuery = true)
	int countByMacha(String macha);
	@Query("SELECT nh FROM NhomHang nh WHERE nh.loaiNhom = ?1")
	List<NhomHang> getByLoaiNhom(int loaiNhom);

}

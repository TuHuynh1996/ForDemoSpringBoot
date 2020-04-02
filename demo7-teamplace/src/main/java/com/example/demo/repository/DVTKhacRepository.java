package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.sanpham.DVTKhac;

@Repository
public interface DVTKhacRepository extends CrudRepository<DVTKhac, Integer> {
	@Query("SELECT dvt FROM DVTKhac dvt WHERE dvt.sanPham.id = ?1")
	List<DVTKhac> findBySanPhamId(String id);

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.sanpham.ThanhPhan;

@Repository
public interface ThanhPhanRepository extends CrudRepository<ThanhPhan, Integer>{
	@Query("SELECT tp FROM ThanhPhan tp WHERE tp.sanPham.id = ?1")
	List<ThanhPhan> findBySanPhamId(String id);
}

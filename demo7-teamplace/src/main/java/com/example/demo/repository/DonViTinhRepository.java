package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.sanpham.DonViTinh;

public interface DonViTinhRepository extends CrudRepository<DonViTinh, String> {
	@Query(value = "SELECT * FROM ( SELECT * FROM donvitinh ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	DonViTinh getTheLast();

}

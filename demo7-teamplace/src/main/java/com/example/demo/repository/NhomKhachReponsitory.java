package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.khachhang.NhomKhach;
@Repository
public interface NhomKhachReponsitory extends CrudRepository<NhomKhach, String>{
	@Query(value = "SELECT * FROM ( SELECT * FROM nhomkhachhang ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	NhomKhach getTheLast();
	@Query(value = "SELECT COUNT(*) FROM `nhomkhachhang` WHERE macha = ?1", nativeQuery = true)
	int countByMaChaId(String id);


}

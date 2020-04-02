package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.chungtu.DonDatHang;
@Repository
public interface DonDatHangRepository extends CrudRepository<DonDatHang, String> {
	@Query(value = "SELECT * FROM ( SELECT * FROM chungtu ct WHERE ct.id LIKE '%DDH' ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	DonDatHang findTheLast();
	@Query(value = "SELECT ddh FROM DonDatHang ddh WHERE ddh.trangThai = 0")
	List<DonDatHang> getAll();
}

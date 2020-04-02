package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.chungtu.BanLe;
@Repository
public interface BanLeRepository extends CrudRepository<BanLe, String>{
	@Query("SELECT bl FROM BanLe bl WHERE bl.banPhong.id = ?1 AND bl.trangThai = 0")
	BanLe getBanLeByBanPhongId(String id);
	@Query(value = "SELECT * FROM ( SELECT * FROM chungtu ct WHERE ct.id LIKE '%BL' ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	BanLe findTheLast();
	@Query("SELECT COUNT(bl) FROM BanLe bl WHERE trangThai = ?1 AND banPhong.id = ?2")
	Long countByTrangThai(int trangThai, String banPhongId);
	@Query("SELECT bl FROM BanLe bl WHERE bl.ngayCT >= ?1 AND bl.ngayCT <= ?2 AND bl.trangThai = 1")
	List<BanLe> findBetween(Date from, Date to);
}

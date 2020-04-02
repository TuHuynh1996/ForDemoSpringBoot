package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.chungtu.DongChungTu;
@Repository
public interface DongChungTuRepository extends CrudRepository<DongChungTu, Integer>{
	@Query("SELECT dct FROM DongChungTu dct WHERE dct.chungTu.id = ?1")
	List<DongChungTu> getDongChungTuByChungTuId(String id);
	@Query("SELECT count(dct) FROM DongChungTu dct WHERE dct.sanPham.id = ?1")
	double countBySanPhamId(String id);
	
}

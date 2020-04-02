package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.sanpham.BangGia;

public interface BangGiaRepository extends CrudRepository<BangGia, Integer> {
	@Query(value = "SELECT * FROM banggia u WHERE u.masp = ?1", nativeQuery = true)
	List<BangGia> getBySanPhamId(String id);
	@Query(value = "SELECT * FROM banggia u WHERE u.mabg = ?1", nativeQuery = true)
	List<BangGia> getByLoaiBangGia(String id);
	@Query(value = "SELECT bg FROM BangGia bg WHERE bg.dvtKhac.id = ?1")
	List<BangGia> getByDVTKhacId(Integer id);
}

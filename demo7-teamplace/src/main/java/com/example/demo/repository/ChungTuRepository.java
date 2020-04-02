package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.chungtu.ChungTu;
@Repository
public interface ChungTuRepository extends CrudRepository<ChungTu, String> {
	@Query(value = "SELECT count(id) FROM chungtu ct WHERE ct.nhan_vien_id = ?1 OR ct.thu_ngan_id = ?2", nativeQuery = true)
	double countByNhanVienId(String nhanVienId, String thuNganId);
	@Query(value = "SELECT count(id) FROM chungtu ct WHERE ct.khach_hang_id = ?1", nativeQuery = true)
	double countByKhachHangId(String khachHangId);
}

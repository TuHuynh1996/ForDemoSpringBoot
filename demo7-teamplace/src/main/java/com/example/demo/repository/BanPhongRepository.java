package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.banphong.BanPhong;
@Repository
public interface BanPhongRepository  extends CrudRepository<BanPhong, String> {
	@Query("SELECT bp FROM BanPhong bp WHERE bp.status != 2")
	List<BanPhong> getAllBanPhongMenu();

}

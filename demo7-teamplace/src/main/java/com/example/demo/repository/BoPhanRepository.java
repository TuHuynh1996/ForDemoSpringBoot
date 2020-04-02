package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.nhanvien.BoPhan;
@Repository
public interface BoPhanRepository extends CrudRepository<BoPhan, String> {
	@Query(value = "SELECT * FROM ( SELECT * FROM bophan ORDER BY id DESC LIMIT 1 )Var1 ORDER BY id ASC", nativeQuery = true)
	BoPhan findTheLastBP();
	@Query(value = "SELECT COUNT(*) FROM `bophan` WHERE macha = ?1", nativeQuery = true)
	int countByMaCha(String id);
}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.banphong.KhuVuc;
@Repository
public interface KhuVucRepository extends CrudRepository<KhuVuc, String>{

}

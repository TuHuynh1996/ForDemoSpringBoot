package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IChungTuService;
import com.example.demo.model.chungtu.ChungTu;
import com.example.demo.repository.ChungTuRepository;
@Service
public class ChungTuService implements IChungTuService {
	@Autowired
	ChungTuRepository chungTuRepository;
	
	@Override
	public List<ChungTu> getAllDatBan(){
		List<ChungTu> chungTu = new ArrayList<ChungTu>();
		chungTuRepository.findAll().forEach(chungTu::add);
		return chungTu;
	}
}

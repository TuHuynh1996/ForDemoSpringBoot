package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IDongChungTuService;
import com.example.demo.model.chungtu.DongChungTu;
import com.example.demo.repository.DongChungTuRepository;
@Service
public class DongChungTuService implements IDongChungTuService{
	@Autowired
	DongChungTuRepository dongChungTuRepository;
	@Override
	public List<DongChungTu> getDongChungTuByChungTuId(String id) {
		return dongChungTuRepository.getDongChungTuByChungTuId(id);
	}
	@Override
	public List<DongChungTu> getDongChungTuByChungTuIdBangGia(String id, String bangGia) {
		List<DongChungTu> dongChungTu = dongChungTuRepository.getDongChungTuByChungTuId(id);
		for(DongChungTu value:dongChungTu) {
			value.getSanPham().setBangGiaSelected(bangGia);
		}
		return dongChungTu;
	}

}

package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.IService.IBangGiaService;
import com.example.demo.model.sanpham.BangGia;
import com.example.demo.repository.BangGiaRepository;
@Service
public class BangGiaService implements IBangGiaService{
	@Autowired
	BangGiaRepository bangGiaRepository;
	@Override
	public void save(BangGia bg) {
		bangGiaRepository.save(bg);
	}
	public void saveAll(List<BangGia> bg) {
		bangGiaRepository.saveAll(bg);
	}
	@Override
	public List<BangGia> getBySanPhamId(String id){
		return bangGiaRepository.getBySanPhamId(id);
	}
	@Override
	public List<BangGia> getByLoaiNhomHangId(String id) {
		return bangGiaRepository.getByLoaiBangGia(id);
	}
}
